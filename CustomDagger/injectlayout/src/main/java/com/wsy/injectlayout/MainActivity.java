package com.wsy.injectlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wsy.injectlayout.annotation.BindView;
import com.wsy.injectlayout.annotation.ContentView;
import com.wsy.injectlayout.annotation.OnClick;

@ContentView(R.layout.activity_main)

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button1)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        InjectTool.inject(this);
        Log.d("MainActivity", "onCreate");
    }

    @OnClick(R.id.button1)
    public void onClick(View view) {
        Log.d("MainActivity", "onClick");
    }
}
