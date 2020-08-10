package com.wsy.system.customhandler.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {
    BlockingQueue<Message> blockingDeque = new ArrayBlockingQueue<Message>(50);

    // 将Message消息对象存入阻塞队列中
    public void enqueueMessage(Message message) {
        try {
            blockingDeque.put(message);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public Message next() {
        try {
            return blockingDeque.take();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
