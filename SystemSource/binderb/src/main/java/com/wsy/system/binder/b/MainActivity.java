package com.wsy.system.binder.b;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.wsy.system.binder.ILoginInterface;

//service端
public class MainActivity extends AppCompatActivity {

    // 模拟用户名和密码 的值
    private final static String NAME = "netease";
    private final static String PWD = "163";

    private EditText nameEt;
    private EditText pwdEt;
    private boolean isStartRemote; // 是否开启跨进程通信
    private ILoginInterface loginInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);  // 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    // 设置全屏
        setContentView(R.layout.activity_main);

        nameEt = findViewById(R.id.nameEt);
        pwdEt = findViewById(R.id.pwdEt);
        initBindService();
    }

    private void initBindService() {
        Intent intent = new Intent();
        // 设置Server应用Action
        intent.setAction("BinderA_Action");
        // 设置Server应用包名（5.1+要求）
        intent.setPackage("com.wsy.system.binder.a");
        // 开始绑定服务
        bindService(intent, conn, BIND_AUTO_CREATE);
        // 标识跨进程绑定
        isStartRemote = true;
    }

    // 点击事件
    public void startLogin(View view) {

        final String name = nameEt.getText().toString();
        final String pwd = pwdEt.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            showToast("请输入用户名或密码...");
            return;
        }

        // 模拟登录状态加载...
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("登录");
        progressDialog.setMessage("正在登录中...");
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            boolean isLoginSuccess = false;
                            if (NAME.equals(name) && PWD.equals(pwd)) {
                                showToast("QQ登录成功!");
                                isLoginSuccess = true;
                                finish();
                            } else {
                                showToast("QQ登录失败!");
                            }
                            progressDialog.dismiss();
                            loginInterface.loginCallback(isLoginSuccess, NAME);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    // 服务连接
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            loginInterface = ILoginInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isStartRemote) {
            // 解绑服务，一定要记得解绑服务，否则可能会报异常(服务连接资源异常)
            unbindService(conn);
        }
    }
}
