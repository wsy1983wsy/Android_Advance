package com.wsy.okhttp.chain;

public abstract class BaseTask {
    //判断当前任务节点，有没有能力执行 ，有能力就结束，不再往下传
    private boolean isTask;

    public BaseTask(boolean isTask) {
        this.isTask = isTask;
    }

    //执行下一个任务
    private BaseTask nextTask;

    public void addNextTask(BaseTask task) {
        nextTask = task;
    }

    public abstract void doAction();

    public void action() {
        if (isTask) {
            doAction();
        } else {
            if (nextTask != null) {
                nextTask.action();
            }
        }
    }
}
