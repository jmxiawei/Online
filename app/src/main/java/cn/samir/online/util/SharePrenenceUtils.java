package cn.samir.online.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xiaw on 2017/3/17 0017.
 */

public class SharePrenenceUtils {



    public static final String SP_KEY_PREX = "KEY_PREFIX_";
    private static String sp_name = "cn.samir.online.sp_prefix";

    public static void saveString(Context context, String name, String value) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
            sp.edit().putString(getName(name), value).apply();
        }
    }

    public static String getName(String name) {
        return SP_KEY_PREX.concat(name);
    }

    public static String getString(Context context, String name) {
        if (context == null)
            return "";
        return getString(context, getName(name), "");
    }

    public static String getString(Context context, String name, String defaultValue) {
        if (context == null)
            return defaultValue;
        SharedPreferences sp = context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        return sp.getString(getName(name), defaultValue);
    }

    public static boolean getPrefBoolean(Context context, final String key,
                                         final boolean defaultValue) {
        if (context == null)
            return defaultValue;
        final SharedPreferences settings = context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        return settings.getBoolean(getName(key), defaultValue);
    }


    public static void setPrefBoolean(Context context, final String key,
                                      final boolean value) {
        if (context != null) {
            final SharedPreferences settings = context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
            settings.edit().putBoolean(getName(key), value).apply();
        }
    }

    public static int getPrefInt(Context context, final String key,
                                 final int defaultValue) {
        if (context == null)
            return defaultValue;
        final SharedPreferences settings = context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        return settings.getInt(getName(key), defaultValue);
    }

    public static void setSettingLong(Context context, final String key,
                                      final long value) {
        if (context != null) {
            final SharedPreferences settings = context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
            settings.edit().putLong(getName(key), value).apply();
        }
    }

    public static long getPrefLong(Context context, final String key,
                                   final long defaultValue) {
        if (context == null)
            return defaultValue;

        final SharedPreferences settings = context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        return settings.getLong(getName(key), defaultValue);
    }
}
