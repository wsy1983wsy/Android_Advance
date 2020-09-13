package com.wsy.custom.custom.rxjava.custom.create;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.wsy.custom.custom.rxjava.R;

public class CustomRxJavaActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_rxjava);
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(Observer<Integer> observableEmitter) {

            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(Integer item) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
