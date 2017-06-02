package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiawei on 2017/3/14.
 */

public class Tag implements Parcelable {

    public int id;
    public String name;
    public String actionUrl;
    public String adTrack;

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", actionUrl='" + actionUrl + '\'' +
                ", adTrack='" + adTrack + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.actionUrl);
        dest.writeString(this.adTrack);
    }

    public Tag() {
    }

    protected Tag(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.actionUrl = in.readString();
        this.adTrack = in.readString();
    }

    public static final Parcelable.Creator<Tag> CREATOR = new Parcelable.Creator<Tag>() {
        public Tag createFromParcel(Parcel source) {
            return new Tag(source);
        }

        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };
}
