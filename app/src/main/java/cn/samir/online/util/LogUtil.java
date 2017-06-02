package cn.samir.online.util;

import android.util.Log;

import java.util.Locale;


/**
 * Logging helper specifically for use by Stetho internals.
 */
public class LogUtil {
    private static final String TAG = "online__";

    public static void e(String format, Object... args) {
        e(format(format, args));
    }

    public static void e(Throwable t, String format, Object... args) {
        e(t, format(format, args));
    }

    public static void e(String message) {
        Log.e(TAG, message);
    }

    public static void e(Object o) {
        Log.e(TAG, o.getClass().getSimpleName() + "-[" + o.toString() + " ]");
    }

    private static String format(String format, Object... args) {
        return String.format(Locale.US, format, args);
    }

}
