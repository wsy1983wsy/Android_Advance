package com.wsy.event.listener;

import com.wsy.event.MotionEvent;
import com.wsy.event.View;

public interface OnTouchListener {
    boolean onTouch(View v, MotionEvent event);
}
