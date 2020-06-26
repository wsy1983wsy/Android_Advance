package com.wsy.view_day01.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.wsy.view_day01.util.SizeUtil;

public class LetterSideBar extends View {
    private Paint paint;
    private static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private String currentLetter = "";

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
//        paint.setColor(Color.parseColor("#333333"));
        paint.setColor(Color.BLUE);
        paint.setTextSize(SizeUtil.sp2px(context, 12));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //计算宽度，paddingLeft + 字母宽度+ paddingRight
        float letterWidth = paint.measureText("A");
        int width = (int) (getPaddingLeft() + letterWidth + getPaddingRight());
        //计算高度
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画26个字母
        int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / letters.length;
        for (int i = 0; i < letters.length; i++) {
            int x = getWidth() / 2 - (int) paint.measureText(letters[i]) / 2;
            int centerY = i * itemHeight + itemHeight / 2 + getPaddingTop();
            //基线
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
            int baseLine = centerY + (int) dy;
            if (TextUtils.equals(currentLetter, letters[i])) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.BLUE);
            }
            canvas.drawText(letters[i], x, baseLine, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //计算出当前触摸的字母，获取当前的位置
                float currentY = event.getY();
                int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / letters.length;
                int currentPosition = (int) (currentY / itemHeight);

                if (currentPosition < 0) {
                    currentPosition = 0;
                }

                if (currentPosition > letters.length - 1) {
                    currentPosition = letters.length - 1;
                }
                currentLetter = letters[currentPosition];
                if (mListener != null) {
                    mListener.touch(currentLetter, true);
                }

                // 重新绘制
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (mListener != null) {
                    mListener.touch(currentLetter, false);
                }
                break;
        }
        return true;
    }

    private LetterTouchListener mListener;

    public void setOnLetterTouchListener(LetterTouchListener listener) {
        this.mListener = listener;
    }

    // 接口回掉其他View会不会使用？
    public interface LetterTouchListener {
        void touch(CharSequence letter, boolean isTouch);
    }
}
