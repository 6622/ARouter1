package com.czq.module_common.zhujie;

import android.app.Activity;

import com.czq.module_common.log.LoggerTool;

import java.lang.reflect.Field;

public class AnnotationUtil {
    public static void injectViews(Activity activity){
        //获取类
        Class<? extends Activity> klass = activity.getClass();
        // 获取所有的属性
        Field[] fields = klass.getDeclaredFields();
        // 遍历每一个属性
        for(Field field : fields){
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if(viewInject != null){
                try {
                    String id = viewInject.value();
                    field.setAccessible(true);
                    field.set(activity,id);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    LoggerTool.logdebug("注解报错");
                }
            }
        }


    }
}
