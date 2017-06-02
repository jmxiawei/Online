package cn.samir.online.http.downloads;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.orm.SugarRecord;

import java.util.concurrent.ScheduledFuture;

import cn.samir.online.OnlineApplication;

/**
 * Created by xiaw on 2017/5/24 0024.
 */

public class DownloadQueryTask {

    private static final String TAG = "DownloadQueryTask";
    private DownloadRecord downloadRecord;

    private Handler mH;
    private DownloadManager downloadManager;

    private DownloadManager.Query mQuery;

    private DownloadTask.onProgressListener onProgressListener;


    ScheduledFuture mScheduledFuture;

    public DownloadQueryTask(Context context, DownloadRecord downloadRecord) {
        this.downloadRecord = downloadRecord;
        mH = new Handler(Looper.getMainLooper());
        downloadManager = DownloadUtils.getDownloadManager(context);
    }

    public ScheduledFuture startQuery(DownloadTask.onProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
        mQuery = new android.app.DownloadManager.Query();
        mQuery.setFilterById(downloadRecord.downloadId);
        mScheduledFuture = ScheduledExecutorServiceImp.getInstance().scheduleFixMils(command, 100);
        return mScheduledFuture;
    }


    private Runnable command = new Runnable() {
        @Override
        public void run() {
            mH.post(queryCmd);
        }
    };


    private Runnable queryCmd = new Runnable() {
        @Override
        public void run() {
            query();
        }
    };


    public void query() {
        Cursor cursor = downloadManager.query(mQuery);

        if (cursor != null && cursor.moveToFirst()) {
            //此处直接查询文件大小
            long downSize = cursor.getLong(cursor.getColumnIndex(android.app.DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));

            //获取文件下载总大小
            long mTotalSize = cursor.getLong(cursor.getColumnIndex(
                    android.app.DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

            int status = cursor.getInt(cursor.getColumnIndex(
                    android.app.DownloadManager.COLUMN_STATUS));

            cursor.close();

            Log.w(TAG, "downloaded size: " + downSize);
            Log.w(TAG, "total size: " + mTotalSize);

            if (mTotalSize != 0) {
                int percentage = (int) (downSize * 100 / mTotalSize);
                Log.w(TAG, "percentage: " + percentage);

                if (onProgressListener != null) {
                    onProgressListener.onProgress(mTotalSize, downSize);
                }
            }

            if (downloadRecord != null) {
                downloadRecord.setCurrent((int) downSize);
                downloadRecord.setTotal((int) mTotalSize);
            }


            //终止轮询task
            if (mTotalSize == downSize || status == android.app.DownloadManager.STATUS_SUCCESSFUL) {
                success(DownloadUtils.buildDestUri(OnlineApplication.getInstance(), downloadRecord.getVideoId()));
            } else {
                if (downloadRecord != null) {

                    SugarRecord.update(downloadRecord);
                }

            }
        }
    }

    private void success(Uri successUri) {

        if (downloadRecord != null) {
            downloadRecord.setSuccess(true);
            SugarRecord.update(downloadRecord);
        }
        stopProgress();
        if (onProgressListener != null) {
            onProgressListener.onComplete(successUri);
        }
    }

    private void stopProgress() {
        if (mScheduledFuture != null) {
            mScheduledFuture.cancel(true);
        }
    }
}
