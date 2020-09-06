package com.wsy.okhttp.chain;

public class Task3  extends BaseTask {

    public Task3(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        System.out.println("Task3 任务节点 执行了");
    }

}
