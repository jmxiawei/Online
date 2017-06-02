package cn.samir.online.util;

import android.content.Context;
import android.os.Environment;

/**
 * Created by xiaw on 2017/4/10 0010.
 */

public class FileUtil {


    /**
     * 获取缓存路径
     *
     * @param ctx
     * @return
     */
    public static String getCacheDir(Context ctx, String path) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory() + "/" + Constant.FOLDER_ROOT + "/" + path;
        } else {
            return ctx.getCacheDir().getAbsolutePath();
        }
    }
}
