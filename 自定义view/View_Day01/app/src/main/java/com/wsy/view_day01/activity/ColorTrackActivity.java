package com.wsy.view_day01.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.wsy.view_day01.R;
import com.wsy.view_day01.view.ColorTrackTextView;

public class ColorTrackActivity extends Activity {
    ColorTrackTextView colorTrackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_track);
        colorTrackTextView = findViewById(R.id.colorTrackTextView);
    }

    public void leftToRight(View view) {
        changeColor(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
    }

    public void rightToLeft(View view) {
        changeColor(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
    }

    private void changeColor(ColorTrackTextView.Direction direction) {
        colorTrackTextView.setDirection(direction);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();
                colorTrackTextView.setCurrentProgress(currentStep);
            }
        });
        valueAnimator.start();
    }
}
