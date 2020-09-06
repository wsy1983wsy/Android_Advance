package com.wsy.custom.okhttp.okhttp.chain;

import com.wsy.custom.okhttp.okhttp.Request2;
import com.wsy.custom.okhttp.okhttp.RequestBody2;
import com.wsy.custom.okhttp.okhttp.Response2;
import com.wsy.custom.okhttp.okhttp.SocketRequestServer;

import java.io.IOException;
import java.util.Map;

/**
 * 请求头拦截器处理
 */
public class RequestHeaderInterceptor implements Interceptor2 {

    @Override
    public Response2 doNext(Chain2 chain2) throws IOException {

        // 拼接请求头之 请求集
        ChainManager manager = (ChainManager) chain2;
        Request2 request2 = manager.getRequest();

        Map<String, String> mHeaderList = request2.getmHeaderList();

        // get post  hostName    Host: restapi.amap.com
        mHeaderList.put("Host", new SocketRequestServer().getHost(manager.getRequest()));

        if ("POST".equalsIgnoreCase(request2.getRequestMethod())) {
            // 请求体   type lan
            /**
             * Content-Length: 48
             * Content-Type: application/x-www-form-urlencoded
             */
            mHeaderList.put("Content-Length", request2.getRequestBody2().getBody().length()+"");
            mHeaderList.put("Content-Type", RequestBody2.TYPE);
        }

        return chain2.getResponse(request2); // 执行下一个拦截器（任务节点）
    }
}
