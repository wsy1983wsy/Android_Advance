package com.wsy.view_day01.view.ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wsy.view_day01.R;

public class PercentRelativeLayout extends RelativeLayout {
    public PercentRelativeLayout(Context context) {
        super(context);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams params = child.getLayoutParams();
            if (!checkLayoutParams(params)) {
                continue;
            }
            LayoutParams lp = (LayoutParams) params;
            float widthPercent = lp.widthPercent;
            float heightPercent = lp.heightPercent;
            float marginLeftPercent = lp.marginLeftPercent;
            float marginRightPercent = lp.marginRightPercent;
            float marginTopPercent = lp.marginTopPercent;
            float marginBottomPercent = lp.marginBottomPercent;
            if (widthPercent > 0) {
                params.width = (int) (widthSize * widthPercent);
            }
            if (heightPercent > 0) {
                params.height = (int) (heightSize * heightPercent);
            }

            if (marginLeftPercent > 0) {
                ((LayoutParams) params).leftMargin = (int) (widthSize * marginLeftPercent);
            }

            if (marginRightPercent > 0) {
                ((LayoutParams) params).rightMargin = (int) (widthSize * marginRightPercent);
            }

            if (marginTopPercent > 0) {
                ((LayoutParams) params).topMargin = (int) (heightSize * marginTopPercent);
            }

            if (marginBottomPercent > 0) {
                ((LayoutParams) params).bottomMargin = (int) (heightSize * marginBottomPercent);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {
        private float widthPercent;
        private float heightPercent;
        private float marginLeftPercent;
        private float marginRightPercent;
        private float marginTopPercent;
        private float marginBottomPercent;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.PercentRelativeLayout);
            widthPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_widthPercent, 0);
            heightPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_heightPercent, 0);
            marginLeftPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_marginLeftPercent, 0);
            marginRightPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_marginRightPercent, 0);
            marginTopPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_marginTopPercent, 0);
            marginBottomPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_marginBottomPercent, 0);
            typedArray.recycle();
        }

    }
}
