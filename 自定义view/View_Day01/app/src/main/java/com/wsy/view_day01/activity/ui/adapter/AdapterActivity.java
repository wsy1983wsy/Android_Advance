package com.wsy.view_day01.activity.ui.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wsy.view_day01.R;
import com.wsy.view_day01.util.UIUtils;
import com.wsy.view_day01.util.ViewCalculateUtil;

public class AdapterActivity extends Activity {
    private TextView tvText3;
    private TextView tvText4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adatper);
        UIUtils.getInstance(this.getApplicationContext());
        tvText3 = findViewById(R.id.tvText3);
        tvText4 = findViewById(R.id.tvText4);
        ViewCalculateUtil.setViewLayoutParam(tvText3, 540, 100, 0, 0, 0, 0);
        ViewCalculateUtil.setViewLayoutParam(tvText4, 1080, 100, 0, 0, 0, 0);
        ViewCalculateUtil.setTextSize(tvText4, 30);
    }

}
