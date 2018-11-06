package com.czq.module_common.tool.router;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.czq.module_common.R;
import com.czq.module_common.log.LoggerTool;

/**
 * Created by ASUS on 2018/6/21.
 */

public class RouterTool {
    private static RouterTool routerTool = null;
    private static Postcard postcard = null;

    public static RouterTool getInstance(Application application){
        if (routerTool==null){
            synchronized (RouterTool.class) {
                if (routerTool == null) {
                    routerTool = new RouterTool(application);
                }
            }
        }
        return routerTool;
    }
    public RouterTool(Application application) {
        if (true) {                 // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();      // 打印日志
            ARouter.openDebug();    // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
    }
    public RouterTool build(String target) {
        postcard=ARouter.getInstance().build(target).withTransition(R.anim.slide_in_bottom,R.anim.slide_out_bottom);
        return this;
    };
    //注入参数
    public RouterTool withString(String key,String value) {
        postcard.withString(key,value);
        return this;
    };
    //注入参数
    public RouterTool withInt(String key,int value) {
        postcard.withInt(key,value);
        return this;
    };
    //发起路由跳转
    public RouterTool navigation(Activity activity) {
        postcard.navigation();
        activity.overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_in);
        return this;
    };

    //发起路由跳转
    public RouterTool navigation(Activity activity, int requestCode) {
        postcard.navigation(activity,requestCode);
        activity.overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_in);
        return this;
    };
    //发起路由跳转,关闭当前页
    public RouterTool navigationAddListen(final Activity activity) {
        postcard.navigation(activity, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) { //路由到达之后调用
                LoggerTool.logdebug("路由到达之后调用");
                activity.finish();
            }
            @Override
            public void onInterrupt(Postcard postcard) {//路由被拦截时调用
                super.onInterrupt(postcard);
                LoggerTool.logdebug("路由被拦截时调用");
            }

            @Override
            public void onLost(Postcard postcard) {//路由被丢失时调用
                super.onLost(postcard);
                LoggerTool.logdebug("路由被丢失时调用");
            }
            @Override
            public void onFound(Postcard postcard) {//路由目标被发现时调用
                super.onFound(postcard);
                LoggerTool.logdebug("路由目标被发现时调用");
            }
        });
        activity.overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_in);
        return this;
    };

    //依赖注入参数初始化
    public RouterTool inject(Context context){
        ARouter.getInstance().inject(context);
        return this;
    }


}
