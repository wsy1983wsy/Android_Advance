package com.wsy.database.db;

public interface IBaseDao<T> {
    long insert(T entity);
}
