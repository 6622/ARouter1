package com.czq.module_common.network.retrofitrxok;


import android.content.Context;


import com.czq.module_common.log.LoggerTool;
import com.czq.module_common.network.retrofitrxok.cache.CacheProviders;
import com.czq.module_common.network.retrofitrxok.interfacepath.NetworkCall;
import com.czq.module_common.network.retrofitrxok.interfacepath.UserService;
import com.czq.module_common.network.retrofitrxok.net.DownLoadManager;
import com.czq.module_common.network.retrofitrxok.rxjavabean.RxJavavBean;
import com.czq.module_common.post.LoginFastPost;
import com.czq.module_common.tool.rx.RxTool;
import com.czq.module_common.zhujie.ViewInject;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 外部LoginModel，LoginView，MyApplication.User_state.edit()
 */

public class NetworkTool{
    //服务接口
    private static UserService userService2;
    private static Observer<ResponseBody> objectObserver1=null;
    private List<Disposable> disposables=null;
    public NetworkTool() {
        userService2 = LobbyNetHttpApi.getInstance(RxTool.getContext()).getService_JSONObject(UserService.class);
        objectObserver1=new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                LoggerTool.logdebug("上传成功");
                try {
                    LoggerTool.logdebug("responseBody",responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                LoggerTool.logdebug(e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
    }




    public void requestHttp(NiuNIuPostMain postMain,Observer<RxJavavBean> objectObserver) {
        Map<String, String> params =params(postMain);
        //网络请求数据
        Observable<JSONObject> user =  userService2.LoginFast(params);
        //缓存配置
        CacheProviders.getUserCache()
        .getUser(user, new DynamicKey("a"), new EvictDynamicKey(true))//用户名作为动态key生成不同文件存储数据，默认不清除缓存数据
        .delay(1, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map(new Function<JSONObject, RxJavavBean>() {
            @Override
            public RxJavavBean apply(JSONObject jsonObject) throws Exception {
//                Logger.json(jsonObject.toString());
                RxJavavBean rxJavavBean=new RxJavavBean();
                rxJavavBean.setResponse(jsonObject);
                rxJavavBean.setUrl(Data.LoginFast);
                return rxJavavBean;

            }
        })
        .subscribe(objectObserver);
    }



    //快速登陆
    public void LoginFast(NiuNIuPostMain postMain,Observer<RxJavavBean> objectObserver) {
        LoginFastPost loginFastPost=new LoginFastPost();
        loginFastPost.setSid(postMain.getSid());
        Map<String, String> params =params(postMain);
        userService2.LoginFast(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map(new Function<JSONObject, RxJavavBean>() {
            @Override
            public RxJavavBean apply(JSONObject jsonObject) throws Exception {
//                Logger.json(jsonObject.toString());
                RxJavavBean rxJavavBean=new RxJavavBean();
                rxJavavBean.setResponse(jsonObject);
                rxJavavBean.setUrl(Data.LoginFast);
                return rxJavavBean;

            }
        })
        .subscribe(objectObserver);
    }

    //微信登陆
    public void Login(NiuNIuPostMain postMain,Observer<RxJavavBean> objectObserver) {
//        LoginPost loginPost=new LoginPost();
//        loginPost.setSid(postMain.getSid());
//        loginPost.setWx_uid(postMain.getUnionid());
//        loginPost.setWx_username(postMain.getUsername());
//        loginPost.setWx_img(postMain.getImg());
        Map<String, String> params =params(postMain);
        userService2.Login(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map(new Function<JSONObject, RxJavavBean>() {
            @Override
            public RxJavavBean apply(JSONObject jsonObject) throws Exception {
//                Logger.json(jsonObject.toString());
                RxJavavBean rxJavavBean=new RxJavavBean();
                rxJavavBean.setResponse(jsonObject);
                rxJavavBean.setUrl(Data.Loginwx);
                return rxJavavBean;

            }
        })
        .subscribe(objectObserver);
    }

    //上传文件
    public static void upload(File file) {
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
        RequestBody.create(MediaType.parse("multipart/form-data"), file);
        LoggerTool.logdebug("上传"+ file.getName());
    // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body =
        MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        // 添加描述
//        String descriptionString = "hello, 这是文件描述";
//        RequestBody description =
//        RequestBody.create(
//        MediaType.parse("multipart/form-data"), descriptionString);

        userService2.upload(body)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(objectObserver1);
    }

    //下载文件
    public static void downloadFile(final Context context, String fileUrl) {
        userService2.downloadFile(fileUrl)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody value) {
                DownLoadManager.getInstance(null).writeResponseBodyToDisk(context, (okhttp3.ResponseBody) value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                LoggerTool.logdebug("下载完成");
            }
        });
    }




    private Map<String, String> params(Object s){
        Class c=s.getClass();
        Map<String, String> params = new HashMap<String, String>();
        try {
            for(Field field:c.getDeclaredFields()){
                field.setAccessible(true);//f.setAccessible(true);得作用就是让我们在用反射时访问私有变量，不然会报错
                ViewInject viewInject = field.getAnnotation(ViewInject.class);
                if (viewInject!=null){
                    String key="";
                    try {
                        key = viewInject.key();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                params.put((String)field.getName(), (String) field.get(s));
                    params.put(key, (String) field.get(s));
                }else {
                    params.put((String)field.getName(), String.valueOf(field.get(s)) );
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LoggerTool.logdebug("提取提值和字段数报错");
        }
        LoggerTool.logdebug("提交参数"+params.toString());
        return params;
    }



}
