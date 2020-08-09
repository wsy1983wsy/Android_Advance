package com.wsy.cmake.aop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity implements DbOperation {
    DbOperation dbOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用动态代理的方式，做切面
        dbOperation = (DbOperation) Proxy.newProxyInstance(DbOperation.class.getClassLoader(), new Class[]{DbOperation.class}, new DBHandler(this));
    }

    public void jump(View view) {
//        startActivity(new Intent(this, OrderActivity.class));
        dbOperation.insert();
    }

    @Override
    public void insert() {
        Log.d("DBHelper", "插入数据");
    }

    @Override
    public void delete() {
        Log.d("DBHelper", "删除数据");
    }

    @Override
    public void update() {
        Log.d("DBHelper", "更新数据");
    }

    @Override
    public void save() {
        Log.d("DBHelper", "保存数据");
    }
}