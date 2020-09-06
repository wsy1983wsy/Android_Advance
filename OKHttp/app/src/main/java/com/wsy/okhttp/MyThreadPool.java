package com.wsy.okhttp;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
    public static void main(String[] args) {
        //不使用线程池
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("new Thread");
            }
        }).start();
        //Java 1.5 线程池 复用（线程池负责线程的创建，复用)
        //类关系
//        Executor
//        ----ExecutorService
//            ---- AbstractExecutorService
//                 ----ThreadPoolExecutor
        /**
         * 参数一： corePoolSize 核心线程数
         * 参数二： maximumPoolSize 线程池非核心线程数，线程池规定大小     maximumPoolSize 必须大于corePoolSize，否则崩溃
         * 参数三，四： 时间数值KeepAlive，单位：时分秒
         *          正在运行的Runnable个数 > corePoolSize  参数三，四才会起作用
         *          作用： 如果正在运行的Runnable执行完毕后，闲置keepAlive （60s），如果过了闲置60s，会回收掉Runnable任务，如果在闲置时60s内，复用此线程Runnable
         * 参数五：workQueue 队列，会把超出的任务加入到队列中，缓存起来
         *
         */
//        Executor executorService = new ThreadPoolExecutor(1,
//                1,
//                60, TimeUnit.SECONDS,
//                new LinkedBlockingDeque<Runnable>());

//        Executor executorService = new ThreadPoolExecutor(5,
//                10,
//                60, TimeUnit.SECONDS,
//                new LinkedBlockingDeque<Runnable>());
        Executor executorService = new ThreadPoolExecutor(0,
                Integer.MAX_VALUE,
                60, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("Test thread");
                thread.setDaemon(false);
                return thread;
            }
        });
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("当前线程，执行耗时任务，线程：" + Thread.currentThread().getName());
                    } catch (Throwable e) {
                    }
                }
            });
        }
    }
}
