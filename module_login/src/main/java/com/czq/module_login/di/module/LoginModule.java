package com.czq.module_login.di.module;




import com.czq.module_common.di.scope.ActivityScope;
import com.czq.module_common.post.LoginFastPost;
import com.czq.module_login.view.inter.ILoginAView;
import com.google.gson.Gson;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    ILoginAView iLoginAView;

    public LoginModule(ILoginAView iLoginAView) {
        this.iLoginAView = iLoginAView;
    }



//@Singleton

//  @ActivityScope
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    LoginFastPost provideLoginFastPost() {
        return new LoginFastPost();
    }

    @ActivityScope
    @Provides
    ILoginAView providePresenter() {
        return this.iLoginAView;
    }

}
