package com.czq.module_common.post;



public class LoginPost {
    private String sid;
    private String wx_uid;
    private String wx_username;
    private String wx_img;


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getWx_uid() {
        return wx_uid;
    }

    public void setWx_uid(String wx_uid) {
        this.wx_uid = wx_uid;
    }

    public String getWx_username() {
        return wx_username;
    }

    public void setWx_username(String wx_username) {
        this.wx_username = wx_username;
    }

    public String getWx_img() {
        return wx_img;
    }

    public void setWx_img(String wx_img) {
        this.wx_img = wx_img;
    }
}
