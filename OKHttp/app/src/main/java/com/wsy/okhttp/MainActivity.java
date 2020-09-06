package com.wsy.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static void okhttpUserAction() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://www.baidu.com").get().build();
        Call call = okHttpClient.newCall(request);
        //同步方法
//        try {
//            Response response = call.execute();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBodyStr = response.body().string();
                System.out.println("请求完成： " + responseBodyStr);
            }
        });
    }

    public static void main(String[] args) {
        okhttpUserAction();
    }
}