//
// Created by wang songye on 2020/8/6.
//

#include <jni.h>

int test() {
    return 123;
}

extern "C" jint Java_com_wsy_cmake_demo_MainActivity_nativeTest(JNIEnv *env, jobject thiz) {
    return test();
}