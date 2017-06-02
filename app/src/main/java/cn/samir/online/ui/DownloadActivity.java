//package cn.samir.online.ui;
//
//import android.app.DownloadManager;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import cn.samir.online.R;
//import cn.samir.online.http.downloads.DownloadTask;
//import cn.samir.online.views.CustomFontTextView;
//
//
//public class DownloadActivity extends AppCompatActivity {
//
//
//    DownloadManager downloadManager;
//    @BindView(R.id.progress)
//    CustomFontTextView progress;
//    private long downloadId = -1;
//
//    public int[] getBytesAndStatus(long downloadId) {
//        int[] bytesAndStatus = new int[]{-1, -1, 0};
//        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
//        Cursor c = null;
//        try {
//            c = downloadManager.query(query);
//            if (c != null && c.moveToFirst()) {
//                bytesAndStatus[0] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
//                bytesAndStatus[1] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
//                bytesAndStatus[2] = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
//            }
//        } finally {
//            if (c != null) {
//                c.close();
//            }
//        }
//        return bytesAndStatus;
//    }
//
//
//    BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
////            int[] rs = getBytesAndStatus(downloadId);
////            String data = rs[0] + "," + rs[1] + "," + rs[2];
////            LogUtil.e(data);
////            progress.setText(data);
//
//
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_download);
//        ButterKnife.bind(this);
//
//
//        DownloadTask downloadTask = new DownloadTask(this, "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=21801&editionType=default&source=ucloud", 21801);
//
//
//        downloadTask.start(new DownloadTask.onProgressListener() {
//            @Override
//            public void before() {
//
//            }
//
//            @Override
//            public void onProgress(long total, long current) {
//
//                progress.setText("current=" + current + ",/total=" + total);
//            }
//
//            @Override
//            public void onComplete(Uri destUri) {
//                progress.append(",dest=" + destUri.toString());
//            }
//        });
//
//
////        DownloadManager.Request req = new DownloadManager.Request(Uri.parse("http://baobab.kaiyanapp.com/api/v1/playUrl?vid=21801&editionType=default&source=ucloud"));
////        req.setTitle("download");
////        req.setMimeType("application/cn.samir.online.download.file");
////        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED | DownloadManager.Request.VISIBILITY_VISIBLE);
////        req.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "videos_21801.apk");
////        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
////        downloadId = downloadManager.enqueue(req);
//
//
//        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
//        registerReceiver(receiver, filter);
//
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(receiver);
//    }
//}
