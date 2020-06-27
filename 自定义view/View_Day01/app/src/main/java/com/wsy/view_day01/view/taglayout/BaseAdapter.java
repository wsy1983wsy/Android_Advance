package com.wsy.view_day01.view.taglayout;

import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAdapter {
    /**
     * 有多少个条目
     *
     * @return
     */
    public abstract int getCount();

    /**
     * 获取指定位置出的view
     *
     * @param position
     * @param parent
     * @return
     */
    public abstract View getView(int position, ViewGroup parent);
}
