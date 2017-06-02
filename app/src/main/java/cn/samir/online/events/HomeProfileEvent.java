package cn.samir.online.events;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaw on 2017/5/26 0026.
 */

public class HomeProfileEvent implements Event {
    private int id;

    public HomeProfileEvent(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
    }

    public HomeProfileEvent() {
    }

    protected HomeProfileEvent(Parcel in) {
        this.id = in.readInt();
    }

    public static final Creator<HomeProfileEvent> CREATOR = new Creator<HomeProfileEvent>() {
        public HomeProfileEvent createFromParcel(Parcel source) {
            return new HomeProfileEvent(source);
        }

        public HomeProfileEvent[] newArray(int size) {
            return new HomeProfileEvent[size];
        }
    };

    public int getId() {
        return id;
    }


    @Override
    public Parcelable getData() {
        return null;
    }
}
