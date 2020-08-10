package com.wsy.system.customhandler.core;

public class Handler {
    private Looper mLooper;
    private MessageQueue mQueue;

    public Handler() {
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException("Can't create handler inside thread " + Thread.currentThread() + " that has not called Looper.prepare()");
        }
        mQueue = mLooper.mQueue;
    }

    // 给开发者提供的开放API，用于重写和回调监听
    public void handleMessage(Message msg) {

    }

    public void sendMessage(Message message) {
        enqueueMessage(message);
    }

    private void enqueueMessage(Message message) {
        message.target = this;
        // 使用mQueue将消息放入
        mQueue.enqueueMessage(message);
    }

    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }
}
