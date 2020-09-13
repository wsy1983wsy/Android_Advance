package com.wsy.custom.custom.rxjava.custom.create;

// todo 被观察者 上游
public class Observable<T> { // 类声明的泛型T  Int

    ObservableOnSubscribe<T> source;

    private Observable(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    // 静态方法声明的<T>泛型        ObservableOnSubscribe<T>==静态方法声明的<T>泛型
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) { // int
        return new Observable<T>(source); // 静态方法声明的<T>泛型 int
    }

    public static <T> Observable<T> just(final T t) {
        return new Observable<T>(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(Observer<? super T> observableEmitter) {
                observableEmitter.onNext(t);
                observableEmitter.onComplete();
            }
        });
    }

    public static <T> Observable<T> just(final T... t) {
        return new Observable<T>(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(Observer<? super T> observableEmitter) {
                for (T t1 : t) {
                    observableEmitter.onNext(t1);
                }
                observableEmitter.onComplete();
            }
        });
    }

    // 2个参数
    public static <T> Observable<T> just(final T t, final T t2) {
        return new Observable<T>(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(Observer<? super T> observableEmitter) {
                // 发射用户传递的参数数据 去发射事件
                observableEmitter.onNext(t);
                observableEmitter.onNext(t2);

                // 调用完毕
                observableEmitter.onComplete(); // 发射完毕事件
            }
        });
    }

    // 3个参数
    public static <T> Observable<T> just(final T t, final T t2, final T t3) {
        return new Observable<T>(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(Observer<? super T> observableEmitter) {
                // 发射用户传递的参数数据 去发射事件
                observableEmitter.onNext(t);
                observableEmitter.onNext(t2);
                observableEmitter.onNext(t3);

                // 调用完毕
                observableEmitter.onComplete(); // 发射完毕事件
            }
        });
    }

    // 4个参数的
    public static <T> Observable<T> just(final T t, final T t2, final T t3, final T t4) {
        return new Observable<T>(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(Observer<? super T> observableEmitter) {
                // 发射用户传递的参数数据 去发射事件
                observableEmitter.onNext(t);
                observableEmitter.onNext(t2);
                observableEmitter.onNext(t3);
                observableEmitter.onNext(t4);

                // 调用完毕
                observableEmitter.onComplete(); // 发射完毕事件
            }
        });
    }

    // new Observable<T>(source).subscribe(Observer<Int>)
    public void subscribe(Observer<T> observer) {
        observer.onSubscribe();
        source.subscribe(observer);
    }
}
