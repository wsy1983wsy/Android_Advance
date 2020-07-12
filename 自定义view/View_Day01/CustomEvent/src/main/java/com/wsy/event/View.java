package com.wsy.event;

import com.wsy.event.listener.OnClickListener;
import com.wsy.event.listener.OnTouchListener;

public class View {
    private int left, top, right, bottom;
    private OnTouchListener touchListener;
    private OnClickListener clickListener;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTouchListener(OnTouchListener touchListener) {
        this.touchListener = touchListener;
    }

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public View(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public boolean isContainer(int x, int y) {
        if (x >= left && x <= right && y >= top && y <= bottom) {
            return true;
        }
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result = false;
        if (touchListener != null && touchListener.onTouch(this, event)) {
            result = true;
        }
        //TouchListener没有消费，则让onTouchEvent处理
        if (!result && onTouchEvent(event)) {
            result = true;
        }
        return result;
    }

    private boolean onTouchEvent(MotionEvent event) {
        if (clickListener != null) {
            clickListener.onClick(this);
            return true;
        }
        return false;
    }

}
