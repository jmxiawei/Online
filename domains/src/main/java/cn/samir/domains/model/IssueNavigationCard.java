package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaw on 2017/5/2 0002.
 */
public class IssueNavigationCard implements Parcelable {
    /**
     * "dataType": "IssueNavigationCard",
     * "id": 1493654400000,
     * "date": 1493654400000,
     * "total": 8,
     * "playedCount": 0,
     * "actionUrl": "eyepetizer://issue?date=1493654400000",
     * "adTrack": null
     */

    private String dataType;

    private long id;
    private long date;
    private int total;
    private int playedCount;
    private String actionUrl;
    private String adTrack;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPlayedCount() {
        return playedCount;
    }

    public void setPlayedCount(int playedCount) {
        this.playedCount = playedCount;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dataType);
        dest.writeLong(this.id);
        dest.writeLong(this.date);
        dest.writeInt(this.total);
        dest.writeInt(this.playedCount);
        dest.writeString(this.actionUrl);
        dest.writeString(this.adTrack);
    }

    public IssueNavigationCard() {
    }

    protected IssueNavigationCard(Parcel in) {
        this.dataType = in.readString();
        this.id = in.readLong();
        this.date = in.readLong();
        this.total = in.readInt();
        this.playedCount = in.readInt();
        this.actionUrl = in.readString();
        this.adTrack = in.readString();
    }

    public static final Creator<IssueNavigationCard> CREATOR = new Creator<IssueNavigationCard>() {
        public IssueNavigationCard createFromParcel(Parcel source) {
            return new IssueNavigationCard(source);
        }

        public IssueNavigationCard[] newArray(int size) {
            return new IssueNavigationCard[size];
        }
    };
}
