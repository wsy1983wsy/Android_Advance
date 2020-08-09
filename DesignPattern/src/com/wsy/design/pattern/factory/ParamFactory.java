package com.wsy.design.pattern.factory;

public class ParamFactory {
    public static Api createApi(int type) {
        switch (type) {
            case 1:
                return new ApiImpl_A();
            case 2:
                return new ApiImpl_B();
        }
        return null;
    }
}
