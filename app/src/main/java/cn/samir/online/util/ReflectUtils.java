package cn.samir.online.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xiaw on 2017/5/10 0010.
 */

public class ReflectUtils {


    /**
     * @param src
     * @param name
     * @return
     */
    public static Object getField(Object src, String name) {
        try {
            Field field = src.getClass().getField(name);
            field.setAccessible(true);
            return field.get(src);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void setFieldBooleanValue(Object src, String fieldName, boolean value) {

        Field field = null;
        try {
            field = src.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            if (field != null) {
                field.set(src, value);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static void callMethod(Object src, String name, Object... params) {
        int size = params.length;
        Class[] clazz = null;
        if (size > 0) {
            clazz = new Class[size];
            for (int i = 0; i < size; i++) {
                clazz[i] = params[i].getClass();
            }
        }
        try {
            Method method = src.getClass().getDeclaredMethod(name,clazz);
            method.invoke(src,params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
