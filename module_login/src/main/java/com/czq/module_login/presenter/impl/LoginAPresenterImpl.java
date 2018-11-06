package com.czq.module_login.presenter.impl;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;

import com.czq.module_common.log.LoggerTool;
import com.czq.module_common.network.retrofitrxok.Data;
import com.czq.module_common.network.retrofitrxok.NetworkTool;
import com.czq.module_common.network.retrofitrxok.NiuNIuPostMain;
import com.czq.module_common.network.retrofitrxok.rxjavabean.RxJavavBean;
import com.czq.module_common.tool.datapersistence.SPUtil;
import com.czq.module_common.tool.rx.RxDataTool;
import com.czq.module_common.tool.rx.RxDeviceTool;
import com.czq.module_common.tool.rx.RxToast;
import com.czq.module_common.tool.rx.RxTool;
import com.czq.module_login.model.impl.LoginAModelImpl;
import com.czq.module_login.model.inter.ILoginAModel;
import com.czq.module_login.presenter.inter.ILoginAPresenter;
import com.czq.module_login.view.inter.ILoginAView;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginAPresenterImpl extends BasePresenter implements ILoginAPresenter{
    private ILoginAView mILoginAView;
    private ILoginAModel mILoginAModel;


    private NetworkTool networkTool;
    private Context context;
    private NiuNIuPostMain postMain=new NiuNIuPostMain();
    @Inject
    public LoginAPresenterImpl(ILoginAView aILoginAView) {
        mILoginAView = aILoginAView;
        mILoginAModel = new LoginAModelImpl();
        context =RxTool.getContext();
        networkTool=new NetworkTool();

    }


    private Observer<RxJavavBean> objectObserver=new Observer<RxJavavBean>() {
        @Override
        public void onSubscribe(Disposable d) {
            disposables.add(d);
        }

        @Override
        public void onNext(RxJavavBean rxJavavBean) {
            LoggerTool.logdebug(rxJavavBean.getResponse().toString());
            handleloginjson(rxJavavBean.getResponse());
        }

        @Override
        public void onError(Throwable e) {
            LoggerTool.logdebug(e.toString());
            RxToast.showToast(RxDataTool.handleResponseError(e));
        }

        @Override
        public void onComplete() {

        }
    };




    //回调处理
    private void handleloginjson(JSONObject jsonObject){
        LoggerTool.logdebug(jsonObject.toString());
        try {
            msg = jsonObject.getString("msg");
            code = jsonObject.getInt("code");
            JSONObject info = jsonObject.optJSONObject("data");
            if (code==1){
                _token=info.getString("token");
                String host=info.optString("host");
                String port=info.optString("port");
                if (host!=null&&port!=null){
                    SPUtil.getInstance(context).putSP(Data.USERCENTERHOSTKEY,host);
                    SPUtil.getInstance(context).putSP(Data.USERCENTERPORTKEY,port);
                }
                SPUtil.getInstance(context).putSP(Data.tokenkey,_token);
                mILoginAView.toMainActivity();
            }else {
                mILoginAView.showfailureError(msg);
            }
        } catch (JSONException e) {
            msg="数据结构错误";
//            LoggerTool.loggerE(e,"登录回调数据处理出错");
            mILoginAView.showfailureError(msg);
        }
    }




    //快速登陆
    @Override
    public void loginFast() {
//        LoggerTool.logdebug("setDeviceId()",setDeviceId());

        postMain.setSid(RxDeviceTool.getDeviceIdIMEI(context));
        networkTool.requestHttp(postMain,objectObserver);
//        networkTool.LoginFast(postMain,objectObserver);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.d(simpleName, "onDestroy");
        destroy();
    }

}
