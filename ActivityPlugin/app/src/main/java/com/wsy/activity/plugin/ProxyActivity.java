package com.wsy.activity.plugin;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wsy.activity.standard.ActivityInterface;

import java.lang.reflect.Constructor;

public class ProxyActivity extends AppCompatActivity {

    @Override
    public Resources getResources() {
        return PluginManager.getInstance(this).getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance(this).getClassLoader();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 真正的加载 插件里面的 Activity
        String className = getIntent().getStringExtra("className");

        try {
            Class pluginActivityClass = getClassLoader().loadClass(className);
            Constructor constructor = pluginActivityClass.getConstructor(new Class[]{});
            Object pluginActivity = constructor.newInstance(new Object[]{});

            ActivityInterface activityInterface  = (ActivityInterface)pluginActivity;
            activityInterface.insertAppContext(this);
            Bundle bundle = new Bundle();
            bundle.putString("appName", "我是宿主传递过来的信息");

            // 执行插件里面的onCreate方法
            activityInterface.onCreate(bundle);

        } catch (Throwable e) {

        }
    }
}
