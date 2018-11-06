package com.czq.module_common.log;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by ASUS on 2018/1/8.
 */

public class LoggerTool {
    private static boolean is_logger=true;
    private static boolean is_progress_logger=true;
    private static String progresstab="游戏流程";
    private static String progresstab1="明牌抢庄流程";
    private static Toast toast=null;
    //输出提示
    public static void logshow(Context context,String msg){
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        // toast.setGravity(Gravity.CENTER, 0, 0);
        if (!TextUtils.isEmpty(msg)){
            toast.show();
        }
    }

    //输出log
    public static void logdebug(String msg){
        if (is_progress_logger==true){
            if (TextUtils.isEmpty(msg)){
                Log.e(progresstab,"null");
            }else {
                Log.e(progresstab,msg);
            }

        }
    }
    public static void logdebug(String tab,String msg){
        if (is_progress_logger==true){
            if (TextUtils.isEmpty(msg)){
                Log.e(tab,"null");
            }else {
                Log.e(tab,msg);
            }
        }
    }
    //输出log
    public static void logdebug1(String msg){
        if (is_progress_logger==true){
            Log.e(progresstab1,msg);
        }
    }

}
