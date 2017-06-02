package cn.samir.domains.model.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * 获取手机相关信息
 * Created by liz on 2015/10/13 0013.
 */
public class PhoneUtil {


    /**
     * 手机品牌
     *
     * @return
     */
    public static String getVendor() {
        return Build.BRAND;
    }

    /**
     * 手机机型
     *
     * @return
     */
    public static String getDevice() {
        return Build.MODEL;
    }

    /**
     * 设备唯一标识
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager TelephonyMgr;
        TelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

    /**
     * 系统版本号
     *
     * @return
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }


    public static String getOSVersionInt() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }


    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
