package com.wsy.okhttp.chain2;

public class Task3 implements IBaseTask {

    @Override
    public void doRunAction(String isTask, IBaseTask nextTask) {
        if ("OK".equals(isTask)) {
            System.out.println("拦截器 任务节点三 处理了...");
            return;
        } else {
            // 继续执行下一个链条的任务节点  ChainManager.doRunAction("ok", ChainManager)
            // ChainManager.doRunAction
            nextTask.doRunAction(isTask, nextTask);
        }
    }
}
