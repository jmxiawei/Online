/**
  * Copyright 2017 bejson.com 
  */
package cn.samir.domains.model.configs;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2017-05-03 16:25:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Autocache implements Parcelable {


    public boolean isForceOff() {
        return forceOff;
    }

    public int getVideoNum() {
        return videoNum;
    }

    public String getVersion() {
        return version;
    }

    public int getOffset() {
        return offset;
    }

    private boolean forceOff;
    private int videoNum;
    private String version;
    private int offset;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(forceOff ? (byte) 1 : (byte) 0);
        dest.writeInt(this.videoNum);
        dest.writeString(this.version);
        dest.writeInt(this.offset);
    }

    public Autocache() {
    }

    protected Autocache(Parcel in) {
        this.forceOff = in.readByte() != 0;
        this.videoNum = in.readInt();
        this.version = in.readString();
        this.offset = in.readInt();
    }

    public static final Parcelable.Creator<Autocache> CREATOR = new Parcelable.Creator<Autocache>() {
        public Autocache createFromParcel(Parcel source) {
            return new Autocache(source);
        }

        public Autocache[] newArray(int size) {
            return new Autocache[size];
        }
    };
}