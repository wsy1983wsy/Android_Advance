package com.wsy.design.pattern.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.wsy.design.pattern.databinding.databinding.ActivityMainBindingImpl;
import com.wsy.design.pattern.databinding.model.UserInfo;

public class MainActivity extends AppCompatActivity {
    private UserInfo userInfo;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActivityMainBindingImpl binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userInfo = new UserInfo();

        //第一种 单向绑定 model 到 view
       /*
        userInfo.setUsername("163");
        userInfo.setPassword("163");
        binding.setUser(userInfo);
        Log.d(TAG, "name: " + userInfo.getUsername() + " pwd: " + userInfo.getPassword());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                userInfo.setUsername("123");
                userInfo.setPassword("123");
                Log.d(TAG, "name: " + userInfo.getUsername() + " pwd: " + userInfo.getPassword());
            }
        }, 3000);*/
        //第二种单向
        userInfo.username.set("163");
        userInfo.password.set("163");
        binding.setUser(userInfo);
        Log.d(TAG, "name: " + userInfo.username.get() + " pwd: " + userInfo.password.get());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                userInfo.username.set("123");
                userInfo.password.set("123");
                Log.d(TAG, "name: " + userInfo.username.get() + " pwd: " + userInfo.password.get());
            }
        }, 3000);

        //双向
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "name: " + userInfo.username.get() + " pwd: " + userInfo.password.get());
            }
        }, 20000);

    }
}
