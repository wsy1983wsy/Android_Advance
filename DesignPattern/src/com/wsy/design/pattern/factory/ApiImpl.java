package com.wsy.design.pattern.factory;

public class ApiImpl implements Api {
    @Override
    public UserInfo createUser() {
        UserInfo userInfo = new UserInfo();
        System.out.println("UserInfo:" + userInfo.toString());
        return userInfo;
    }
}
