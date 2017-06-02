package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiawei on 2017/4/29.
 */

public class ParcelableString implements Parcelable {


    private String data;

    public ParcelableString(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.data);
    }

    public ParcelableString() {
    }

    protected ParcelableString(Parcel in) {
        this.data = in.readString();
    }

    public static final Parcelable.Creator<ParcelableString> CREATOR = new Parcelable.Creator<ParcelableString>() {
        public ParcelableString createFromParcel(Parcel source) {
            return new ParcelableString(source);
        }

        public ParcelableString[] newArray(int size) {
            return new ParcelableString[size];
        }
    };
}
