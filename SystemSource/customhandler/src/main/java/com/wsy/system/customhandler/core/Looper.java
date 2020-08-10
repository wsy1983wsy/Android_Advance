package com.wsy.system.customhandler.core;

public class Looper {
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    public MessageQueue mQueue;

    private Looper() {
        mQueue = new MessageQueue();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one looper may be created per thread");
        }
        //初始化时赋值
        sThreadLocal.set(new Looper());
    }

    //轮询，提取消息
    public static void loop() {
        // 从全局ThreadLocalMap中获取唯一looper对象
        Looper me = myLooper();
        // 从Looper对象中获取全局唯一消息队列MessageQueue对象
        MessageQueue queue = me.mQueue;

        Message resultMessage;
        while (true) {
            //从消息队列中取消息
            Message msg = queue.next();
            if (msg != null) {
                if (msg.target != null) {
                    msg.target.dispatchMessage(msg);
                }
            }
        }
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }
}
