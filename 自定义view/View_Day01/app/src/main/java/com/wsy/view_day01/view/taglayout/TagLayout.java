package com.wsy.view_day01.view.taglayout;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

public class TagLayout extends ViewGroup {
    private HashMap<Integer, Rect> childViewRectMap;

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

        for (int i = 0; i < childCount; i++) {
            int left;
            //循环子view
            View childView = getChildAt(i);
            //这段话之后就可以获取子View的宽高，因为会调用子View的onMeasure
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //根据子view计算和指定自己的布局

            if (i == 0 || ((lineWidth + childView.getMeasuredWidth() + getPaddingRight()) > width)) {
                left = getPaddingLeft();
                lineWidth = getPaddingLeft() + childView.getMeasuredWidth();
                height = height + childView.getMeasuredHeight();
            } else {
                left = lineWidth;
                lineWidth += childView.getMeasuredWidth();
            }
            Rect rect = new Rect(left, height - childView.getMeasuredHeight(), lineWidth, height);
            childViewRectMap.put(i, rect);
        }

        //计算自己的宽高
        setMeasuredDimension(width, height + getPaddingBottom());
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
}
