package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaw on 2017/4/6 0006.
 */

public class Tab implements Parcelable {
    public String name;
    public int id;
    public String apiUrl;

    @Override
    public String toString() {
        return "Tab{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", apiUrl='" + apiUrl + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeString(this.apiUrl);
    }

    public Tab() {
    }

    protected Tab(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
        this.apiUrl = in.readString();
    }

    public static final Parcelable.Creator<Tab> CREATOR = new Parcelable.Creator<Tab>() {
        public Tab createFromParcel(Parcel source) {
            return new Tab(source);
        }

        public Tab[] newArray(int size) {
            return new Tab[size];
        }
    };
}
