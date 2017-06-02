package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiawei on 2017/3/14.
 */

public class Author implements Parcelable {
    /**
     * {
     id: 339,
     icon: "http://img.kaiyanapp.com/c7871e4436ed01d77e02a8b3633a5ed0.jpeg?imageMogr2/quality/60",
     name: "Netflix 视频网",
     description: "Netflix 是一间在世界多国提供网络视频点播的公司，并同时在美国经营单一费率邮寄 DVD 出租服务",
     link: "",
     latestReleaseTime: 1489453200000,
     videoNum: 166,
     adTrack: null,
     follow: {
     itemType: "author",
     itemId: 339,
     followed
     */


    public int id;
    public String icon;
    public String name;
    public String description;
    public String link;
    public long  latestReleaseTime;
    public int videoNum;
    public Follow follow;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.icon);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.link);
        dest.writeLong(this.latestReleaseTime);
        dest.writeInt(this.videoNum);
        dest.writeParcelable(this.follow, flags);
    }

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public long getLatestReleaseTime() {
        return latestReleaseTime;
    }

    public int getVideoNum() {
        return videoNum;
    }


    public Follow getFollow() {
        return follow;
    }

    public Author() {
    }

    protected Author(Parcel in) {
        this.id = in.readInt();
        this.icon = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.link = in.readString();
        this.latestReleaseTime = in.readLong();
        this.videoNum = in.readInt();
        this.follow = in.readParcelable(Follow.class.getClassLoader());
    }

    public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>() {
        public Author createFromParcel(Parcel source) {
            return new Author(source);
        }

        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
