package com.wsy.okhttp.chain;

import android.widget.BaseAdapter;

public class Task1 extends BaseTask {

    public Task1(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        System.out.println("Task1 任务节点 执行了");
    }
}
