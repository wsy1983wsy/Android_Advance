package com.wsy.activity.plugin_package;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wsy.activity.standard.ActivityInterface;

public class BaseActivity extends Activity implements ActivityInterface {
    protected Activity appActivity;

    @Override
    public void insertAppContext(Activity appActivity) {
        this.appActivity = appActivity;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {

    }

    @Override
    public void setContentView(View view) {
        appActivity.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        appActivity.setContentView(layoutResID);
    }

    @Override
    public void startActivity(Intent intent) {

        Intent intentNew = new Intent();
        intentNew.putExtra("className", intent.getComponent().getClassName());
        appActivity.startActivity(intentNew);
    }
}
