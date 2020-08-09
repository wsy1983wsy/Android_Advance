package com.wsy.design.pattern.mvx;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wsy.design.pattern.mvx.bean.ImageBean;

public class MainActivity extends AppCompatActivity implements Callback {

    private ImageView imageView;
    private final static String PATH = "http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg";
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case ImageDownloader.SUCCESS: // 成功
                    imageView.setImageBitmap((Bitmap) msg.obj);
                    break;

                case ImageDownloader.ERROR: // 失败
                    Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.iv_image);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(50000);
            }
        }).start();
    }

    public void getImage(View view) {
        ImageBean imageBean = new ImageBean();
        imageBean.setUrl(PATH);
        new ImageDownloader().down(this, imageBean);
    }

    @Override
    public void callback(int resultCode, ImageBean imageBean) {
        Message message = handler.obtainMessage(resultCode);
        message.obj = imageBean.getBitmap();
        handler.sendMessageDelayed(message, 500);
    }
}