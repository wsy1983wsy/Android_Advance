package com.wsy.custom.custom.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * 异常操作符
 */
public class MainActivity5 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * onErrorReturn异常操作符：1.能够接收e.onError，  2.如果接收到异常，会中断上游后续发射的所有事件
     * error
     *
     * @param view
     */
    public void r01(View view) {
        // 上游 被观察者
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 100; i++) {
                    if (i == 5) {
                        // RxJava中是不标准的
                        // throw new IllegalAccessError("我要报错了");

                        // RxJava标准的
                        e.onError(new IllegalAccessError("我要报错了")); // 发射异常事件
                    }
                    e.onNext(i);
                }
                e.onComplete();
            }
        })
                // 在上游 和 下游之间 添加异常操作符
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        // 处理，纪录，异常，通知给下一层
                        Log.d(TAG, "onErrorReturn: " + throwable.getMessage());
                        return 400; // 400代表有错误，给下一层，目前 下游 观察者
                    }
                })
                .subscribe(new Observer<Integer>() { // 完整版 下游 观察者
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    // 如果使用了 异常操作符 onNext: 400
                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer); // 400
                    }

                    // 如果不使用 异常操作符 onError
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    /**
     * onErrorResumeNext 异常操作符：1.能够接收e.onError，
     * onErrorResumeNext 将异常转为Observable
     * onErrorReturn可以返回标识400    对比   onErrorResumeNext可以返回被观察者（被观察者可以再次发射多次事件给 下游）
     * error
     *
     * @param view
     */
    public void r02(View view) {
        // 上游 被观察者
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 100; i++) {
                    if (i == 5) {
                        // RxJava中是不标准的
                        // throw new IllegalAccessError("我要报错了");

                        // RxJava标准的
                        e.onError(new IllegalAccessError("我要报错了")); // 发射异常事件
                    }
                    e.onNext(i);
                }
                e.onComplete();
            }
        })
                // 在上游 和 下游之间 添加异常操作符
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<Integer>() {
                            @Override
                            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                                e.onNext(400);
                                e.onNext(400);
                                e.onNext(400);
                                e.onNext(400);
                                e.onComplete();
                            }
                        });
                    }
                })
                .subscribe(new Observer<Integer>() { // 完整版 下游 观察者
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    // 如果使用了 异常操作符 onNext: 400
                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer); // 400
                    }

                    // 如果不使用 异常操作符 onError
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    /**
     * exception
     * onExceptionResumeNext 操作符，能在发生异常的时候，扭转乾坤，（这种错误一定是可以接受的，才这样使用）
     * 慎用：自己去考虑，是否该使用
     *
     * @param view
     */
    public void r03(View view) {
        // 上游
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 100; i++) {
                    if (i == 5) {
                       /* throw new 其他Exception("错了");
                        throw new IllegalAccessException("错了");*/
//                        throw new Exception("错了");
                        e.onError(new IllegalAccessException("错了")); // 异常事件
                    } else {
                        e.onNext(i);
                    }

                }
                e.onComplete(); // 一定要最后执行

                /**
                 * e.onComplete();
                 * e.onError
                 * 会报错
                 */
            }
        })
                .onExceptionResumeNext(new ObservableSource<Integer>() {
                    @Override
                    public void subscribe(Observer<? super Integer> observer) {
                        observer.onNext(400);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "onNext: " + integer); // 400
            }
        });
    }

    /**
     * retry 重试操作符 异常处理操作符中
     *
     * @param view
     */
    public void r04(View view) {
        // 上游
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 100; i++) {
                    if (i == 5) {
                       /* throw new 其他Exception("错了");
                        throw new IllegalAccessException("错了");*/
//                        throw new Exception("错了");
                        e.onError(new IllegalAccessException("错了")); // 异常事件
                    } else {
                        e.onNext(i);
                    }

                }
                e.onComplete(); // 一定要最后执行

                /**
                 * e.onComplete();
                 * e.onError
                 * 会报错
                 */
            }
        })
                .retry(3, new Predicate<Throwable>() {
                    @Override
                    public boolean test(Throwable throwable) throws Exception {
                        Log.d(TAG, "retry: " + throwable.getMessage());
                        return true;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "onNext: " + integer); // 400
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        Log.d(TAG, "出错了：" + e.getMessage());
                    }
                });
    }

    /**
     * zip 合并操作符 合并的被观察者发射的事件，需要对应, 没有对应的丢弃
     * 需求：考试 课程 == 分数
     *
     * @param view
     */
    public void r05(View view) {

        // 课程 被观察者
        Observable observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("英语"); // String
                e.onNext("数学");
                e.onNext("政治");
                e.onNext("物理");  // 被忽略
                e.onComplete();
            }
        });

        // 分数 被观察者
        Observable observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(85); // Integer
                e.onNext(90);
                e.onNext(96);
                e.onComplete();
            }
        });
        Observable.zip(observable1, observable2, new BiFunction<String, Integer, StringBuffer>() {
            @Override
            public StringBuffer apply(String str, Integer score) throws Exception {
                return new StringBuffer().append("课程: ").append(str).append(" == ").append(score + "");
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(TAG, "accept: " + o.toString());
            }
        });
    }
}
