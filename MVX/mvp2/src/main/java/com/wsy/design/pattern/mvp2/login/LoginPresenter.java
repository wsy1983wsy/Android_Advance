package com.wsy.design.pattern.mvp2.login;

import android.os.SystemClock;

import com.wsy.design.pattern.mvp2.MainActivity;
import com.wsy.design.pattern.mvp2.base.BasePresenter;
import com.wsy.design.pattern.mvp2.http.HttpEngine;
import com.wsy.design.pattern.mvp2.model.BaseEntity;
import com.wsy.design.pattern.mvp2.model.UserInfo;

public class LoginPresenter extends BasePresenter<LoginModel, MainActivity, LoginContract.Presenter> {

    public LoginPresenter() {
        super();
    }

    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter<UserInfo>() {
            @Override
            public void requestLogin(String name, String pwd) {
                try {
                    // 三种风格（P层很极端，要么不做事只做转发，要么就是拼命一个人干活）
//                    m.getContract().executeLogin(name, pwd);

                    // 第二种，让功能模块去工作（Library：下载、请求、图片加载）
//                    HttpEngine engine = new HttpEngine<>(LoginPresenter.this);
//                    engine.post(name, pwd);

                    // P层自己处理（谷歌例子）
                    if ("163".equalsIgnoreCase(name) && "163".equals(pwd)) {
                        responseResult(new UserInfo("网易", "彭老师"));
                    } else {
                        responseResult(null);
                    }
                    //内存泄露测试
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(50000);
                        }
                    }).start();
                } catch (Throwable e) {
                }
            }

            @Override
            public void responseResult(UserInfo userInfo) {
                //有结果，就告知View
                getView().getContract().handlerResult(userInfo);
            }
        };
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }
}
