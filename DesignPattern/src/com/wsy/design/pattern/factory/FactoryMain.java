package com.wsy.design.pattern.factory;
public class FactoryMain {
    public static void main(String[] args) {
        // write your code here
        //常规方法
        Api api = new ApiImpl();
        UserInfo userInfo = api.createUser();

        // 简单工厂，降低了模块间的耦合度
        Api simpleApi = SimpleFactory.createApi();
        UserInfo userInfo1 = simpleApi.createUser();

        //根据参数，生成不同的Api
        Api simpleA = ParamFactory.createApi(1);
        userInfo1 = simpleA.createUser();

        Api simpleB = ParamFactory.createApi(2);
        userInfo1 = simpleB.createUser();

        //根据配置文件
    }
}
