package com.wsy.view_day01.paint.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class PathView extends View {

    private Path mPath = new Path();
    private Paint mPaint = new Paint();

    public PathView(Context context) {
        super(context);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mPaint.setStyle(Paint.Style.FILL);
        //lineTo表示的就是一阶贝塞尔曲线，表示的是一条直线
//        mPath.moveTo(100, 70); //移动
////        mPath.lineTo(140, 800);//连线
//        //等同于上一行代码效果
//        mPath.rLineTo(40, 730);
//        mPath.lineTo(250, 600);
//        mPath.close();

        mPath.moveTo(0, 0);
        mPath.lineTo(140, 180);
        mPath.arcTo(new RectF(400, 200, 600, 400), 0, 270, false);

        canvas.drawPath(mPath, mPaint);

    }
}
