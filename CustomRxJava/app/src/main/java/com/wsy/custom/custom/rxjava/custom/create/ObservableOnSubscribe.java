package com.wsy.custom.custom.rxjava.custom.create;

public interface ObservableOnSubscribe<T> { // T == String

    public void subscribe(Observer<? super T> observableEmitter); // Observer<String>

}
