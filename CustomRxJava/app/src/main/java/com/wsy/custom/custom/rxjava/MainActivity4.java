package com.wsy.custom.custom.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 合并型操作符
 */
public class MainActivity4 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * startWith 1.startWith(2)  先发射2中的事件，然后再发射1中的事件
     *
     * @param view
     */
    public void r01(View view) {
        // 上游
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // todo 2
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        })
                .startWith(Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        // todo 1
                        e.onNext(10000);
                        e.onNext(20000);
                        e.onNext(30000);
                        e.onComplete();
                    }
                }))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + integer);
                    }
                });
    }

    /**
     * concatWith  1.concatWith(2) 先执行1中的事件发射，再执行2中的事件发射
     *
     * @param view
     */
    public void r02(View view) {
        // 上游
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // todo 2
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        })
                .concatWith(Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        // todo 1
                        e.onNext(10000);
                        e.onNext(20000);
                        e.onNext(30000);
                        e.onComplete();
                    }
                }))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + integer);
                    }
                });
    }

    /**
     * concat 最多能够合并四个，按照参数顺序发射事件
     */
    public void r03(View view) {
        Observable.concat(Observable.just(1),
                Observable.just(2),
                Observable.just(3),
                Observable.just(4))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + integer);
                    }
                });
    }

    /**
     * merge 合并操作符 的特性：最多能够合并四个，并列执行 并发
     *
     * @param view
     */
    public void r04(View view) {
        // 为了体现并列执行 并发，所以要新学一个操作符(intervalRange)
        // 被观察者  start开始累计， count累计多个个数量， initDelay开始等待时间，  period 每隔多久执行， TimeUnit 时间单位
        // 从1开始，累加5个，每隔2秒钟加一
        /*Observable.intervalRange(1, 5, 1, 2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG, "accept: " + aLong); //1 2 3 4 5
            }
        });*/

        Observable observable1 = Observable.intervalRange(1, 5, 1, 2, TimeUnit.SECONDS);
        Observable observable2 = Observable.intervalRange(6, 5, 1, 2, TimeUnit.SECONDS);
        Observable observable3 = Observable.intervalRange(11, 5, 1, 2, TimeUnit.SECONDS);
        Observable.merge(observable1, observable2, observable3)
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d(TAG, "accept: " + o.toString());
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
