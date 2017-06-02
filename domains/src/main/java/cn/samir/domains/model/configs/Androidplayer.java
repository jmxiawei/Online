/**
 * Copyright 2017 bejson.com
 */
package cn.samir.domains.model.configs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Auto-generated: 2017-05-03 16:25:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Androidplayer implements Parcelable {


    private List<String> playerlist;
    private String version;

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.playerlist);
        dest.writeString(this.version);
    }

    public Androidplayer() {
    }

    protected Androidplayer(Parcel in) {
        this.playerlist = in.createStringArrayList();
        this.version = in.readString();
    }

    public static final Parcelable.Creator<Androidplayer> CREATOR = new Parcelable.Creator<Androidplayer>() {
        public Androidplayer createFromParcel(Parcel source) {
            return new Androidplayer(source);
        }

        public Androidplayer[] newArray(int size) {
            return new Androidplayer[size];
        }
    };
}