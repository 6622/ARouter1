package com.czq.module_common.network.retrofitrxok;
import android.content.Context;
import android.os.Environment;


import com.czq.module_common.BuildConfig;
import com.czq.module_common.log.LoggerTool;
import com.czq.module_common.network.okhttp.CaheInterceptor;
import com.czq.module_common.network.retrofitrxok.converterfactory.JsonConverterFactory;
import com.czq.module_common.tool.rx.RxTool;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 网络请求Api
 * 1. 设置BaseUrl时必须后缀”/”，如https://api.github.com/

 （否而： java.lang.IllegalArgumentException: baseUrl must end in /:）

 2. 因为Retrofit在创建时候传入了BaseUrl，所以基本上所有请求都基于该BaseUrl了。
 但是总有些API不是以该BaseUrl开头的，特别是有些公司可能不是restful API的。那么该怎么办呢。
 Retrofit提供了一个注解@Url解决这个问题，可以在运行时直接使用该Url直接访问
 */
public class LobbyNetHttpApi {


    private Retrofit mRetrofit_JSONObject;
    //请求超时时间
    private static final int REQUEST_TIME = 10;
    private static LobbyNetHttpApi instance;
    private LobbyNetHttpApi(Context context) {
        //缓存文件
        File cacheFile = new File(Environment.getExternalStorageDirectory().toString() + "/Android/data/" + context.getPackageName() + "/ca/");
        //设置缓存大小
        Cache cache = new Cache(cacheFile, 10 * 1024 * 1024);




        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(REQUEST_TIME, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIME, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIME, TimeUnit.SECONDS)
//        .cookieJar(new NovateCookieManger(context))
        //错误重连,默认重试一次，若需要重试N次，则要实现拦截器。
        .retryOnConnectionFailure(true)
//        .cache(cache)//缓存机制
//        .addInterceptor(new BaseInterceptor(headers))
//        .addNetworkInterceptor(new CaheInterceptor(context))//缓存机制
        .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
        // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为15s
       ;


        LoggerTool.logdebug("BuildConfig.DEBUG"+String.valueOf(BuildConfig.DEBUG));
        LoggerTool.logdebug("BuildConfig.HTTP_BASE"+String.valueOf(BuildConfig.HTTP_BASE));
        if (BuildConfig.DEBUG){
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor); //Log信息拦截器
        }
        ;



        mRetrofit_JSONObject = new Retrofit.Builder()
        .client(client.build())
        .baseUrl(Data.API)
        .addConverterFactory(JsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    }


    public static LobbyNetHttpApi getInstance(Context context) {
        if (instance == null) {
            synchronized (LobbyNetHttpApi.class) {
                if (instance == null) {
                    instance = new LobbyNetHttpApi(context);
                }
            }
        }
        return instance;
    }

    public <T> T getService_JSONObject(Class<T> service) {
        return mRetrofit_JSONObject.create(service);
    }
}
