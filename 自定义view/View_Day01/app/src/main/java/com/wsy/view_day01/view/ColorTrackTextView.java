package com.wsy.view_day01.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wsy.view_day01.R;

public class ColorTrackTextView extends TextView {
    // 1. 实现一个文字两种颜色 - 绘制不变色字体的画笔
    private Paint mOriginPaint;
    // 1. 实现一个文字两种颜色 - 绘制变色字体的画笔
    private Paint mChangePaint;
    private float currentProgress = 0.5f;

    // 1. 实现一个文字两种颜色 - 当前的进度
    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);
    }


    /**
     * 1.1 初始化画笔
     */
    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);

        int originColor = array.getColor(R.styleable.ColorTrackTextView_originColor, getTextColors().getDefaultColor());
        int changeColor = array.getColor(R.styleable.ColorTrackTextView_changeColor, getTextColors().getDefaultColor());

        mOriginPaint = getPaintByColor(originColor);
        mChangePaint = getPaintByColor(changeColor);

        // 回收
        array.recycle();
    }


    /**
     * 1.根据颜色获取画笔
     */
    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        // 设置颜色
        paint.setColor(color);
        // 设置抗锯齿
        paint.setAntiAlias(true);
        // 防抖动
        paint.setDither(true);
        // 设置字体的大小  就是TextView的字体大小
        paint.setTextSize(getTextSize());
        return paint;
    }

    //利用clipRect 可以裁剪 左边用一个画笔画，右边用另一个画笔去画，不断的改变中间值
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        //屏蔽掉原来的onDraw方法
        String text = getText().toString();
        Rect bounds = new Rect();
        //获取字体的宽度
        mChangePaint.getTextBounds(text, 0, text.length(), bounds);
        int x = getWidth() / 2 - bounds.width() / 2;
        Paint.FontMetrics fontMetrics = mChangePaint.getFontMetrics();
        int dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
        int baseLine = getHeight() / 2 + dy;
        //根据进度计算中间值
        int middle = (int) (currentProgress * getMeasuredWidth());

        canvas.save();
        Rect rect = new Rect(0, 0, middle, getMeasuredHeight());
        canvas.clipRect(rect);
        canvas.drawText(text, x, baseLine, mChangePaint);
        canvas.restore();

        canvas.save();
        rect = new Rect(middle, 0, getWidth(), getMeasuredHeight());
        canvas.clipRect(rect);
        canvas.drawText(text, x, baseLine, mOriginPaint);
        canvas.restore();
    }
}
