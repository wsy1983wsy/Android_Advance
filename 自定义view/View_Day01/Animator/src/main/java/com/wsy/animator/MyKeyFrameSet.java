package com.wsy.animator;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;

import java.util.Arrays;
import java.util.List;

public class MyKeyFrameSet {
    //类型估值器
    TypeEvaluator typeEvaluator;
    //第一帧
    MyFloatKeyFrame firstKeyFrame;
    //关键帧列表
    List<MyFloatKeyFrame> keyFrameList;

    public MyKeyFrameSet(MyFloatKeyFrame... keyFrames) {
        this.keyFrameList = Arrays.asList(keyFrames);
        firstKeyFrame = keyFrames[0];
        typeEvaluator = new FloatEvaluator();
    }

    public static MyKeyFrameSet ofFloat(float[] values) {
        int keyFrameLength = values.length;
        MyFloatKeyFrame keyFrames[] = new MyFloatKeyFrame[keyFrameLength];
        keyFrames[0] = new MyFloatKeyFrame(0, values[0]);

        for (int i = 1; i < keyFrameLength; i++) {
            keyFrames[i] = new MyFloatKeyFrame((float) i * 1.0f / (keyFrameLength - 1), values[i]);
        }
        return new MyKeyFrameSet(keyFrames);
    }

    public Object getValue(float fraction) {
        MyFloatKeyFrame prevKeyFrame = firstKeyFrame;
        for (int i = 1; i < keyFrameList.size(); i++) {
            MyFloatKeyFrame nextKeyFrame = keyFrameList.get(i);
            if (fraction < nextKeyFrame.getFraction()) {
                return typeEvaluator.evaluate(fraction, prevKeyFrame.getValue(), nextKeyFrame.getValue());
            }
            prevKeyFrame = nextKeyFrame;
        }
        return null;
    }
}
