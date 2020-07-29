package com.wsy.view_day01.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.wsy.view_day01.view.CarView;

public class CarAnimationActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CarView(this));
    }
}
