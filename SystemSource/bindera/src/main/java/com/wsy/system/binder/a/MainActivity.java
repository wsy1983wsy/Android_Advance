package com.wsy.system.binder.a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.wsy.system.binder.ILoginInterface;

public class MainActivity extends AppCompatActivity {
    private boolean isStartRemote = false;
    private ServiceConnection conn;
    private ILoginInterface loginInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //连接 成功
                loginInterface = ILoginInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                //连接 失败
            }
        };
        initBindService();
    }

    private void initBindService() {
        Intent intent = new Intent();
        //设置server应用Action（服务的唯一表示）
        intent.setAction("BinderB_Action");
        //设置server包名
        intent.setPackage("com.wsy.system.binder.b");
        //开启绑定服务
        bindService(intent, conn, BIND_AUTO_CREATE);
        //标记跨进城绑定
        isStartRemote = true;
    }

    public void startQQLoginAction(View view) {
        if (loginInterface != null) {
            try {
                loginInterface.login();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "请安装QQ应用...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解绑服务，一定要记得解绑服务，否则可能会报异常(服务连接资源异常)
        if (isStartRemote) {
            unbindService(conn);
        }
    }
}
