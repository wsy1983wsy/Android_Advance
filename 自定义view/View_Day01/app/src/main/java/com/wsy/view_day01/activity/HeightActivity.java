package com.wsy.view_day01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.wsy.view_day01.R;

public class HeightActivity extends Activity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);
        textView = findViewById(R.id.textView);
        Log.d("HeightActivity", "height1 : " + textView.getMeasuredHeight());
        textView.post(() -> {//post方法其实什么都没有干，只是把runnable保存到队列中，等到dispatchAttachWindow之后做测量，才会执行runnable
            Log.d("HeightActivity", "height2 : " + textView.getMeasuredHeight());
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("HeightActivity", "height3 : " + textView.getMeasuredHeight());
    }
}
