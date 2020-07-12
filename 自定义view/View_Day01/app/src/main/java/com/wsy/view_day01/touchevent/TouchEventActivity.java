package com.wsy.view_day01.touchevent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wsy.view_day01.R;

public class TouchEventActivity extends Activity {
    private static final String TAG = "TouchEventActivity";
    FrameLayout container;
    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        container = findViewById(R.id.container);
        text = findViewById(R.id.text);
        MyViewGroup myViewGroup = new MyViewGroup(this);

        MyView myView = new MyView(this);
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "MyView onTouch  listener ");
                return false;
            }
        });

        myView.setClickable(true);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "MyView onClick");
            }
        });

        container.addView(myViewGroup);

        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch container ");
                return false;
            }
        });

        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch Text ");
                return false;
            }
        });
    }
}
