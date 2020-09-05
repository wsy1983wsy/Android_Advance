package com.wsy.activity.eventbuslearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe
    public void onEvent(String string) {
        Log.e("MainActivity", "1 received: " + string);
    }

    @Subscribe(priority = 10, sticky = true)
    public void onEvent2(String string) {
        Log.e("MainActivity", "2 received: " + string);
    }


    public void jumpView(View view) {
        EventBus.getDefault().postSticky("sticky event");
        startActivity(new Intent(this, SecondActivity.class));
    }
}