package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiawei on 2017/3/20.
 */

public class AdOrganizition implements Parcelable {


    /**
     * organization: "guoshuang",
     viewUrl: "",
     clickUrl: "https://bsch.serving-sys.com/BurstingPipe/adServer.bs?cn=tf&c=20&mc=click&pli=20668738&PluID=0&ord=__TIME__&mb=1",
     playUrl:
     */

    public String organization;
    public String viewUrl;
    public String clickUrl;
    public String playUrl;
    public String version;

    @Override
    public String toString() {
        return "AdOrganizition{" +
                "organization='" + organization + '\'' +
                ", viewUrl='" + viewUrl + '\'' +
                ", clickUrl='" + clickUrl + '\'' +
                ", playUrl='" + playUrl + '\'' +
                '}';
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.organization);
        dest.writeString(this.viewUrl);
        dest.writeString(this.clickUrl);
        dest.writeString(this.playUrl);
    }

    public AdOrganizition() {
    }

    protected AdOrganizition(Parcel in) {
        this.organization = in.readString();
        this.viewUrl = in.readString();
        this.clickUrl = in.readString();
        this.playUrl = in.readString();
    }

    public static final Parcelable.Creator<AdOrganizition> CREATOR = new Parcelable.Creator<AdOrganizition>() {
        public AdOrganizition createFromParcel(Parcel source) {
            return new AdOrganizition(source);
        }

        public AdOrganizition[] newArray(int size) {
            return new AdOrganizition[size];
        }
    };
}
