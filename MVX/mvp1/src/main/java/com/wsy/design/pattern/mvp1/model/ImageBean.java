package com.wsy.design.pattern.mvp1.model;

import android.graphics.Bitmap;

public class ImageBean {
    private String url;
    // 结果返回bitmap对象
    private Bitmap bitmap;

    public ImageBean() {
        this("");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ImageBean(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "url='" + url + '\'' +
                ", bitmap=" + bitmap +
                '}';
    }
}
