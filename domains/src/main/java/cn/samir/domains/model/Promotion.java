package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaw on 2017/3/20 0020.
 */

public class Promotion implements Parcelable {
    public String text;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
    }

    public Promotion() {
    }

    protected Promotion(Parcel in) {
        this.text = in.readString();
    }

    public static final Parcelable.Creator<Promotion> CREATOR = new Parcelable.Creator<Promotion>() {
        public Promotion createFromParcel(Parcel source) {
            return new Promotion(source);
        }

        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    };
}
