package com.wsy.okhttp.chain;

public class Task4 extends BaseTask {

    public Task4(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        System.out.println("Task4 任务节点 执行了");
    }

}
