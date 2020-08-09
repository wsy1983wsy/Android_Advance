package com.wsy.aspect.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wsy.aspect.login.annotation.ClickBehavior;
import com.wsy.aspect.login.annotation.LoginCheck;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @ClickBehavior("登录")
    public void login(View view) {
        Log.d(TAG, "模拟登录请求...验证通过，登录成功！");
    }

    @ClickBehavior("专区")
    @LoginCheck
    public void area(View view) {
        Log.d(TAG, "跳转到我的专区！");
        startActivity(new Intent(this, OtherActivity.class));
    }

    @ClickBehavior("优惠券")
    @LoginCheck
    public void coupon(View view) {
        Log.d(TAG, "跳转到优惠券界面！");
        startActivity(new Intent(this, OtherActivity.class));
    }

    @ClickBehavior("积分")
    @LoginCheck
    public void score(View view) {
        Log.d(TAG, "跳转到积分界面！");
        startActivity(new Intent(this, OtherActivity.class));
    }
}
