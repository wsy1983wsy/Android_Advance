package com.wsy.handler.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

/**
 * 1、Handler内存泄露测试
 * 2、为什么不能在子线程创建Handler
 * 3、textView.setText()只能在主线程执行，这句话是错误！
 * 4、new Handler()两种写法有什么区别？
 * 5、ThreadLocal用法和原理
 */
public class MainActivity extends AppCompatActivity {
    private TextView textView;
    // 4、new Handler()两种写法有什么区别？
    private Handler handler1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            startActivity(new Intent(MainActivity.this, PersonActivity.class));
            return false;
        }
    });

    // 这是谷歌备胎的api，不推荐使用
    private Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText(msg.obj.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
        test();
    }

    private void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
//                message.obj = "Net163";
//                message.what = 163;
//                handler2.sendMessage(message);
//                SystemClock.sleep(10000);
//                message.what = 1;
//                if (handler1 != null) {
                   handler1.sendMessage(message);
//                }

//                handler1.sendMessageDelayed(message, 10000);
                //不能再子线程new Handler
                new Handler();
                new TextView(MainActivity.this);
                textView.setText("hello213123");
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
        handler1.removeMessages(1);
        handler1 = null;
    }
}
