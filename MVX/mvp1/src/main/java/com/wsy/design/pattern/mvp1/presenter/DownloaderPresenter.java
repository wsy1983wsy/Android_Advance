package com.wsy.design.pattern.mvp1.presenter;

import android.os.SystemClock;

import com.wsy.design.pattern.mvp1.DownloadContract;
import com.wsy.design.pattern.mvp1.MainActivity;
import com.wsy.design.pattern.mvp1.engine.DownloadEngine;
import com.wsy.design.pattern.mvp1.model.ImageBean;

public class DownloaderPresenter implements DownloadContract.PV {
    private MainActivity view;
    private DownloadEngine model;

    public DownloaderPresenter(MainActivity view) {
        this.view = view;
        model = new DownloadEngine(this);
    }

    @Override
    public void requestDownloader(ImageBean imageBean) throws Exception {
        //接收到View层的命令，取完成某个需求（可以自己完成，也可以是别人去做)
        try {
            model.requestDownloader(imageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //同样会导致内存泄露，MainActivity内存泄露
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(50000);
            }
        }).start();
    }

    @Override
    public void responseDownloadResult(final boolean isSuccess, final ImageBean imageBean) {
        //将完成的结果告知View层（刷新UI）
        view.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.responseDownloadResult(isSuccess, imageBean);
            }
        });
    }
}
