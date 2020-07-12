package com.wsy.view_day01.paint.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import com.wsy.view_day01.R;

public class PathMeasureView extends View {

    private Paint mPaint = new Paint();
    private Paint mLinePaint = new Paint(); //坐标系
    private Bitmap mBitmap;

    public PathMeasureView(Context context) {
        super(context);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);

        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStrokeWidth(6);

        //缩小图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrow, options);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mLinePaint);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mLinePaint);
        canvas.translate(getWidth() / 2, getHeight() / 2);

        /*Path path = new Path();
        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);

        PathMeasure pathMeasure = new PathMeasure();
        // forceClose会影响测量结果
        pathMeasure.setPath(path, true);
        Log.d("PathMeasureView", "onDraw forceClose = true : " + pathMeasure.getLength());

        PathMeasure pathMeasure2 = new PathMeasure();
        pathMeasure2.setPath(path, false);
        Log.d("PathMeasureView", "onDraw forceClose = false : " + pathMeasure2.getLength());
        //path修改后，需要重新setPath进行关联
        path.lineTo(-200, 200);
        pathMeasure2.setPath(path, false);
        Log.d("PathMeasureView", "onDraw  path changed forceClose = false : " + pathMeasure2.getLength());*/

      /*  Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        Path dst = new Path();
        dst.moveTo(-300, -300);

        PathMeasure pathMeasure = new PathMeasure(path, true);
        //截取一部分存入dst中，并且使用moveto保持截取得到Path第一个点位置不变
        pathMeasure.getSegment(200, 1000, dst, true);

        canvas.drawPath(path, mPaint);
        canvas.drawPath(dst, mLinePaint);*/
        Path path = new Path();
        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
        path.addOval(new RectF(-200, -200, 200, 200), Path.Direction.CW);

        canvas.drawPath(path, mPaint);
        PathMeasure pathMeasure = new PathMeasure(path, false);
        Log.d("PathMeasureView", "onDraw first path length: " + pathMeasure.getLength());
        //每次获取一条路径的长度
        pathMeasure.nextContour();
        Log.d("PathMeasureView", "onDraw first path length: " + pathMeasure.getLength());

    }
}
