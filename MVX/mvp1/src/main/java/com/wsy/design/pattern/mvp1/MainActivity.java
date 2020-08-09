package com.wsy.design.pattern.mvp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wsy.design.pattern.mvp1.model.ImageBean;
import com.wsy.design.pattern.mvp1.presenter.DownloaderPresenter;
import com.wsy.design.pattern.mvp1.utils.Constant;

public class MainActivity extends AppCompatActivity implements DownloadContract.PV {
    private ImageView imageView;
    private DownloaderPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.iv);
        presenter = new DownloaderPresenter(this);
    }

    public void down(View view) {
        ImageBean imageBean = new ImageBean(Constant.IMAGE_PATH);
        try {
            requestDownloader(imageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestDownloader(ImageBean imageBean) throws Exception {
        if (presenter != null) {
            presenter.requestDownloader(imageBean);
        }
    }

    @Override
    public void responseDownloadResult(boolean isSuccess, ImageBean imageBean) {
        if (isSuccess && imageBean != null) {
            Toast.makeText(this, "下载图片成功", Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(imageBean.getBitmap());
        } else {
            Toast.makeText(this, "下载图片失败", Toast.LENGTH_SHORT).show();
        }
    }
}
