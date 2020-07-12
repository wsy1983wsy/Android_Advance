package com.wsy.view_day01.paint;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.wsy.view_day01.paint.view.GradientLayout;
import com.wsy.view_day01.paint.view.PathMeasureView;
import com.wsy.view_day01.paint.view.PathView;
import com.wsy.view_day01.paint.view.XfermodeView;
import com.wsy.view_day01.paint.view.XfermodesView;

public class PaintActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathMeasureView(this));
    }
}
