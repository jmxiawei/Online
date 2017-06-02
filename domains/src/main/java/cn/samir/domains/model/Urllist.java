package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2017-03-10 15:43:2
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Urllist implements Parcelable {

    private String name;
    private String url;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    @Override
    public String toString() {
        return "Urllist{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    public Urllist() {
    }

    protected Urllist(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Urllist> CREATOR = new Parcelable.Creator<Urllist>() {
        public Urllist createFromParcel(Parcel source) {
            return new Urllist(source);
        }

        public Urllist[] newArray(int size) {
            return new Urllist[size];
        }
    };
}