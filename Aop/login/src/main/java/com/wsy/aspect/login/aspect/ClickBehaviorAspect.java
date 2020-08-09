package com.wsy.aspect.login.aspect;

import android.util.Log;

import com.wsy.aspect.login.annotation.ClickBehavior;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 定义一个切面类
 */
@Aspect
public class ClickBehaviorAspect {
    private static final String TAG = "ClickBehaviorAspect";

    // 1、应用中用到了哪些注解，放到当前的切入点进行处理（找到需要处理的切入点）
    // execution，以方法执行时作为切点，触发Aspect类
    // * *(..)) 可以处理ClickBehavior这个类所有的方法
    @Pointcut("execution(@com.wsy.aspect.login.annotation.ClickBehavior * *(..))")
    public void methodPointCut() {

    }

    // 2、对切入点如何处理
    @Around("methodPointCut()")
    public Object joinPint(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法所属的类名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        // 获取方法名
        String methodName = methodSignature.getName();
        // 获取方法的注释值(需要统计用户的行为)
        String funName = methodSignature.getMethod().getAnnotation(ClickBehavior.class).value();
        //统计方法的执行时间，统计用户点击某功能行为
        long begin = System.currentTimeMillis();
        Log.d(TAG, "ClickBehavior start。。。。");
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        Log.d(TAG, "ClickBehavior end。。。。");
        Log.d(TAG, String.format("统计了：%s功能，在%s类的%s方法，用时%d ms", funName, className, methodName,  (begin - end)));
        return result;
    }
}
