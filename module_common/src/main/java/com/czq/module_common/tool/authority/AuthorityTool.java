package com.czq.module_common.tool.authority;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;


import com.czq.module_common.log.LoggerTool;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ASUS on 2017/12/27.
 */

public class AuthorityTool implements LifecycleObserver{
    private static final String TAG = AuthorityTool.class.getSimpleName();
    //授权请求码
    private static final int REQUEST_READ_CONTACTS = 0;
    private PermissionsCallBack permissionsCallBack;
    Activity activity;
    Context context;
    private static AuthorityTool instance=null;

    public AuthorityTool(Activity activity,PermissionsCallBack permissionsCallBack){
        this.activity=activity;
        this.permissionsCallBack=permissionsCallBack;
    }



    @SuppressLint("NewApi")
    public void mayRequestContacts(String[] authoritys) {
        List<String> authorityl=new ArrayList<String>();
        //判断手机系统版本是否大于等于6.0，小于6.0不需要询问用户开启权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        for (String authority:authoritys){
            //判断手机是否拥有该权限
            if (activity.checkSelfPermission(authority) != PackageManager.PERMISSION_GRANTED) {
                authorityl.add(authority);
            }
        }
        //list转化为[]
        final String[] authoritys1=authorityl.toArray(new String[authorityl.size()]);
        if (authoritys1.length>0){
            activity.requestPermissions(authoritys1,REQUEST_READ_CONTACTS);
        }else {
            permissionsCallBack.AuthorizeSucceed();
        }
    }


    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void verifyPermissions(int[] grantResults, int requestCode, View view,String[] authoritys,String showmsg) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            boolean ispermissionsall=true;
            List<String> authorityl=new ArrayList<String>();
            for (int i = 0; i < grantResults.length; ++i) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    if (!activity.shouldShowRequestPermissionRationale(authoritys[i])) {
                        authorityl.add(authoritys[i]);
                    }else {
                        ispermissionsall=false;
                    }
                }
            }
            if (authorityl.size()>0){
                //点击了
                Snackbar snackbar=Snackbar.make(view,showmsg, Snackbar.LENGTH_LONG);
                View mView = snackbar.getView();
                mView.setBackgroundColor(0xdd222222);
                snackbar.setAction("手动开启", new View.OnClickListener() {
                    @Override
                    @TargetApi(Build.VERSION_CODES.M)
                    public void onClick(View v) {
                        //引导用户至设置页手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", activity.getApplicationContext().getPackageName(), null);
                        intent.setData(uri);
                        activity.startActivity(intent);
                    }
                })
                .show();
                return;
            }

            if (ispermissionsall){
                permissionsCallBack.AuthorizeSucceed();
            }else {
                permissionsCallBack.AuthorizeFailure();
            }
        }
    }




    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() { Log.d(TAG, "onCreate"); }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() { Log.d(TAG, "onStart"); }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() { Log.d(TAG, "onResume"); }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() { Log.d(TAG, "onPause"); }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() { Log.d(TAG, "onStop"); }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() { Log.d(TAG, "onDestroy"); }

}
