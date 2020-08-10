package com.wsy.system.binder.a.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.wsy.system.binder.ILoginInterface;

public class ResultService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ILoginInterface.Stub() {
            @Override
            public void login() throws RemoteException {

            }

            @Override
            public void loginCallback(boolean loginStatus, String loginUser) throws RemoteException {
                Log.d("ResultService", "login status : " + loginStatus + " login user:" + loginUser);
            }
        };
    }
}
