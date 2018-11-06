package com.czq.module_login.view.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.czq.module_common.base.MyApplication;
import com.czq.module_common.log.LoggerTool;
import com.czq.module_common.network.retrofitrxok.NetworkTool;
import com.czq.module_common.post.LoginFastPost;
import com.czq.module_common.tool.authority.AuthorityTool;
import com.czq.module_common.tool.authority.PermissionsCallBack;
import com.czq.module_common.tool.router.MyARouter;
import com.czq.module_common.tool.router.RouterTool;
import com.czq.module_login.R;
import com.czq.module_login.R2;
import com.czq.module_login.di.component.DaggerLoginComponent;
import com.czq.module_login.di.module.LoginModule;
import com.czq.module_login.presenter.impl.LoginAPresenterImpl;
import com.czq.module_login.view.inter.ILoginAView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;
import io.reactivex.disposables.Disposable;


@Route(path = MyARouter.LoginActivity)
public class LoginActivity extends AppCompatActivity implements PermissionsCallBack, ILoginAView {
    @Inject
    MyApplication myApplication;
    @Inject
    Gson mGson1;

    @Inject
    Gson mGson2;

    @BindView(R2.id.button)
    Button button;
    @BindView(R2.id.button1)
    Button button1;
    private NetworkTool networkTool;

    //    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    public List<Disposable> disposables = new ArrayList<Disposable>();
    private AuthorityTool authorityTool;
    private static final String[] PERMISSION = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

//    private ILoginAPresenter mILoginAPresenter;
    @Inject
    public LoginAPresenterImpl loginAPresenter;

    @Inject
    Lazy<LoginFastPost> gsonLazy;
    @Inject
    Provider<LoginFastPost> gsonLazy1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mILoginAPresenter = new LoginAPresenterImpl(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        DaggerLoginComponent.builder().appComponent(MyApplication.getApplicationComponent())
        .loginModule(new LoginModule(this))
        .build().inject(this);


        authorityTool = new AuthorityTool(this, this);
        getLifecycle().addObserver(authorityTool);
        getLifecycle().addObserver(loginAPresenter);

        // 每隔1s执行一次事件
//        Observable.interval(1, TimeUnit.SECONDS)
//        .delay(5, TimeUnit.SECONDS)
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                disposables.add(d);
//            }
//
//            @Override
//            public void onNext(@NonNull Long aLong) {
//                Log.i("接收数据", String.valueOf(aLong));
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        authorityTool.mayRequestContacts(PERMISSION);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < disposables.size(); i++) {
            if (disposables.get(i) != null && !disposables.get(i).isDisposed()) {
                disposables.get(i).dispose();
            }
        }
    }

    @OnClick({R2.id.button, R2.id.button1})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.button) {
            RouterTool.getInstance(getApplication()).build(MyARouter.MainActivity).navigationAddListen(this);
        } else if (id == R.id.button1) {

//            SPUtil.getInstance(this).putSP("test1", "1");
//            SPUtil.getInstance(this).putSP("test2", 3);
//            SPUtil.getInstance(this).putSP("test3", 3.2);
//            LoggerTool.logdebug(SPUtil.getInstance(this).getAll().toString());
//            loginAPresenter.loginFast();
//            gsonLazy.get();
//            gsonLazy1.get();
            //在这时才创建user1,以后每次调用get会得到同一个user1对象
            LoginFastPost user1=gsonLazy.get();
            //在这时创建user2，以后每次调用get会再强制调用Module的Provider方法一次，根据Provides方法具体实现的不同，可能返回跟user2是同一个对象，也可能不是。
            LoginFastPost user2=gsonLazy1.get();
            if (gsonLazy==null){
                LoggerTool.logdebug("gsonLazy1空");
            }else {
                LoggerTool.logdebug("gsonLazy1"+String.valueOf(gsonLazy.hashCode()));
            }
            if (gsonLazy1==null){
                LoggerTool.logdebug("gsonLazy空");
            }else {
                LoggerTool.logdebug("gsonLazy"+String.valueOf(gsonLazy1.hashCode()));
            }
            if (loginAPresenter==null){
                LoggerTool.logdebug("loginAPresenter空");
            }else {
                LoggerTool.logdebug("loginAPresenter"+String.valueOf(loginAPresenter.hashCode()));
            }
            if (myApplication==null){
                LoggerTool.logdebug("myApplication空");
            }else {
                LoggerTool.logdebug("myApplication"+String.valueOf(myApplication.hashCode()));
            }
            if (mGson1==null){
                LoggerTool.logdebug("mGson1空");
            }else {
                LoggerTool.logdebug("mGson1"+String.valueOf(mGson1.hashCode()));
            }
            if (mGson2==null){
                LoggerTool.logdebug("mGson2空");
            }else {
                LoggerTool.logdebug("mGson2"+String.valueOf(mGson2.hashCode()));
            }
//            myAdpplication.log();
//            myApplication.log();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        authorityTool.verifyPermissions(grantResults, requestCode, button, permissions,"没有开启获取手机信息权限，将登陆不了游戏");
    }




    @Override
    public void AuthorizeSucceed() {

    }

    @Override
    public void AuthorizeFailure() {

    }





    @Override
    public <T> T request(int requestFlag) {
        return null;
    }

    @Override
    public <T> void response(T response, int responseFlag) {

    }



    @Override
    public void toMainActivity() {
        LoggerTool.logdebug("跳转");

    }

    @Override
    public void shownetworkError(String msg) {
        LoggerTool.logdebug(msg);
    }

    @Override
    public void showfailureError(String msg) {
        LoggerTool.logdebug(msg);
    }
}
