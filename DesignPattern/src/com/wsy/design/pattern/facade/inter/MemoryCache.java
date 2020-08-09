package com.wsy.design.pattern.facade.inter;

import com.wsy.design.pattern.facade.Bitmap;

public interface MemoryCache {
    //从内存中加载一张图片
    Bitmap loadBitmap(String url);
}
