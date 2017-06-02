package cn.samir.online.http.downloads;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.net.URI;

import cn.samir.domains.model.Video;
import cn.samir.online.util.Constant;

/**
 * Created by xiaw on 2017/5/24 0024.
 */

public class DownloadUtils {
    public static final String SEPARATOR = "_";
    public static final String PREFIX = "eye_videos_";
    public static final String SUFFIX_MP4 = ".mp4";


    public static DownloadManager getDownloadManager(Context context) {
        return (DownloadManager) context.getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public static String buildFileName(Object... params) {
        if (params != null && params.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (Object data :
                    params) {
                sb.append(data).append(SEPARATOR);
            }
            sb.deleteCharAt(sb.length() - 1);
            return PREFIX + sb.toString() + SUFFIX_MP4;
        } else {
            return PREFIX + SUFFIX_MP4;
        }


    }

    public static Uri buildDestUri(Context context, Object... params) {
        File file = new File(Environment.getExternalStorageDirectory() + Constant.FOLDER_ROOT, Environment.DIRECTORY_DOWNLOADS);
        Uri uri = Uri.fromFile(file);
        return Uri.withAppendedPath(uri, buildFileName(params));
    }


    public static void checkExist(Context context, Video video) {
        video.setDownloadUri(DownloadUtils.isExist(context, video.getId()));
    }

    public static Uri isExist(Context context, Object... params) {
        Uri uri = buildDestUri(context, params);
        File file = new File(URI.create(uri.toString()));
        if (file.exists()) {
            return uri;
        } else {
            return null;
        }
    }

}
