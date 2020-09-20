package com.wsy.customdagger;

import dagger.Module;
import dagger.Provides;

@Module
public class StudentModule {
    @Provides
    public Student getStudent() {
        return new Student();
    }
}
