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

public class ColorTrackTextView extends androidx.appcompat.widget.AppCompatTextView {
    // 1. 实现一个文字两种颜色 - 绘制不变色字体的画笔
    private Paint mOriginPaint;
    // 1. 实现一个文字两种颜色 - 绘制变色字体的画笔
    private Paint mChangePaint;
    private float mCurrentProgress = 0.5f;
    Direction mDirection = Direction.LEFT_TO_RIGHT;

    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }

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
    /*@Override
    protected void onDraw(Canvas canvas) {
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
        int middle = (int) (currentProgress * getWidth());

        canvas.save();
        Rect rect = new Rect(0, 0, middle, getHeight());
        canvas.clipRect(rect);
        canvas.drawText(text, x, baseLine, mChangePaint);
        canvas.restore();

        canvas.save();
        rect = new Rect(middle, 0, getWidth(), getHeight());
        canvas.clipRect(rect);
        canvas.drawText(text, x, baseLine, mOriginPaint);
        canvas.restore();
    }*/


    // 1. 一个文字两种颜色
    // 利用clipRect的API 可以裁剪  左边用一个画笔去画  右边用另一个画笔去画  不断的改变中间值
    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        // canvas.clipRect();  裁剪区域
        // 根据进度把中间值算出来
        int middle = (int) (mCurrentProgress * getWidth());

        // 从左变到右
        if (mDirection == Direction.LEFT_TO_RIGHT) {  // 左边是红色右边是黑色
            // 绘制变色
            drawText(canvas, mChangePaint, 0, middle);
            drawText(canvas, mOriginPaint, middle, getWidth());
        } else {
            // 右边是红色左边是黑色
            drawText(canvas, mChangePaint, getWidth() - middle, getWidth());
            // 绘制变色
            drawText(canvas, mOriginPaint, 0, getWidth() - middle);
        }
    }


    /**
     * 绘制Text
     *
     * @param canvas
     * @param paint
     * @param start
     * @param end
     */
    private void drawText(Canvas canvas, Paint paint, int start, int end) {
        canvas.save();
        // 绘制不变色
        Rect rect = new Rect(start, 0, end, getHeight());
        canvas.clipRect(rect);
        // 我们自己来画
        String text = getText().toString();
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        // 获取字体的宽度
        int x = getWidth() / 2 - bounds.width() / 2;
        // 基线baseLine
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(text, x, baseLine, paint);// 这么画其实还是只有一种颜色
        canvas.restore();
    }

    public void setDirection(Direction direction) {
        this.mDirection = direction;
    }

    public void setCurrentProgress(float currentProgress) {
        this.mCurrentProgress = currentProgress;
        invalidate();
    }

    public void setChangeColor(int changeColor) {
        this.mChangePaint.setColor(changeColor);
    }

    public void setOriginColor(int originColor) {
        this.mOriginPaint.setColor(originColor);
    }
}
