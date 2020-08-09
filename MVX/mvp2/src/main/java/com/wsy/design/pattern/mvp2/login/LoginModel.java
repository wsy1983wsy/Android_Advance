package com.wsy.design.pattern.mvp2.login;

import com.wsy.design.pattern.mvp2.base.BaseModel;
import com.wsy.design.pattern.mvp2.model.UserInfo;

public class LoginModel extends BaseModel<LoginPresenter, LoginContract.Model> {
    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void executeLogin(String name, String pwd) throws Exception {
                if ("163".equalsIgnoreCase(name) && "163".equals(pwd)) {
                    p.getContract().responseResult(new UserInfo("网易", "彭老师"));
                } else {
                    p.getContract().responseResult(null);
                }
            }
        };
    }
}
