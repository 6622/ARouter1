package com.czq.module_common.network.retrofitrxok;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2016/8/20 0020.
 */
public class Data {
    public static final int NOTSHOW = 0;//不显示
    public static final int OPENHANDFOUR = 1;//明牌发牌
    public static final int OTHEROPENHANDFOUR = 8;//明牌发牌别人
    public static final int OPENHANDOVER = 2;//明牌发牌bright
    public static final int BRIGHTCARD = 3;//亮牌
    public static final int FLOPCARD = 4;//翻牌
    public static final int STARTCARD = 5;//正常发牌
    public static final int DISCARD = 6;//弃牌和输牌
    public static final int NORMALCARD = 7;//正常没动画的显示牌
    //限定赋值范围
    @IntDef({NOTSHOW,OPENHANDFOUR, OPENHANDOVER,BRIGHTCARD,FLOPCARD,STARTCARD,DISCARD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode{}

    //token的key
    public static String tokenkey="token";
    //rid的key
    public static String ridkey="rid";
    //uid的key
    public static String uidkey="uid";
    //    声音大小
    public static int bgsize=0;
    public static int yinxiaosize=0;
    public static String bgsizekey="音乐大小";
    public static String yinxiaokey="音效大小";
    public static int defaultsize=50;//声音默认大小
    //1普通话，2方言
    public static  String language ="";
    public static String languagekey="语言";

    //用户中心host和port的key
    public static String USERCENTERHOSTKEY="host";
    public static String USERCENTERPORTKEY="port";


    public static String APP_TOKEN ="";
    public static String PACKAGE_NAME ="";//包名


    public static final String DEBUG_APP_TOKEN ="f0c7bf0ee7a28c02315d64ceec0df060";//测试
    public static final String FORMAllY_APP_TOKEN ="3e0cfaea4a1c428c61097f4f89dc09fc";//正式

    public static final Boolean debug = true;
//    public static final Boolean debug = false;
    public static final String API = "http://g2.elysoft.cn";//测试
//    public static final String API = "http://zghy.niuzhatian.cn";//正式

//    public static final String API = "http://47.106.152.152";//测试

    //用户协议
    public static final String Agreement = API + "/center/Http/news/type/4";
    //微信登录
    public static final String Loginwx = API + "/center/http/login_wx";
    //快速登录
    public static final String LoginFast = API + "/center/http/login_guest";


    //微信支付
    public static final String Pay = API + "/center/pay/index";
    public static final String Upload ="http://47.106.152.152/web/fileUploadPage1";
//    //充值页面
//    public static final String Pay = API + "/Api/pay?_token=";
    //上传异常文件
    public static final String getException ="http://39.108.9.154/?s=/Api/Oops/getException";






}
