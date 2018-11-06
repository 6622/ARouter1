package com.czq.module_common.di.component;


import com.czq.module_common.di.module.AppModule;
import com.czq.module_common.base.MyApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MyApplication application);
    //说明将MyApplication开放给其他Component使用
    MyApplication providerApp();
}

