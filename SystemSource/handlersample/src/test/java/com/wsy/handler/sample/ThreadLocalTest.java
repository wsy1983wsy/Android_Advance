package com.wsy.handler.sample;

import androidx.annotation.Nullable;

import org.junit.Test;

public class ThreadLocalTest {
    @Test
    public void test() {
        final ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
            @Nullable
            @Override
            protected String initialValue() {
                return "Hello world";
            }
        };
        System.out.println("主线程: " + threadLocal.get());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread-0: " + threadLocal.get());
                System.out.println("set value");
                threadLocal.set("你好，世界");
                System.out.println("thread-0: " + threadLocal.get());

                //使用 完成建议remove，避免大量无意义的内存占用
                threadLocal.remove();
            }
        });
        thread.start();
    }
}
