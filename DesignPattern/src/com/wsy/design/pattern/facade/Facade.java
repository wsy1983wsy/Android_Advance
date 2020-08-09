package com.wsy.design.pattern.facade;

import com.wsy.design.pattern.facade.imp.DiskCacheImpl;
import com.wsy.design.pattern.facade.imp.MemoryCacheImpl;
import com.wsy.design.pattern.facade.imp.NetorkLoaderImpl;
import com.wsy.design.pattern.facade.inter.DiskCache;
import com.wsy.design.pattern.facade.inter.MemoryCache;
import com.wsy.design.pattern.facade.inter.NetworkLoader;

public class Facade {
    private String url;
    private MemoryCache memoryCache;
    private DiskCache diskCache;
    private NetworkLoader networkLoader;

    public Facade(String url) {
        this.url = url;
        this.memoryCache = new MemoryCacheImpl();
        this.diskCache = new DiskCacheImpl();
        this.networkLoader = new NetorkLoaderImpl();
    }

    public Bitmap loader() {
        Bitmap bitmap = memoryCache.loadBitmap(url);
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = diskCache.loadBitmapFromDisk(url);
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = networkLoader.loadBitmapFromNet(url);
        return bitmap;
    }
}
