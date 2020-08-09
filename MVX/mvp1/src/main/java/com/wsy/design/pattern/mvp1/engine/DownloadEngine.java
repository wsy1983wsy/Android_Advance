package com.wsy.design.pattern.mvp1.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.wsy.design.pattern.mvp1.DownloadContract;
import com.wsy.design.pattern.mvp1.model.ImageBean;
import com.wsy.design.pattern.mvp1.utils.Constant;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadEngine implements DownloadContract.M {
    private DownloadContract.PV presenter;

    public DownloadEngine(DownloadContract.PV presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestDownloader(ImageBean imageBean) {
        //P层让我做这个操作
        new Thread(new Downloader(imageBean)).start();
    }

    final class Downloader implements Runnable {
        ImageBean imageBean;

        public Downloader(ImageBean imageBean) {
            this.imageBean = imageBean;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(imageBean.getUrl());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");

                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    showUi(Constant.SUCCESS, bitmap);
                } else {
                    showUi(Constant.ERROR, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showUi(Constant.ERROR, null);
            }
        }

        private void showUi(int resultCode, Bitmap bitmap) {
            imageBean.setBitmap(bitmap);
            presenter.responseDownloadResult(resultCode == Constant.SUCCESS, imageBean);
        }
    }
}
