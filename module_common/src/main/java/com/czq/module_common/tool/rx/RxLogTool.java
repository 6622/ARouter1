package com.czq.module_common.tool.rx;

import android.content.Context;
import android.net.ParseException;
import android.os.Environment;
import android.util.Log;


import com.czq.module_common.log.LoggerTool;
import com.czq.module_common.tool.datapersistence.SPUtil;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.exceptions.CompositeException;
import retrofit2.HttpException;

/**
 *
 * @author vondear
 * @date 2016/1/24
 */
public class RxLogTool {

    private final static SimpleDateFormat LOG_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 日志的输出格式
    private final static SimpleDateFormat FILE_SUFFIX = new SimpleDateFormat("yyyy-MM-dd");// 日志文件格式
    private static Boolean LOG_SWITCH = true; // 日志文件总开关
    private static Boolean LOG_TO_FILE = true; // 日志写入文件开关
    private static String LOG_TAG = "TAG"; // 默认的tag
    private static char LOG_TYPE = 'v';// 输入日志类型，v代表输出所有信息,w则只输出警告...
    private static int LOG_SAVE_DAYS = 7;// sd卡中日志文件的最多保存天数
    public static String LOG_FILE_PATH; // 日志文件保存路径
    public static String LOG_FILE_NAME;// 日志文件保存名称
    public static String LOG_FILE_NAME1;// 日志文件保存名称
    public static String USER_NAME="";// 用户名和uid

    public static void init(Context context) { // 在Application中初始化
//        LOG_FILE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + context.getPackageName();
        LOG_FILE_NAME = "Log";
        LOG_FILE_PATH =RxFileTool.getRootPath()+ "/Android/data/" + context.getPackageName() + "/log/";
    }
    public static void setUserName(String userName){
        USER_NAME=userName;
    }

    /****************************
     * Warn
     *********************************/
    public static void w(Object msg) {
        w(LOG_TAG, msg);
    }

    public static void w(String tag, Object msg) {
        w(tag, msg, null);
    }

    public static void w(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'w');
    }

    /***************************
     * Error
     ********************************/
    public static void e(Object msg) {
        e(LOG_TAG, msg);
    }

    public static void e(String tag, Object msg) {
        e(tag, msg, null);
    }

    public static void e(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'e');
    }

    /***************************
     * Debug
     ********************************/
    public static void d(Object msg) {
        d(LOG_TAG, msg);
    }

    public static void d(String tag, Object msg) {// 调试信息
        d(tag, msg, null);
    }

    public static void d(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'd');
    }

    /****************************
     * Info
     *********************************/
    public static void i(Object msg) {
        i(LOG_TAG, msg);
    }

    public static void i(String tag, Object msg) {
        i(tag, msg, null);
    }

    public static void i(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'i');
    }

    /**************************
     * Verbose
     ********************************/
    public static void v(Object msg) {
        v(LOG_TAG, msg);
    }

    public static void v(String tag, Object msg) {
        v(tag, msg, null);
    }

    public static void v(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'v');
    }

    /**
     * 根据tag, msg和等级，输出日志
     *
     * @param tag
     * @param msg
     * @param level
     */
    private static void log(String tag, String msg, Throwable tr, char level) {
        if (LOG_SWITCH) {
            if ('e' == level && ('e' == LOG_TYPE || 'v' == LOG_TYPE)) { // 输出错误信息
                Log.e(tag, msg, tr);
            } else if ('w' == level && ('w' == LOG_TYPE || 'v' == LOG_TYPE)) {
                Log.w(tag, msg, tr);
            } else if ('d' == level && ('d' == LOG_TYPE || 'v' == LOG_TYPE)) {
                Log.d(tag, msg, tr);
            } else if ('i' == level && ('d' == LOG_TYPE || 'v' == LOG_TYPE)) {
                Log.i(tag, msg, tr);
            } else {
                Log.v(tag, msg, tr);
            }
            if (LOG_TO_FILE){
                LoggerTool.logdebug("日志",msg);
//                log2File(String.valueOf(level), tag, msg + tr == null ? "" : "\n" + Log.getStackTraceString(tr));
                log2File(String.valueOf(level), tag, msg + tr == null ? "" : "\n" +msg);
            }
        }
    }

    /**
     * 打开日志文件并写入日志
     *
     * @return
     **/
    private synchronized static void log2File(String mylogtype, String tag, String text) {
        Date nowtime = new Date();
        String date = FILE_SUFFIX.format(nowtime);
        String dateLogContent = LOG_FORMAT.format(nowtime) + ":" + mylogtype + ":" + tag + ":" + text; // 日志输出格式

        File destDir = new File(LOG_FILE_PATH);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        LOG_FILE_NAME1=LOG_FILE_NAME + date+USER_NAME+".txt";
        SPUtil.getInstance(RxTool.getContext()).putSP("logfilename",LOG_FILE_NAME1);
        File file = new File(LOG_FILE_PATH,LOG_FILE_NAME1);
        try {
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8");
            BufferedWriter bufWriter=new BufferedWriter(write);
//            FileWriter filerWriter = new FileWriter(file, true);
//            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(dateLogContent);
            bufWriter.newLine();
            bufWriter.close();
//            filerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定的日志文件
     */
    public static void delFile() {// 删除日志文件
        String needDelFiel = FILE_SUFFIX.format(getDateBefore());
        File file = new File(LOG_FILE_PATH, needDelFiel + LOG_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 得到LOG_SAVE_DAYS天前的日期
     *
     * @return
     */
    private static Date getDateBefore() {
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - LOG_SAVE_DAYS);
        return now.getTime();
    }

    public static void saveLogFile(String message) {
        File fileDir = new File(RxFileTool.getRootPath() + File.separator + RxTool.getContext().getPackageName());
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File file = new File(fileDir, RxTimeTool.getCurrentDateTime("yyyyMMdd") + ".txt");
        try {
            if (file.exists()) {
                PrintStream ps = new PrintStream(new FileOutputStream(file, true));
                ps.append(RxTimeTool.getCurrentDateTime("\n\n\nyyyy-MM-dd HH:mm:ss") + "\n" + message);// 往文件里写入字符串
            } else {
                PrintStream ps = new PrintStream(new FileOutputStream(file));
                file.createNewFile();
                ps.println(RxTimeTool.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") + "\n" + message);// 往文件里写入字符串
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
