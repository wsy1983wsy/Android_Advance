package com.wsy.design.pattern.facade;

import com.wsy.design.pattern.facade.imp.DiskCacheImpl;
import com.wsy.design.pattern.facade.imp.MemoryCacheImpl;
import com.wsy.design.pattern.facade.imp.NetorkLoaderImpl;

import java.io.InputStream;

public class FacadeMain {
    public static final String url = "http://aaa.cc.com/aaa.jpg";

    public static void main(String[] args) {
        //常规写法
        /*MemoryCache memoryCache = new MemoryCacheImpl();
        Bitmap bitmap = memoryCache.loadBitmap(url);
        if (bitmap != null) {
            return;
        }
        DiskCache diskCache = new DiskCacheImpl();
        bitmap = diskCache.loadBitmapFromDisk(url);
        if (bitmap != null) {
            return;
        }
        NetworkLoader networkLoader = new NetorkLoaderImpl();
        InputStream inputStream = networkLoader.loadBitmapFromNet(url);*/

        Facade facade = new Facade(url);
        Bitmap bitmap = facade.loader();

    }
}
