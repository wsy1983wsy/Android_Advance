//
// Created by wang songye on 2020/8/4.
//
#include <jni.h>

int test() {
    return 123;
}

jint Java_com_wsy_ndkhellojni_MainActivity_nativeTest(JNIEnv *env, jobject thiz) {
return test();
}