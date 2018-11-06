package com.czq.module_common.network.retrofitrxok.interfacepath;


import com.czq.module_common.network.retrofitrxok.rxjavabean.RxJavavBean;

import org.json.JSONObject;

/*网络请求回调接口*/
public interface NetworkCall{
    //操作成功
    void Success(RxJavavBean rxJavavBean);
    //操作失败
    void Failure(RxJavavBean rxJavavBean);
    //网络异常
    void NetworkAnomaly(String throwable);
}
