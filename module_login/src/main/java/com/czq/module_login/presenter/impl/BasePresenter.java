package com.czq.module_login.presenter.impl;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;


/**
 * Created by ASUS on 2018/3/27.
 */

public class BasePresenter{
    public String simpleName = getClass().getSimpleName();
    public String msg="";
    public int code=0;
    public String _token="";

    public List<Disposable> disposables=null;

    public Activity activity=null;
    public BasePresenter(){
        disposables=new ArrayList<Disposable>();
    }

    /*
 * 销毁所有的请求
 * dispose():取消订阅
 * isDisposed()：判断订阅是否已经取消
 */
    public void destroy(){
        for (int i = 0; i < disposables.size(); i++) {
            if(disposables.get(i)!=null&&!disposables.get(i).isDisposed()){
                disposables.get(i).dispose();
            }
        }
    }





}
