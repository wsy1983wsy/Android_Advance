package com.wsy.view_day01.paint.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    }
}
