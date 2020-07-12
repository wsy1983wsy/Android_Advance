package com.wsy.view_day01.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.wsy.view_day01.R;

public class AnimatorActivity extends Activity {
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        button = findViewById(R.id.btn);
    }

    public void scale(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(button, "scaleX", 2f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }
}
