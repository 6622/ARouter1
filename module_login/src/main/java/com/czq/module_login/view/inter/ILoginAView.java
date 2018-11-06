package com.czq.module_login.view.inter;

public interface ILoginAView {
    //请求标记
    int REQUEST_ONE = 0;
    int REQUEST_TWO = 1;
    int REQUEST_THREE = 2;
    //响应标记
    int RESPONSE_ONE = 0;
    int RESPONSE_TWO = 1;
    int RESPONSE_THREE = 2;

    <T> T request(int requestFlag);

    <T> void response(T response, int responseFlag);

    // 登录成功跳转页面
    void toMainActivity();
    void shownetworkError(String msg);//网络异常
    void showfailureError(String msg);//请求失败
}
