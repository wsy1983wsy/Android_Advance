package com.wsy.custom.okhttp.okhttp.chain;

import com.wsy.custom.okhttp.okhttp.Response2;

import java.io.IOException;

public interface Interceptor2 {

    Response2 doNext(Chain2 chain2) throws IOException;

}
