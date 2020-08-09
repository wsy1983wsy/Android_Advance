package com.wsy.design.pattern.facade.imp;

import com.wsy.design.pattern.facade.Bitmap;
import com.wsy.design.pattern.facade.inter.DiskCache;

public class DiskCacheImpl implements DiskCache {
    @Override
    public Bitmap loadBitmapFromDisk(String url) {
        System.out.println("从硬盘中加载图片： " + url);
        return null;
    }
}
