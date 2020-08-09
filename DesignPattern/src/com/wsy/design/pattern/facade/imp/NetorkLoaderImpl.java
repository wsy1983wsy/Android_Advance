package com.wsy.design.pattern.facade.imp;

import com.wsy.design.pattern.facade.Bitmap;
import com.wsy.design.pattern.facade.inter.NetworkLoader;


public class NetorkLoaderImpl implements NetworkLoader {
    @Override
    public Bitmap loadBitmapFromNet(String url) {
        System.out.println("从网络载图片： " + url);
        return null;
    }
}
