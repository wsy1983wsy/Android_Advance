package com.wsy.animator;

// 关键帧，保存着某一个时刻的具体状态，
// 初始化的时候都已经计算出来了
public class MyFloatKeyFrame {
    //当前帧所占的百分比
    float mFraction;
    //值类型
    Class mValueType;
    //属性的当前值
    float mValue;

    public MyFloatKeyFrame(float fraction, float value) {
        mFraction = fraction;
        mValue = value;
        mValueType = float.class;
    }

    public float getValue() {
        return mValue;
    }

    public void setValue(float mValue) {
        this.mValue = mValue;
    }

    public float getFraction() {
        return mFraction;
    }
}
