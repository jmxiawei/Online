package cn.samir.domains.model;

/**
 * Created by xiawei on 2017/3/10.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * {"dataType":"Banner","id":308,"title":"","description":"",
 * "image":"http://img.kaiyanapp.com/fc652727a588c237b5d7df0c3e710d3e.jpeg?imageMogr2/quality/60/format/jpg",
 * "actionUrl":"eyepetizer://webview/?title=%E5%BC%80%E7%9C%BC%E8%B0%83%E7%A0%94&url=https%3A%2F%2Fwww.diaochapai.com%2Fsurvey2501704%3Fext%3D0_",
 * "adTrack":null,"shade":false,"label":{"text":null,"card":null,"detail":null}}
 */
public class Banner implements Parcelable {

    protected String dataType;
    protected int id;
    protected String title;
    protected String description;
    protected String image;
    protected String actionUrl;
    protected ArrayList<AdOrganizition> adTrack;
    protected boolean shade;
    protected Label label;


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public ArrayList<AdOrganizition> getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(ArrayList<AdOrganizition> adTrack) {
        this.adTrack = adTrack;
    }

    public boolean isShade() {
        return shade;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "dataType='" + dataType + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", actionUrl='" + actionUrl + '\'' +
                ", adTrack='" + adTrack + '\'' +
                ", shade=" + shade +
                ", label=" + label +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dataType);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.image);
        dest.writeString(this.actionUrl);
        dest.writeTypedList(adTrack);
        dest.writeByte(shade ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.label, 0);
    }

    public Banner() {
    }

    protected Banner(Parcel in) {
        this.dataType = in.readString();
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        this.actionUrl = in.readString();
        this.adTrack = in.createTypedArrayList(AdOrganizition.CREATOR);
        this.shade = in.readByte() != 0;
        this.label = in.readParcelable(Label.class.getClassLoader());
    }

    public static final Parcelable.Creator<Banner> CREATOR = new Parcelable.Creator<Banner>() {
        public Banner createFromParcel(Parcel source) {
            return new Banner(source);
        }

        public Banner[] newArray(int size) {
            return new Banner[size];
        }
    };
}
