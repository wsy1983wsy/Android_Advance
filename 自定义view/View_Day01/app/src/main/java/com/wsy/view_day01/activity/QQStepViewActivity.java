package com.wsy.view_day01.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import com.wsy.view_day01.R;
import com.wsy.view_day01.view.QQStepView;

public class QQStepViewActivity extends Activity {
    QQStepView qqStepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_step_textview);
        qqStepView = findViewById(R.id.qqStepView);
        qqStepView.setMaxStep(4000);
        //属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(0, 3000);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentStep = (int) animation.getAnimatedValue();
                qqStepView.setCurrentStep(currentStep);
            }
        });
        valueAnimator.start();
    }
}
