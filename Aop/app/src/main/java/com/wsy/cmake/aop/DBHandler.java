package com.wsy.cmake.aop;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DBHandler implements InvocationHandler {
    DbOperation dbOperation;

    public DBHandler(DbOperation dbOperation) {
        this.dbOperation = dbOperation;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if (dbOperation != null) {
            Log.d("DBHelper", "操作数据库之前,开始执行备份");
            dbOperation.save();
            Log.d("DBHelper", "操作数据库之前,执行备份完毕");
            return method.invoke(dbOperation, args);
        }
        return null;
    }
}
