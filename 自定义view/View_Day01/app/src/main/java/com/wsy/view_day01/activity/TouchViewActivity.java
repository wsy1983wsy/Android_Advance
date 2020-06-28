package com.wsy.view_day01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.wsy.view_day01.R;
import com.wsy.view_day01.view.touch.TouchView;

public class TouchViewActivity extends Activity {
    TouchView touchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_view);
        touchView = findViewById(R.id.touchView);

        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TouchViewActivity", "event: " + event.getAction());
                return false;
            }
        });
        /*touchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TouchViewActivity", "click");
            }
        });*/
    }
}
