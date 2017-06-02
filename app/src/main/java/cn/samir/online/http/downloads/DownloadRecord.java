package cn.samir.online.http.downloads;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Column;

import java.util.List;

/**
 *
 *
 *
 * 注意事项：1  每行都加上 @Column(name=“xxx”)显示的说明列的名称
 *          2.必须显示一个无参数的构造函数
 *
 * Created by xiaw on 2017/5/24 0024.
 */

public class DownloadRecord extends SugarRecord {


    @Column(name="downloadId")
    @Expose
    public long downloadId;


    @Column(name="localPath")
    @Expose
    public String localPath;


    @Column(name="downloadUrl")
    @Expose
    public String downloadUrl;


    @Column(name="videoId")
    @Expose
    public int videoId;

    @Column(name="total")
    @Expose
    public int total;

    @Column(name="current")
    @Expose
    public int current;

    @Column(name = "iconUrl")
    public String iconUrl;

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    @Column(name="success")
    @Expose
    public boolean success = false;

    @Override
    public String toString() {
        return "DownloadRecord{" +
                "downloadId=" + downloadId +
                ", localPath='" + localPath + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", total=" + total +
                ", current=" + current +
                ", success=" + success +
                '}';
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getLocalPath() {
        return localPath;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DownloadRecord(long downloadId, String downloadUrl) {
        this.downloadId = downloadId;
        this.downloadUrl = downloadUrl;
    }

    public long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(long downloadId) {
        this.downloadId = downloadId;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public DownloadRecord() {
    }

    public static DownloadRecord find(String videoId) {
        List<DownloadRecord> rs = SugarRecord.find(DownloadRecord.class, " videoId =?", videoId);
        if (rs != null && rs.size() > 0) {
            return rs.get(0);
        } else {
            return null;
        }

    }

}
