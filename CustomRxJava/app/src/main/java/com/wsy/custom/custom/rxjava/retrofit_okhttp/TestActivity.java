package com.wsy.custom.custom.rxjava.retrofit_okhttp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wsy.custom.custom.rxjava.R;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Retrofit + RxJava
 * 需求：
 * 1.请求服务器注册操作
 * 2.注册完成之后，更新注册UI
 * 3.马上去登录服务器操作
 * 4.登录完成之后，更新登录的UI
 */
public class TestActivity extends AppCompatActivity {

    private final String TAG = TestActivity.class.getSimpleName();

    private TextView tv_register_ui;
    private TextView tv_login_ui;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        tv_register_ui = findViewById(R.id.tv_login_ui);
        tv_login_ui = findViewById(R.id.tv_login_ui);
    }

    public void request(View view) {
        // 分开写
        /**
         * 1.请求服务器注册操作
         * 2.注册完成之后，更新注册UI
         */
//        IRequestNetwork iRequestNetwork = MyRetrofit.createRetrofit().create(IRequestNetwork.class);
        // 1.请求服务器注册操作
        MyRetrofit.createRetrofit().create(IRequestNetwork.class) // IRequestNetwork
                // IRequestNetwork.registerAction
                .registerAction(new RegisterRequest())  // Observable<RegisterResponse> 上游 被观察者 耗时操作
                .subscribeOn(Schedulers.io()) // todo 给上游分配异步线程

                .observeOn(AndroidSchedulers.mainThread()) // todo 给下游切换 主线程
                // 2.注册完成之后，更新注册UI
                .subscribe(new Consumer<RegisterResponse>() { // 下游 简化版
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        // 更新注册相关的所有UI
                        // .....
                    }
                });


        // 3.马上去登录服务器操作
        MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                .loginAction(new LoginRequest())  // Observable<LoginResponse> 上游 被观察者 耗时操作
                .subscribeOn(Schedulers.io()) // todo 给上游分配异步线程

                .observeOn(AndroidSchedulers.mainThread()) // todo 给下游切换 主线程

                // 4.登录完成之后，更新登录的UI
                .subscribe(new Consumer<LoginResponse>() { // 下游 简化版
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        // 更新登录相关的所有UI
                        // .....
                    }
                });
    }

    private ProgressDialog progressDialog;

    public void request2(View view) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在执行中...");

        /**
         * 一行代码 实现需求
         * 需求：
         *  * 1.请求服务器注册操作
         *  * 2.注册完成之后，更新注册UI
         *  * 3.马上去登录服务器操作
         *  * 4.登录完成之后，更新登录的UI
         */

        MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                .registerAction(new RegisterRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                /**
                 *  这样不能订阅，如果订阅了，就无法执行
                 *      3 马上去登录服务器操作
                 *      4.登录完成之后，更新登录的UI
                 *
                 *  所以我们要去学习一个 .doOnNext()，可以在不订阅的情况下，更新UI
                 */
                .doOnNext(new Consumer<RegisterResponse>() {
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        // todo 第三步 更新注册相关的所有UI
                        // 更新注册相关的所有UI
                        tv_register_ui.setText("xxx");
                    }
                })
                // 3.马上去登录服务器操作 -- 耗时操作
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
                    @Override
                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
                        return MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                                .loginAction(new LoginRequest());
                    }
                })
                // 4.登录完成之后，更新登录的UI
                .observeOn(AndroidSchedulers.mainThread()) // // todo 给下游切换 主线程
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // todo 第一步
                        progressDialog.show();
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        // 更新登录相关的所有UI
                        // todo 第五步 更新登录相关的所有UI
                        tv_login_ui.setText("xxxx");
                        // ...........
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        // todo 第六步
                        progressDialog.dismiss(); // 结束对话框 ，整个流程完成
                    }
                });
    }
}
