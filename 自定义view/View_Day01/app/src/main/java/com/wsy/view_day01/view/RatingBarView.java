package com.wsy.view_day01.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.wsy.view_day01.R;

public class RatingBarView extends View {
    private Paint paint;
    private int selectedImage;
    private int normalImage;
    private int imageCount;
    private int currentX;
    Bitmap selectedBitmap;
    Bitmap normalBitmap;

    public RatingBarView(Context context) {
        this(context, null);
    }

    public RatingBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBarView);
        selectedImage = typedArray.getResourceId(R.styleable.RatingBarView_selectedImage, R.mipmap.star_selected);
        normalImage = typedArray.getResourceId(R.styleable.RatingBarView_normalImage, R.mipmap.star_normal);
        imageCount = typedArray.getInt(R.styleable.RatingBarView_imageCount, 5);
        typedArray.recycle();
        paint = new Paint();
        paint.setAntiAlias(true);
        selectedBitmap = BitmapFactory.decodeResource(getResources(), selectedImage);
        normalBitmap = BitmapFactory.decodeResource(getResources(), normalImage);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = selectedBitmap.getWidth() * 5 + 20 * 4;
        int height = selectedBitmap.getHeight();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int imgWidth = selectedBitmap.getWidth();
        int selectedCount = getSelectedCount();
        for (int i = 0; i < selectedCount; i++) {
            canvas.drawBitmap(selectedBitmap, i * imgWidth + i * 20, 0, paint);
        }
        for (int i = selectedCount; i < imageCount; i++) {
            canvas.drawBitmap(normalBitmap, i * imgWidth + i * 20, 0, paint);
        }
    }

    private int getSelectedCount() {
        Bitmap selectedBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.star_selected);
        int imgWidth = selectedBitmap.getWidth();
        for (int i = imageCount; i >= 1; i--) {
            int totalImageWidth = imgWidth * i + 20 * (i - 1);
            int lessTotalImageWidth = imgWidth * (i - 1) + (i > 1 ? 20 * (i - 1) : 0);
            if ((currentX < totalImageWidth && currentX > lessTotalImageWidth) || (currentX > totalImageWidth)) {
                return i;
            }
        }
        return 0;
    }

    int lastX = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                Log.d("RatingView", "x:" + x);
                if (x != lastX) {
                    currentX = x;
                    invalidate();
                }
        }

        return true;
    }
}
