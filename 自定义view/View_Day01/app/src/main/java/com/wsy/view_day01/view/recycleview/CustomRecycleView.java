package com.wsy.view_day01.view.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.wsy.view_day01.R;

import java.util.ArrayList;
import java.util.List;

public class CustomRecycleView extends ViewGroup {
    Adapter adapter;
    //当前显示的View
    private List<View> viewList;
    //当前滑动的y值
    private int currentY;
    //行数
    private int rowCount;
    //view的第一行  是占内容的几行
    private int firstRow;
    //y偏移量
    private int scrollY;
    //初始化  第一屏最慢
    private boolean needRelayout;
    private int width;

    private int height;
    private int[] heights;//item  高度
    Recycler recycler;
    //最小滑动距离
    private int touchSlop;

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        if (adapter != null) {
            recycler = new Recycler(adapter.getViewTypeCount());
            scrollY = 0;
            firstRow = 0;
            needRelayout = true;
            requestLayout();//1  onMeasure   2  onLayout
        }
    }

    public CustomRecycleView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.touchSlop = viewConfiguration.getScaledTouchSlop();
        this.viewList = new ArrayList<>();
        this.needRelayout = true;
    }

    //初始化
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (needRelayout || changed) {
            needRelayout = false;
            viewList.clear();
            removeAllViews();
            if (adapter != null) {
                width = r - l;
                height = b - t;
                int left, top = 0, right, bottom;
                for (int i = 0; i < rowCount && top < height; i++) {
                    right = r;
                    bottom = top + heights[i];
                    View view = makeAndStep(i, 0, top, width, bottom);
                    this.viewList.add(view);
                    top = bottom;
                }

            }
        }
    }

    private View makeAndStep(int row, int left, int top, int right, int bottom) {
        View view = obtainView(row, right - left, bottom - top);
        view.layout(left, top, right, bottom);
        return view;
    }

    private View obtainView(int row, int width, int height) {
        int itemType = adapter.getItemViewType(row);
        View recycleView = recycler.get(itemType);
        View view = null;
        if (recycleView == null) {
            view = adapter.onCreateViewHolder(row, recycleView, this);
            if (view == null) {
                throw new RuntimeException("onCreateViewHolder 必须填充布局");
            }
        } else {
            view = adapter.onBinderViewHolder(row, recycleView, this);
        }
        view.setTag(R.id.tag_type_view, itemType);
        view.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        addView(view, 0);
        return view;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int h = 0;
        if (adapter != null) {
            this.rowCount = adapter.getCount();
            heights = new int[rowCount];
            for (int i = 0; i < heights.length; i++) {
                heights[i] = adapter.getHeight(i);
            }
        }
        int tmpH = sumArray(heights, 0, heights.length);
        h = Math.max(tmpH, heightSize);
        setMeasuredDimension(widthSize, h);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //    firstIndex  firstIndex+count
    private int sumArray(int array[], int firstIndex, int count) {
        int sum = 0;
        count += firstIndex;
        for (int i = firstIndex; i < count; i++) {
            sum += array[i];
        }
        return sum;
    }

    public interface Adapter {
        View onCreateViewHolder(int position, View convertView, ViewGroup parent);

        View onBinderViewHolder(int position, View convertView, ViewGroup parent);

        //Item的类型
        int getItemViewType(int row);

        //Item的类型数量
        int getViewTypeCount();

        int getCount();

        /**
         * item的个数
         *
         * @param index
         * @return
         */
        int getHeight(int index);
    }

}
