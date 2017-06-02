package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2017-03-10 15:43:2
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Provider implements Parcelable {

    private String name;
    private String alias;
    private String icon;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setAlias(String alias) {
         this.alias = alias;
     }
     public String getAlias() {
         return alias;
     }

    public void setIcon(String icon) {
         this.icon = icon;
     }
     public String getIcon() {
         return icon;
     }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.alias);
        dest.writeString(this.icon);
    }

    public Provider() {
    }

    protected Provider(Parcel in) {
        this.name = in.readString();
        this.alias = in.readString();
        this.icon = in.readString();
    }

    public static final Parcelable.Creator<Provider> CREATOR = new Parcelable.Creator<Provider>() {
        public Provider createFromParcel(Parcel source) {
            return new Provider(source);
        }

        public Provider[] newArray(int size) {
            return new Provider[size];
        }
    };
}