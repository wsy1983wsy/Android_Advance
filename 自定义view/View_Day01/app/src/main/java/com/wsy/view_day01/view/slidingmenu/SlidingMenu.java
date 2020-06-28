package com.wsy.view_day01.view.slidingmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class SlidingMenu extends HorizontalScrollView {
    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
