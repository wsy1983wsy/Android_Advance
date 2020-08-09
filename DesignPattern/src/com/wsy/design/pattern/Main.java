package com.wsy.design.pattern;

import com.wsy.design.pattern.factory.Api;
import com.wsy.design.pattern.factory.ApiImpl;
import com.wsy.design.pattern.factory.SimpleFactory;
import com.wsy.design.pattern.factory.UserInfo;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //常规方法
        Api api = new ApiImpl();
        UserInfo userInfo = api.createUser();

        // 简单工厂，降低了模块间的耦合度
        Api simpleApi  = SimpleFactory.createApi();
        UserInfo userInfo1 = simpleApi.createUser();
    }
}
