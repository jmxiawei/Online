package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiawei on 2017/3/14.
 */

public class Follow implements Parcelable {

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    /**
     * itemType: "author",
     itemId: 339,
     followed: false
     */


    public String itemType;
    public int itemId;
    public boolean followed;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemType);
        dest.writeInt(this.itemId);
        dest.writeByte(followed ? (byte) 1 : (byte) 0);
    }

    public Follow() {
    }

    protected Follow(Parcel in) {
        this.itemType = in.readString();
        this.itemId = in.readInt();
        this.followed = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Follow> CREATOR = new Parcelable.Creator<Follow>() {
        public Follow createFromParcel(Parcel source) {
            return new Follow(source);
        }

        public Follow[] newArray(int size) {
            return new Follow[size];
        }
    };
}
