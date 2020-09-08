package com.wsy.custom.custom.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void r01(View view) {
        // 起点 被观察者
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

            }
        })
                .subscribe( // 订阅 == registerObserver
                        // 终点 一个 观察者
                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

    }

    public void r02(View view) {
        // 上游被观察者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "开始发射事件 ");
                //发射事件
                e.onNext(1);
                Log.d(TAG, "发射事件完成");
            }
        });
        // 下游观察者
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer o) {
                Log.d(TAG, "下游处理事件onNext : " + o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);

    }

    public void r04(View view) {
        // 上游被观察者
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "开始发射事件 ");//2
                //发射事件
                e.onNext(1);//3
                e.onComplete();//5
                Log.d(TAG, "发射事件完成");//7
            }
        }).subscribe(
                // 下游
                new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {//1
                        Log.d(TAG, "订阅成功");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "下游处理事件onNext : " + integer);//4
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "下游接收完成");//6
                    }
                });

    }

    public void r05(View view) {
        // 上游被观察者
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "开始发射事件 ");//2
                //发射事件
                e.onNext(1);//3
                Log.d(TAG, "发射事件完成");//7
                //
                e.onError(new IllegalAccessException("error rxJava"));
                e.onComplete();//5
                //onError/onComplete 之后，下游不再接受事件
                //onComplete 之后再 onError会导致崩溃
                //onError 之后再  onComplete 不会导致崩溃，但下游也收不到onComplete事件
//                e.onNext(2);//3
//                e.onNext(3);//3
//                e.onNext(4);//3
//                e.onNext(5);//3
            }
        }).subscribe(
                // 下游
                new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {//1
                        Log.d(TAG, "订阅成功");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "下游处理事件onNext : " + integer);//4
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError : " + e.getMessage());//4
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "下游接收完成");//6
                    }
                });
    }

    private Disposable disposable;

    public void r06(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 100; i++) {
                    e.onNext(i);
                }
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                MainActivity.this.disposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "下游处理事件onNext : " + integer);
                //切断下游，不再接收上游发射的事件，而上游还在发送事件
                MainActivity.this.disposable.dispose();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}