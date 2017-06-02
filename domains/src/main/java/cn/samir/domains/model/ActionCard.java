package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class ActionCard implements Parcelable {
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }

    @Override
    public String toString() {
        return "ActionCard{" +
                "dataType='" + dataType + '\'' +
                ", id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", actionUrl='" + actionUrl + '\'' +
                ", adTrack='" + adTrack + '\'' +
                '}';
    }

    /**
     * dataType: "ActionCard",
     id: 141,
     text: "查看全部",
     actionUrl: "eyepetizer://webview/?title=%E6%AC%A2%E8%BF%8E%E6%9D%A5%E5%88%B0%E6%96%87%E6%98%8E%E4%B8%96%E7%95%8C%EF%BC%8C%E6%9C%89%E7%82%B9%E9%85%B7%E7%9A%84%E7%A4%BC%E4%BB%AA%E5%AE%A3%E4%BC%A0&url=http%3A%2F%2Fwww.eyepetizer.net%2Fvideos_article.html%3Fnid%3D141%26shareable%3Dtrue",
     adTrack: null
     */


    public String dataType;
    public String id;
    public String text;
    public String actionUrl;
    public String adTrack;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dataType);
        dest.writeString(this.id);
        dest.writeString(this.text);
        dest.writeString(this.actionUrl);
        dest.writeString(this.adTrack);
    }

    public ActionCard() {
    }

    protected ActionCard(Parcel in) {
        this.dataType = in.readString();
        this.id = in.readString();
        this.text = in.readString();
        this.actionUrl = in.readString();
        this.adTrack = in.readString();
    }

    public static final Parcelable.Creator<ActionCard> CREATOR = new Parcelable.Creator<ActionCard>() {
        public ActionCard createFromParcel(Parcel source) {
            return new ActionCard(source);
        }

        public ActionCard[] newArray(int size) {
            return new ActionCard[size];
        }
    };
}
