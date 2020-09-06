package com.wsy.okhttp.chain2;

import java.util.ArrayList;
import java.util.List;

public class ChainManager implements IBaseTask {
    private List<IBaseTask> iBaseTaskList;

    public ChainManager() {
        iBaseTaskList = new ArrayList<>();
    }

    private int index;

    public void addTask(IBaseTask baseTask) {
        iBaseTaskList.add(baseTask);
    }

    @Override
    public void doRunAction(String isTask, IBaseTask nextTask) {
        if (iBaseTaskList.isEmpty()) {
            return;
        }
        if (index == iBaseTaskList.size() || index > iBaseTaskList.size()) {
            return;
        }
        IBaseTask iBaseTaskResult = iBaseTaskList.get(index);
        index++;
        // iBaseTaskResult本质==Task1，   iBaseTaskResult本质==Task2      iBaseTaskResult本质==Task3
        iBaseTaskResult.doRunAction(isTask, nextTask);

    }
}
