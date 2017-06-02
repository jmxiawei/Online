package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2017-03-10 15:43:2
 *
 * @author www.jsons.cn
 * @website http://www.jsons.cn/json2java/
 */
public class Consumption implements Parcelable {
    /**
     * collectionCount: 232,
     * shareCount: 529,
     * replyCount: 6
     */

    protected int collectionCount;

    protected int shareCount;

    protected int replyCount;

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.collectionCount);
        dest.writeInt(this.shareCount);
        dest.writeInt(this.replyCount);
    }

    public Consumption() {
    }

    protected Consumption(Parcel in) {
        this.collectionCount = in.readInt();
        this.shareCount = in.readInt();
        this.replyCount = in.readInt();
    }

    public static final Parcelable.Creator<Consumption> CREATOR = new Parcelable.Creator<Consumption>() {
        public Consumption createFromParcel(Parcel source) {
            return new Consumption(source);
        }

        public Consumption[] newArray(int size) {
            return new Consumption[size];
        }
    };
}