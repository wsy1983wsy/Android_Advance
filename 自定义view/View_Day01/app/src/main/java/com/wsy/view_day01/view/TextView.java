package com.wsy.view_day01.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.wsy.view_day01.R;

public class TextView extends View {
    //显式的文字
    private String text;
    //文字的大小
    private int textSize = 15;
    //文字的颜色
    private int textColor = Color.BLACK;

    // 在代码中new TextView的时候使用
    // TextView textView = new Text(getActivity());
    public TextView(Context context) {
        this(context, null);
    }

    // 在布局中使用
    // xml中直接使用的时候会被调用
    //    <com.wsy.view_day01.view.TextView
    //        android:layout_width="match_parent"
    //        android:layout_height="wrap_content"
    //        android:textColor="@android:color/holo_blue_dark"
    //        android:textSize="20sp" />
    public TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // 在布局中，有style时会被调用
    //   <com.wsy.view_day01.view.TextView
    //        style="@style/defaultTextView" />
    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextView);
        text = array.getString(R.styleable.TextView_text);
        textColor = array.getColor(R.styleable.TextView_textColor, textColor);
        textSize = array.getDimensionPixelSize(R.styleable.TextView_textSize, textSize);
        //回收
        array.recycle();
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

        if (widthMode == MeasureSpec.AT_MOST) {

        }
    }

    /**
     * 绘制内容
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawText();
//        canvas.drawCircle();
//        canvas.drawArc();
    }

    /**
     * 处理跟用户交互的事件，手指触摸等等
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //手指按下
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起
                break;
            case MotionEvent.ACTION_MOVE:
                //手指移动
                break;
        }
        return super.onTouchEvent(event);
    }
}
