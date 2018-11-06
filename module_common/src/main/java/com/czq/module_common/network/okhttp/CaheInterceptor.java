package com.czq.module_common.network.okhttp;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.czq.module_common.log.LoggerTool;
import com.czq.module_common.network.retrofitrxok.net.NetworkUtil;
import com.czq.module_common.tool.rx.RxDeviceTool;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * caheInterceptor
 * Created by Tamic on 2016-08-09.
 */
public class CaheInterceptor implements Interceptor {

    private Context context;

    public CaheInterceptor(Context context) {
        this.context = context;
        //缓存文件
        File cacheFile = new File(Environment.getExternalStorageDirectory().toString() + "/Android/data/" + context.getPackageName() + "/ca/");
        //设置缓存大小
        Cache cache = new Cache(cacheFile, 10 * 1024 * 1024);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!RxDeviceTool.isNetworkConnected(context)) {
            request = request.newBuilder()
            .cacheControl(CacheControl.FORCE_CACHE)
            .build();
        }
        Response response = chain.proceed(request);
        if (RxDeviceTool.isNetworkConnected(context)) {
            LoggerTool.logdebug("有网络");
            int maxAge = 0;
            // 有网络时 设置缓存超时时间0个小时
            response.newBuilder()
            .header("Cache-Control", "public, max-age=" + maxAge)
            .removeHeader("WuXiaolong")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
            .build();
        } else {
            LoggerTool.logdebug("无网络");
            // 无网络时，设置超时为4周
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder()
            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
            .removeHeader("nyn")
            .build();
        }
        return response;
    }
}
