package com.wsy.custom.custom.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 条件型操作符
 */
public class MainActivity3 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * all
     *
     * @param view
     */
    public void r01(View view) {
        String v1 = "1";
        String v2 = "2";
        String v3 = "3";
        String v4 = "cc";
        if ("cc".equals(v1) || "cc".equals(v2) || "cc".equals(v3) || "cc".equals(v4)) {
            Log.d(TAG, "r01: " + true);
        } else {
            Log.d(TAG, "r01: " + false);
        }

        Observable.just(v1, v2, v3, v4)
                .all(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return !"cc".equals(s);
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG, "r01: " + aBoolean);
                    }
                });
    }

    /**
     * contains
     *
     * @param view
     */
    public void r02(View view) {
        Observable.just("javaSE", "javaEE", "javaME", "Android")
                .contains("Android")
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG, "r02: " + aBoolean);
                    }
                });
    }

    /**
     * any
     */
    public void r03(View view) {
        Observable.just("javaSE", "javaEE", "javaME", "Android")
                .any(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return "Android".equals(s);
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG, "r02: " + aBoolean);
                    }
                });
    }

    /**
     * isEmpty
     *
     * @param view
     */
    public void r04(View view) {
        Observable.just("javaSE", "javaEE", "javaME", "Android")
                .isEmpty()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG, "r02: " + aBoolean);
                    }
                });
    }

    public void r05(View view) {

    }
}
