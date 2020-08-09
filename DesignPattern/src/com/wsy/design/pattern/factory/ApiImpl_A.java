package com.wsy.design.pattern.factory;

public class ApiImpl_A implements Api {
    @Override
    public UserInfo createUser() {
        UserInfo userInfo = new UserInfo(0, "Hello");
        System.out.println("UserInfo:" + userInfo.toString());
        return userInfo;
    }
}
