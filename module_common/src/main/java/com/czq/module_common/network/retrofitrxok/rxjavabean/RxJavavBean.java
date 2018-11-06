package com.czq.module_common.network.retrofitrxok.rxjavabean;

import org.json.JSONObject;

/**
 * Created by ASUS on 2017/11/20.
 */

public class RxJavavBean {
    private String url="";
    private String msg="";
    private int code=0;
    private JSONObject response=null;


    public JSONObject getResponse() {
        return response;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        try {
            msg=getResponse().getString("msg");
        } catch (Exception e) {
            e.printStackTrace();
            msg="";
        }
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        try {
            code=getResponse().getInt("code");
        } catch (Exception e) {
            e.printStackTrace();
            code=0;
        }
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
