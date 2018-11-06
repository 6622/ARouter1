package com.czq.module_login.di.component;

import com.czq.module_common.di.component.AppComponent;
import com.czq.module_common.di.scope.ActivityScope;
import com.czq.module_login.di.module.LoginModule;
import com.czq.module_login.view.activity.LoginActivity;
import com.czq.module_login.view.inter.ILoginAView;

import dagger.Component;

//@Singleton
@ActivityScope
@Component(modules = {LoginModule.class},dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}

