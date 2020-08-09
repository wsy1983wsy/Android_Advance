package com.wsy.design.pattern.facade.inter;

import com.wsy.design.pattern.facade.Bitmap;

import java.io.InputStream;

public interface NetworkLoader {
    Bitmap loadBitmapFromNet(String url);
}
