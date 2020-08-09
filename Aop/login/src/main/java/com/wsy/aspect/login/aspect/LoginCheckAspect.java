package com.wsy.aspect.login.aspect;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.wsy.aspect.login.LoginActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoginCheckAspect {
    private static final String TAG = "LoginCheckAspect";

    @Pointcut("execution(@com.wsy.aspect.login.annotation.LoginCheck * *(..))")
    public void methodPointCut() {

    }

    // 2、对切入点如何处理
    @Around("methodPointCut()")
    public Object joinPint(ProceedingJoinPoint joinPoint) throws Throwable {
        Context context = (Context) joinPoint.getThis();
        if (false) {
            Log.d(TAG, "检测到已登录");
            return joinPoint.proceed();
        } else {
            Log.d(TAG, "检测到未登录");
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, LoginActivity.class));
            return null;
        }
    }
}
