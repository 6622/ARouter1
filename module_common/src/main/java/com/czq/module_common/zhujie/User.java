package com.czq.module_common.zhujie;


import com.czq.module_common.log.LoggerTool;

public class User {
    private String name="sas";
    private String password="1213";
    private String showName(String a){
        LoggerTool.logdebug("方法"+name);
        return a;
    }
}
