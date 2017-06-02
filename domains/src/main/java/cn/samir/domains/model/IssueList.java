package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaw on 2017/5/4 0004.
 */

public class IssueList implements Parcelable {

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }


    public ArrayList getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList itemList) {
        this.itemList = itemList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * "releaseTime": 1493859600000,
     * "type": "morning",
     * "date": 1493859600000,
     * "publishTime": 1493859600000,
     * "itemList": [],
     * "count": 7
     */
    public long releaseTime;
    public long date;
    public String type;
    public long publishTime;
    public ArrayList itemList;
    public int count;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.releaseTime);
        dest.writeLong(this.date);
        dest.writeString(this.type);
        dest.writeLong(this.publishTime);
        dest.writeList(this.itemList);
        dest.writeInt(this.count);
    }

    public IssueList() {
    }

    protected IssueList(Parcel in) {
        this.releaseTime = in.readLong();
        this.date = in.readLong();
        this.type = in.readString();
        this.publishTime = in.readLong();
        this.itemList = new ArrayList<BaseModel>();
        in.readList(this.itemList, List.class.getClassLoader());
        this.count = in.readInt();
    }

    public static final Parcelable.Creator<IssueList> CREATOR = new Parcelable.Creator<IssueList>() {
        public IssueList createFromParcel(Parcel source) {
            return new IssueList(source);
        }

        public IssueList[] newArray(int size) {
            return new IssueList[size];
        }
    };
}
