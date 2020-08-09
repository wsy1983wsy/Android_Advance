package com.wsy.cmake.aop;

public interface DbOperation {
    void insert();

    void delete();

    void update();

    //备份
    void save();
}
