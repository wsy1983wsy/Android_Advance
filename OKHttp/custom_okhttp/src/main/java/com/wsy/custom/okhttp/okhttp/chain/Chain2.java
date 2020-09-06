package com.wsy.custom.okhttp.okhttp.chain;

import com.wsy.custom.okhttp.okhttp.Request2;
import com.wsy.custom.okhttp.okhttp.Response2;

import java.io.IOException;

public interface Chain2 {

    Request2 getRequest();

    Response2 getResponse(Request2 request2) throws IOException;


}
