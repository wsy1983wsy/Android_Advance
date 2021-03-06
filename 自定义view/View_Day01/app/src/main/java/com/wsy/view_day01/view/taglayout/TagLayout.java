package com.wsy.view_day01.view.taglayout;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

public class TagLayout extends ViewGroup {
    private HashMap<Integer, Rect> childViewRectMap;
    private BaseAdapter adapter;

    public TagLayout(Context context) {
        this(context, null);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        childViewRectMap = new HashMap<>();
    }

    //2.1指定宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = getPaddingTop();
        int lineWidth = getPaddingLeft();
        int childCount = getChildCount();
        int originHeight = 0;
        int lastLineBottom = 0;
        for (int i = 0; i < childCount; i++) {
            //循环子view
            View childView = getChildAt(i);
            //这段话之后就可以获取子View的宽高，因为会调用子View的onMeasure
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            ViewGroup.MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
            int left;
            //根据子view计算和指定自己的布局
            //需要换行
            if (i == 0 || ((lineWidth + childView.getMeasuredWidth() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin) > width)) {
                left = getPaddingLeft() + layoutParams.leftMargin;
                lineWidth = getPaddingLeft() + childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                originHeight = height;
                lastLineBottom = height;
                height = height + childView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            } else {
                left = lineWidth + layoutParams.leftMargin;
                lineWidth += childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                height = Math.max(originHeight + childView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin, height);
            }
            Rect rect = new Rect(left, lastLineBottom + layoutParams.topMargin, left + childView.getMeasuredWidth(), lastLineBottom + layoutParams.topMargin + childView.getMeasuredHeight());
            childViewRectMap.put(i, rect);
        }

        //计算自己的宽高
        setMeasuredDimension(width, height + getPaddingBottom());
    }

    /**
     * 重新自定义layoutparams
     *
     * @param p
     * @return
     */
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return super.generateLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 2.2 摆放子View
     *
     * @param changed
     * @param let
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int let, int top, int right, int bottom) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            //循环子view
            View childView = getChildAt(i);
            Rect rect = childViewRectMap.get(i);
            childView.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    public void setAdapter(BaseAdapter adapter) {
        if (adapter == null) {
            throw new RuntimeException("param adapter is null");
        }
        removeAllViews();
        this.adapter = null;
        this.adapter = adapter;
        int childCount = this.adapter.getCount();
        for (int i = 0; i < childCount; i++) {
            View childView = this.adapter.getView(i, this);
            this.addView(childView);
        }
        invalidate();
    }
}
