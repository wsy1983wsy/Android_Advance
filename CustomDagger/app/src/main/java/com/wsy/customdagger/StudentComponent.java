package com.wsy.customdagger;

import dagger.Component;

@Component(modules = {StudentModule.class})
public interface StudentComponent {
    void injectMainActivity(MainActivity activity);
}
