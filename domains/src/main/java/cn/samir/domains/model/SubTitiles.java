package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaw on 2017/3/28 0028.
 */

public class SubTitiles implements Parcelable {

    public String type;
    public String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.url);
    }

    public SubTitiles() {
    }

    protected SubTitiles(Parcel in) {
        this.type = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<SubTitiles> CREATOR = new Parcelable.Creator<SubTitiles>() {
        public SubTitiles createFromParcel(Parcel source) {
            return new SubTitiles(source);
        }

        public SubTitiles[] newArray(int size) {
            return new SubTitiles[size];
        }
    };
}
