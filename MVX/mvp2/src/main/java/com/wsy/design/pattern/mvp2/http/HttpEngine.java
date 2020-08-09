package com.wsy.design.pattern.mvp2.http;

import com.wsy.design.pattern.mvp2.login.LoginModel;
import com.wsy.design.pattern.mvp2.login.LoginPresenter;
import com.wsy.design.pattern.mvp2.model.UserInfo;

public class HttpEngine<P extends LoginPresenter> {

    private P p;

    public HttpEngine(P p) {
        this.p = p;
    }

    public void post(String name, String pwd) {
        if ("163".equalsIgnoreCase(name) && "163".equals(pwd)) {
            p.getContract().responseResult(new UserInfo("网易", "彭老师"));
        } else {
            p.getContract().responseResult(null);
        }
    }
}
