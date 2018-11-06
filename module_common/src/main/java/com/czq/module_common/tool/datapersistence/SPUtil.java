package com.czq.module_common.tool.datapersistence;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

//https://blog.csdn.net/zhang31jian/article/details/23258065
public class SPUtil {
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";
    // 创建一个写入器
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;
    private static SPUtil mSharedPreferencesUtil;

    // 构造方法
    public SPUtil(Context context) {
        mPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    // 单例模式
    public static SPUtil getInstance(Context context) {
        if (mSharedPreferencesUtil == null) {
            Log.d(SPUtil.class.getSimpleName(),"创建SPUtil");
            mSharedPreferencesUtil = new SPUtil(context);
        }
        return mSharedPreferencesUtil;
    }

    // 存入数据
    public void putSP(String key,Object object) {
        if (object instanceof String) {
            mEditor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            mEditor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            mEditor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            mEditor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            mEditor.putLong(key, (Long) object);
        } else {
            mEditor.putString(key, object.toString());
        }
        SharedPreferencesCompat.apply(mEditor);
    }

    // 获取数据
    public Object getSP(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return mPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return mPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return mPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return mPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return mPreferences.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    //移除某个key值已经对应的值
    public void remove(String key) {
        mEditor.remove(key);
        SharedPreferencesCompat.apply(mEditor);
    }

    //    清除所有数据
    public void clear(){
        mEditor.clear();
        SharedPreferencesCompat.apply(mEditor);
    }


    //查询某个key是否已经存在
    public boolean contains(String key) {
        return mPreferences.contains(key);
    }

    //返回所有的键值对
    public Map<String, ?> getAll() {
        return mPreferences.getAll();
    }




    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

}
