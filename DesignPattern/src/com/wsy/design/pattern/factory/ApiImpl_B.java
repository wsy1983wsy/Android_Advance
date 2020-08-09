package com.wsy.design.pattern.factory;

public class ApiImpl_B implements Api {
    @Override
    public UserInfo createUser() {
        UserInfo userInfo = new UserInfo(20, "Hello");
        System.out.println("UserInfo:" + userInfo.toString());
        return userInfo;
    }
}
