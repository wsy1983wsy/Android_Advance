package com.wsy.design.pattern.facade.inter;

import com.wsy.design.pattern.facade.Bitmap;

public interface DiskCache {
    Bitmap loadBitmapFromDisk(String url);
}
