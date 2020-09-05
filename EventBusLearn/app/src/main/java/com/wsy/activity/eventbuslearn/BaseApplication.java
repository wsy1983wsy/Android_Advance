package com.wsy.activity.eventbuslearn;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }
}
