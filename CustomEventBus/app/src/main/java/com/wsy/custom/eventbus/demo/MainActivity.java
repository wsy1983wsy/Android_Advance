package com.wsy.custom.eventbus.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.wsy.custom.eventbus.annotation.Subscribe;
import com.wsy.custom.eventbus.demo.model.UserInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Subscribe()
    public void onEvent(UserInfo userInfo) {
        Log.e("MainActivity", "user info : " + userInfo.toString());
    }
}