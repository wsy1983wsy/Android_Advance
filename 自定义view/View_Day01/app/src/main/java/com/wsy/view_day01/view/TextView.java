package com.wsy.view_day01.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TextView extends View {
    // 在代码中new TextView的时候使用
    // TextView textView = new Text(getActivity());
    public TextView(Context context) {
        super(context);
    }

    // 在布局中使用
    // xml中直接使用的时候会被调用
    //    <com.wsy.view_day01.view.TextView
    //        android:layout_width="match_parent"
    //        android:layout_height="wrap_content"
    //        android:textColor="@android:color/holo_blue_dark"
    //        android:textSize="20sp" />
    public TextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    // 在布局中，有style时会被调用
    //   <com.wsy.view_day01.view.TextView
    //        style="@style/defaultTextView" />
    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 自定义view的测量方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 布局的宽高都是由这个方法决定的
        // 指定控件的宽高，需要测量
        // 获取宽高的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST){

        }
    }
}
