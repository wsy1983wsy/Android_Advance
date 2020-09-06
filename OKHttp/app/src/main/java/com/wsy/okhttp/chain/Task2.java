package com.wsy.okhttp.chain;

public class Task2  extends BaseTask {

    public Task2(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        System.out.println("Task2 任务节点 执行了");
    }
}
