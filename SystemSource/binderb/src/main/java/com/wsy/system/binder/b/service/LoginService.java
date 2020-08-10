package com.wsy.system.binder.b.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.wsy.system.binder.ILoginInterface;
import com.wsy.system.binder.b.MainActivity;

public class LoginService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ILoginInterface.Stub() {
            @Override
            public void login() throws RemoteException {
                Log.e("netease >>> ", "BinderB_MyService");
                // 单项通信有问题，真实项目双向通信，双服务绑定
                serviceStartActivity();
            }

            @Override
            public void loginCallback(boolean loginStatus, String loginUser) throws RemoteException {

            }
        };
    }

    /**
     * 在Service启动Activity，需要配置：.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     */
    private void serviceStartActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
