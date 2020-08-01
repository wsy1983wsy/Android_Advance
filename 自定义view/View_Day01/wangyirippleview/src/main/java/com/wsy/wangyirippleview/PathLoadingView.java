package com.wsy.wangyirippleview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathLoadingView extends View {
    private Path mPath;
    private Paint mPaint;
    private float mLength;
    private Path dst;
    private float mAnimatorValue;

    //    新加入的
    private PathMeasure mPathMeasure;

    public PathLoadingView(Context context) {
        this(context, null);
    }

    public PathLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#FF4081"));
        mPaint.setStrokeWidth(10f);
        mPaint.setStyle(Paint.Style.STROKE);
        //Path
        mPath = new Path();
        mPath.addCircle(300f, 300f, 100f, Path.Direction.CW);//加入一个半径为100圆

        //path的测量工具
        mPathMeasure = new PathMeasure(mPath, true);
        mLength = mPathMeasure.getLength();
        dst = new Path();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(valueAnimator.INFINITE);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        dst.reset();
//       dst.moveTo(0, 0);
        float distance = mLength * mAnimatorValue;
        //计算一个起始值，这个起始值可以根据终止值计算出来
        float start = (float) (distance - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * mLength));
        //获取路径中的一段
//        mPathMeasure.getSegment(0, distance, dst, true);
        mPathMeasure.getSegment(start, distance, dst, true);
        canvas.drawPath(dst, mPaint);
    }
}
