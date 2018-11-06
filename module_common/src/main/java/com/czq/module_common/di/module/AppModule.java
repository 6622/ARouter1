package com.czq.module_common.di.module;


import com.czq.module_common.base.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    MyApplication mApplication;

    public AppModule(MyApplication application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    MyApplication providesApplication() {
        return mApplication;
    }





//    @Singleton
//    @Provides
//    Gson provideGson() {
//        return new Gson();
//    }

//    @Provides
//    @Singleton
//    SharedPreferences providesSharedPreferences(Application application) {
//        return PreferenceManager.getDefaultSharedPreferences(application);
//    }
}
