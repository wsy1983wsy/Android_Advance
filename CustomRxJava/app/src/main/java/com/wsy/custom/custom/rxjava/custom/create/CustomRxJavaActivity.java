package com.wsy.custom.custom.rxjava.custom.create;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.wsy.custom.custom.rxjava.R;

public class CustomRxJavaActivity extends Activity {

    public static final String TAG = "CustomRxJavaActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_rxjava);
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(Observer<? super Integer> observableEmitter) {
                observableEmitter.onNext(1);
                observableEmitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe() {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer item) {
                Log.d(TAG, "onNext : " + item);

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError : " + e.getMessage());

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");

            }
        });
    }
}
