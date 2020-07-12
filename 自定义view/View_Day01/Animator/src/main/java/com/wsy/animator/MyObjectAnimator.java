package com.wsy.animator;

import android.view.View;

import java.lang.ref.WeakReference;

public class MyObjectAnimator implements VSYNCManager.AnimationFrameCallback {
    public static final String TAG = "MyObjectAnimator";
    long startTime = -1;
    private long duration = 0;
    private WeakReference<View> target;
    MyFloatPropertyValuesHolder myFloatPropertyValuesHolder;
    private TimeInterpolator interpolator;
    private int index;

    public MyObjectAnimator(View view, String propertyName, float... values) {
        target = new WeakReference<>(view);
        myFloatPropertyValuesHolder = new MyFloatPropertyValuesHolder(propertyName, values);
//        timeInterpolator = new LineInterpolator();
    }

    public static MyObjectAnimator ofFloat(View view, String propertyName, float... values) {
        MyObjectAnimator animator = new MyObjectAnimator(view, propertyName, values);
        return animator;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setInterpolator(TimeInterpolator timeInterpolator) {
        this.interpolator = timeInterpolator;
    }

    //回调，每隔16毫秒
    @Override
    public boolean doAnimationFrame(long currentTime) {
        float total = duration / 16;
        //执行百分比 (index++)/total
        float fraction = (index++) * 1.0f / total;

        if (interpolator != null) {
            fraction = interpolator.getInterpolator(fraction);
        }
        if (index >= total) {
            index = 0;
        }
        myFloatPropertyValuesHolder.setAnimatedValue(target.get(), fraction);
        return false;
    }

    public void start() {
        myFloatPropertyValuesHolder.setupSetter(target);
        startTime = System.currentTimeMillis();
        VSYNCManager.getInstance().add(this);
    }
}
