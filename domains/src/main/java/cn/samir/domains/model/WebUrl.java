package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2017-03-10 15:43:2
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class WebUrl implements Parcelable {

    private String raw;
    private String forweibo;
    public void setRaw(String raw) {
         this.raw = raw;
     }
     public String getRaw() {
         return raw;
     }

    public void setForweibo(String forweibo) {
         this.forweibo = forweibo;
     }
     public String getForweibo() {
         return forweibo;
     }

    @Override
    public String toString() {
        return "WebUrl{" +
                "raw=" + raw +
                ", forweibo='" + forweibo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.raw);
        dest.writeString(this.forweibo);
    }

    public WebUrl() {
    }

    protected WebUrl(Parcel in) {
        this.raw = in.readString();
        this.forweibo = in.readString();
    }

    public static final Parcelable.Creator<WebUrl> CREATOR = new Parcelable.Creator<WebUrl>() {
        public WebUrl createFromParcel(Parcel source) {
            return new WebUrl(source);
        }

        public WebUrl[] newArray(int size) {
            return new WebUrl[size];
        }
    };
}