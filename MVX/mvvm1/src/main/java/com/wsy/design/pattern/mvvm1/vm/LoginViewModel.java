package com.wsy.design.pattern.mvvm1.vm;

import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.wsy.design.pattern.mvvm1.databinding.ActivityMainBinding;
import com.wsy.design.pattern.mvvm1.model.UserInfo;

public class LoginViewModel {
    public UserInfo userInfo;

    public LoginViewModel(ActivityMainBinding binding) {
        userInfo = new UserInfo();
        binding.setLoginViewModel(this);
    }

    public TextWatcher nameInputListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            userInfo.name.set(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public TextWatcher pwdInputListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            userInfo.pwd.set(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 模拟网络请求
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // Model层属性的变更，改变View层的显示
                    // userInfo.name.set("Mir Peng");
                    SystemClock.sleep(2000);

                    if ("163".equals(userInfo.name.get()) && "163".equals(userInfo.pwd.get())) {
                        Log.e("netease >>> ", "登录成功!");
                    } else {
                        Log.e("netease >>> ", "登录失败!");
                    }
                }
            }).start();
        }
    };
}
