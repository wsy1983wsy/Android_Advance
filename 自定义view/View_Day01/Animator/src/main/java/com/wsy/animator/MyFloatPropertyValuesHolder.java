package com.wsy.animator;

import android.view.View;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

public class MyFloatPropertyValuesHolder {
    //属性名称
    String propertyName;
    //float
    Class valueType;
    Method setter = null;
    MyKeyFrameSet keyFrameSet;

    public MyFloatPropertyValuesHolder(String propertyName, float... values) {
        this.propertyName = propertyName;
        this.valueType = float.class;
        keyFrameSet = MyKeyFrameSet.ofFloat(values);
    }

    public void setupSetter(WeakReference<View> target) {
        char firstLetter = Character.toUpperCase(propertyName.charAt(0));
        String theRest = propertyName.substring(1);
        String methodName = "set" + firstLetter + theRest;
        try {
            setter = target.get().getClass().getMethod(methodName, float.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setAnimatedValue(View view, float fraction) {
        Object value = keyFrameSet.getValue(fraction);
        try {
            setter.invoke(view, value);
        } catch (Throwable e) {
        }
    }
}
