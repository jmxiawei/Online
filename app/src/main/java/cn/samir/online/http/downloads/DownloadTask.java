package cn.samir.online.http.downloads;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import com.orm.SugarRecord;

/**
 * 我跟你说啊，你这个说说，一看就知道这是受过高等教育的才能说得出来令人深思而欣慰，
 * 以平实的真情打动读者，语句流畅，一气呵成 心理刻画和细节描写都很成功，给人回味之感！
 * 从文学的角度来讲选材很是新颖，角度清晰可见，语言平实而不失采，简洁而富有寓意，堪称现代说说之典范！
 * 这条说说 平淡中显示出不凡的文学功底，可谓是字字珠玑，句句经典，达到了我等可望而不可及的高度，就艺术的角度而言，
 * 这条说说还有待提高，但它的意义却远远大于成功本身。真不愧为无厘界新一代开山祖师！逐字地看完你的说说后，我的心久久 不能平静！
 * 这世间怎么可能还有如此精辟的说说？我不敢相信自己的眼睛。自从改革开放以后，我就以为再也不会有任何说说能打动我，没想到今天看到这条如此精妙绝伦的说说，
 * 你让我深深理解了 ‘有时候谁都没错，只是不懂得换位思考而已这句话，在看完说说后，我不敢轻易回复，我担心我庸俗不堪的语言会玷污了这世间少有的说说。
 * 但我还是回复了，因为我觉得如果不能再如此精彩的说说后面留下自己的足迹，那将会成为我一生的遗憾。但是我不能这么自私，即便遗憾也不能自私，
 * 所以我决定用挖掘机来埋葬掉我的足迹，那么问题出现了，挖掘机哪家强？
 * Created by xiaw on 2017/5/15 0015.
 */
public class DownloadTask {

    private android.app.DownloadManager downloadManager;
    private Context context;
    private long downloadId;
    private Uri mUri;
    private Uri mDestUri;
    private onProgressListener onProgressListener;
    private android.app.DownloadManager.Request mRequest;

    private Handler mH;

    private Object[] params;

    private DownloadRecord downloadRecord;


    public DownloadTask(Context context, String url, Object... params) {
        this.context = context;
        this.params = params;
        setUrl(url);
        downloadManager = (android.app.DownloadManager) context.getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
        mH = new Handler(Looper.getMainLooper());
    }

    public void setOnProgressListener(DownloadTask.onProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    public void setUrl(String url) {
        mUri = Uri.parse(url);
    }


    public long getDownloadId() {
        return downloadId;
    }


    public void start(DownloadTask.onProgressListener onProgressListener) {
        setOnProgressListener(onProgressListener);
        Uri tempUri = DownloadUtils.isExist(context, params);
        if (null == tempUri) {
            //不存在去下载
            mRequest = new android.app.DownloadManager.Request(mUri);
            mDestUri = DownloadUtils.buildDestUri(context, params);
            mRequest.setDestinationUri(mDestUri);
            mRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
            mRequest.allowScanningByMediaScanner();
            downloadId = downloadManager.enqueue(mRequest);
            downloadRecord = new DownloadRecord(downloadId, mUri.toString());
            downloadRecord.setLocalPath(mDestUri.toString());
            downloadRecord.setVideoId((int) params[0]);
            DownloadQueryTask downloadQueryTask = new DownloadQueryTask(context,downloadRecord);
            downloadQueryTask.startQuery(onProgressListener);
        } else {
            success(tempUri);
        }
    }

    private void success(Uri successUri) {
        if (downloadRecord != null) {
            downloadRecord.setSuccess(true);
            SugarRecord.update(downloadRecord);
        }
        if (onProgressListener != null) {
            onProgressListener.onComplete(successUri);
        }
    }

    /**
     * 下载进度
     */
    public interface onProgressListener {
        void before();

        void onProgress(long total, long current);

        void onComplete(Uri destUri);
    }
}
