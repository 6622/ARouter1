package com.czq.module_common.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.czq.module_common.di.component.AppComponent;
import com.czq.module_common.di.component.DaggerAppComponent;
import com.czq.module_common.di.module.AppModule;
import com.czq.module_common.log.LoggerTool;
import com.czq.module_common.tool.datapersistence.SPUtil;
import com.czq.module_common.tool.router.RouterTool;
import com.czq.module_common.tool.rx.RxFileTool;
import com.czq.module_common.tool.rx.RxLogTool;
import com.czq.module_common.tool.rx.RxTool;

import java.util.List;


/**
 * Created by ASUS on 2018/6/19.
 */

public class MyApplication extends Application {
    public static MyApplication instance;
    private static AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        String processName = getProcessName(this, android.os.Process.myPid());
        if (processName.endsWith("com.czq.arouter")) {
            mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

            RouterTool.getInstance(this);
            //本地持久化
            SPUtil.getInstance(this);
            RxTool.init(this);
            RxLogTool.init(this);
            RxFileTool.getRootPath();
        }
    }
    // 对外提供ApplicationComponent
    public static AppComponent getApplicationComponent() {
        return mAppComponent;
    }










    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

}
