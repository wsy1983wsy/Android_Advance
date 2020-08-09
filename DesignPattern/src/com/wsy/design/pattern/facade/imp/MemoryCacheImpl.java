package com.wsy.design.pattern.facade.imp;

import com.wsy.design.pattern.facade.Bitmap;
import com.wsy.design.pattern.facade.inter.MemoryCache;

public class MemoryCacheImpl implements MemoryCache {

    @Override
    public Bitmap loadBitmap(String url) {
        System.out.println("从内存中加载图片： " + url);
        return null;
    }
}
