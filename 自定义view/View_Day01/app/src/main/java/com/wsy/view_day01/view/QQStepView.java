package com.wsy.view_day01.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.wsy.view_day01.R;

public class QQStepView extends View {
    private int outColor = Color.RED;
    private int innerColor = Color.BLUE;
    private int borderWidth = 18;
    private int stepTextSize = 18;
    private int stepTextColor = Color.BLUE;
    Paint outPaint;
    private int maxStep = 10000;
    private int currentStep;
    private Paint innerPatin;
    private Paint textPaint;


    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //1.分析效果
        //2.确定自定义属性，编写attrs.xml
        //3.在布局中使用
        //4.在自定义的View获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        outColor = typedArray.getColor(R.styleable.QQStepView_outerColor, outColor);
        innerColor = typedArray.getColor(R.styleable.QQStepView_innerColor, innerColor);
        stepTextColor = typedArray.getColor(R.styleable.QQStepView_stepTextColor, stepTextColor);
        borderWidth = (int) typedArray.getDimension(R.styleable.QQStepView_borderWidth, borderWidth);
        stepTextSize = (int) typedArray.getDimension(R.styleable.QQStepView_stepTextSize, stepTextSize);
        maxStep = typedArray.getInt(R.styleable.QQStepView_maxStep, maxStep);
        typedArray.recycle();
        outPaint = new Paint();
        outPaint.setAntiAlias(true);
        outPaint.setStrokeWidth(borderWidth);
        outPaint.setColor(outColor);
        outPaint.setStyle(Paint.Style.STROKE);//空心圆弧
        outPaint.setStrokeCap(Paint.Cap.ROUND);

        innerPatin = new Paint();
        innerPatin.setAntiAlias(true);
        innerPatin.setStrokeWidth(borderWidth);
        innerPatin.setColor(innerColor);
        innerPatin.setStyle(Paint.Style.STROKE);
        innerPatin.setStrokeCap(Paint.Cap.ROUND);


        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(innerColor);
        textPaint.setTextSize(stepTextSize);
        currentStep = 0;
        //5.onMeasure
        //5.画外圆弧，内圆弧，文字
        //7.其他
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //调用者再布局文件中可能wrap_content,宽度高度不一致
        //获取模式， AT_MOST 则设置40dp
        //宽度高度不一致，取最小值，确保是个正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int minSize = width < height ? width : height;

        setMeasuredDimension(minSize, minSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //6.1 画外圆弧
        //paint的stroke有宽度导致弧线没有显式完整,需要设置包含圆的矩形为new RectF(borderWidth, borderWidth, getWidth() - borderWidth, getHeight() - borderWidth)，
        // 而不是new RectF(0，0, getWidth(), getHeight())
        RectF rectF = new RectF(borderWidth, borderWidth, getWidth() - borderWidth, getHeight() - borderWidth);
        canvas.drawArc(rectF, 135, 270, false, outPaint);
        if (maxStep == 0) {
            return;
        }
        //6.2 画内圆弧,不能写死，百分比，由使用者设置
        float sweepAngle = (float) currentStep / maxStep;
        canvas.drawArc(rectF, 135, sweepAngle * 270, false, innerPatin);
        //6.3 画文字
        String stepText = currentStep + "";
        Rect bounds = new Rect();
        //获取字体的Rect
        textPaint.getTextBounds(stepText, 0, stepText.length(), bounds);
        int dx = getWidth() / 2 - bounds.width() / 2;
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        //计算出在每格index区域，竖直居中的baseLine值
        int dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
//        int baseline = (int) ((bounds.height() - fontMetrics.bottom - fontMetrics.top) / 2);
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(stepText, dx, baseLine, textPaint);
    }

    public synchronized void setMaxStep(int maxStep) {
        this.maxStep = maxStep;
    }

    public synchronized void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
        //重新绘制
        invalidate();
    }
}
