package com.wsy.design.pattern.factory;

public class SimpleFactory {
    public static Api createApi() {
        return new ApiImpl();
    }
}
