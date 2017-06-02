package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2017-03-10 15:43:2
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Cover implements Parcelable {

    private String feed;
    private String detail;
    private String blurred;
    private String sharing;
    public void setFeed(String feed) {
         this.feed = feed;
     }
    public String getFeed() {
         return feed;
     }

    public void setDetail(String detail) {
         this.detail = detail;
     }
    public String getDetail() {
         return detail;
     }

    public void setBlurred(String blurred) {
         this.blurred = blurred;
     }
    public String getBlurred() {
         return blurred;
     }

    public void setSharing(String sharing) {
         this.sharing = sharing;
     }
    public String getSharing() {
         return sharing;
     }

    @Override
    public String toString() {
        return "Cover{" +
                "feed='" + feed + '\'' +
                ", detail='" + detail + '\'' +
                ", blurred='" + blurred + '\'' +
                ", sharing='" + sharing + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.feed);
        dest.writeString(this.detail);
        dest.writeString(this.blurred);
        dest.writeString(this.sharing);
    }

    public Cover() {
    }

    protected Cover(Parcel in) {
        this.feed = in.readString();
        this.detail = in.readString();
        this.blurred = in.readString();
        this.sharing = in.readString();
    }

    public static final Parcelable.Creator<Cover> CREATOR = new Parcelable.Creator<Cover>() {
        public Cover createFromParcel(Parcel source) {
            return new Cover(source);
        }

        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };
}