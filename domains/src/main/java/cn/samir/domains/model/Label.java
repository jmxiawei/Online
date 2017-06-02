package cn.samir.domains.model;

/**
 * Created by xiawei on 2017/3/10.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * label":{"text":null,"card":null,"detail":null}}
 */
public class Label implements Parcelable {


    protected String text;
    protected String card;
    protected String detail;
    protected String actionUrl;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    @Override
    public String toString() {
        return "Label{" +
                "text='" + text + '\'' +
                ", card='" + card + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.card);
        dest.writeString(this.detail);
        dest.writeString(this.actionUrl);
    }

    public Label() {
    }

    protected Label(Parcel in) {
        this.text = in.readString();
        this.card = in.readString();
        this.detail = in.readString();
        this.actionUrl = in.readString();
    }

    public static final Parcelable.Creator<Label> CREATOR = new Parcelable.Creator<Label>() {
        public Label createFromParcel(Parcel source) {
            return new Label(source);
        }

        public Label[] newArray(int size) {
            return new Label[size];
        }
    };
}
