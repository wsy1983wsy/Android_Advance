package com.wsy.injectlayout;

import android.util.Log;
import android.view.View;

import com.wsy.injectlayout.annotation.BindView;
import com.wsy.injectlayout.annotation.ContentView;
import com.wsy.injectlayout.annotation.OnClick;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InjectTool {
    private static final String TAG = InjectTool.class.getSimpleName();

    public static void inject(Object object) {
        injectContentView(object);
        injectBindView(object);
        injectOnclick(object);
    }

    private static void injectBindView(Object object) {
        Class<?> mainActivityClass = object.getClass();
        Field[] fields = mainActivityClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView == null) {
                continue;
            }
            int viewId = bindView.value();

            try {
                Method findViewByIdMethod = mainActivityClass.getMethod("findViewById", int.class);
                View view = (View) findViewByIdMethod.invoke(object, viewId);
                field.set(object, view);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }


    private static void injectOnclick(final Object object) {
        Class<?> mainActivityClass = object.getClass();
        Method[] methods = mainActivityClass.getMethods();
        for (final Method method : methods) {
            OnClick onClick = method.getAnnotation(OnClick.class);
            if (onClick == null) {
                continue;
            }
            int viewId = onClick.value();
            try {
                Method findViewByIdMethod = mainActivityClass.getMethod("findViewById", int.class);
                View targetView = (View) findViewByIdMethod.invoke(object, viewId);
                if (targetView == null) {
                    continue;
                }
                targetView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            method.invoke(object, view);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectContentView(Object object) {
        Class<?> mainActivityClass = object.getClass();
        ContentView contentView = mainActivityClass.getAnnotation(ContentView.class);
        if (null == contentView) {
            Log.d(TAG, "contentView is null");
            return;
        }
        int layoutId = contentView.value();
        try {
            Method setContentViewMethod = mainActivityClass.getMethod("setContentView", int.class);
            setContentViewMethod.invoke(object, layoutId);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}