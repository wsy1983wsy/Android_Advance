package com.wsy.view_day01.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.wsy.view_day01.R;

public class TextView extends View {
    //显式的文字
    private String text;
    //文字的大小,单位为像素
    private int textSize = 15;
    //文字的颜色
    private int textColor = Color.BLACK;
    // 画笔
    private Paint paint;

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
        text = array.getString(R.styleable.TextView_wsytext);
        textColor = array.getColor(R.styleable.TextView_wsytextColor, textColor);
        textSize = array.getDimensionPixelSize(R.styleable.TextView_wsytextSize, sp2px(textSize));
        //回收
        array.recycle();

        paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置字体的大小
        paint.setTextSize(textSize);
        //设置字体的颜色
        paint.setColor(textColor);
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

        // 1.确定的值，不需要计算，给多少就是多少
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // 2.给的是wrap_content（即模式为MeasureSpec.AT_MOST），需要计算,
        if (widthMode == MeasureSpec.AT_MOST) {
            //计算得到的宽度 与字体的长度有关，字体的大小有关，用画笔测量
            Rect bounds = new Rect();
            //获取字体的Rect
            paint.getTextBounds(text, 0, text.length(), bounds);
            width = bounds.width();
        }
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            //计算得到的宽度 与字体的长度有关，字体的大小有关，用画笔测量
            Rect bounds = new Rect();
            //获取字体的Rect
            paint.getTextBounds(text, 0, text.length(), bounds);
            height = bounds.height();
        }
        // 设置控件的宽高
        setMeasuredDimension(width, height);
    }

    /**
     * 绘制内容
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画文字，文字，x， y ，画笔
        // x为横坐标快开始的位置
        // y基线的的位置，baseline（基线）
//        canvas.drawText(text, 0, getHeight() / 2, paint);
//        canvas.drawCircle();
//        canvas.drawArc();
        //获取基线
        //参考网址：http://www.imooc.com/article/277490?block_id=tuijian_wz
        //获得画笔的FontMetrics，用来计算baseLine。因为drawText的y坐标，代表的是绘制的文字的baseLine的位置
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        //计算出在每格index区域，竖直居中的baseLine值
        int baseline = (int) ((getHeight() - fontMetrics.bottom - fontMetrics.top) / 2);
        canvas.drawText(text, 0, baseline, paint);
        setBackgroundColor(Color.WHITE);
        setWillNotDraw(false);
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

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }
}
