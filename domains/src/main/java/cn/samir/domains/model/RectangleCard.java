package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by xiaw on 2017/5/5 0005.
 */

public class RectangleCard implements Parcelable {
    /**
     * dataType : RectangleCard
     * id : 1
     * title :
     * image : http://img.kaiyanapp.com/f6765ae9bcd4551ce40f10fe342b681c.jpeg?imageMogr2/quality/60
     * actionUrl : eyepetizer://tag/658/?title=360%C2%B0%E5%85%A8%E6%99%AF
     * shade : false
     * adTrack : null
     */

    private String dataType;
    private int id;
    private String title;
    private String image;
    private String actionUrl;
    private boolean shade;
    private ArrayList<AdOrganizition> adTrack;

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
    }

    public void setAdTrack(ArrayList<AdOrganizition> adTrack) {
        this.adTrack = adTrack;
    }

    public String getDataType() {
        return dataType;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public boolean getShade() {
        return shade;
    }

    public ArrayList<AdOrganizition> getAdTrack() {
        return adTrack;
    }

    /**
     *
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dataType);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.image);
        dest.writeString(this.actionUrl);
        dest.writeByte(shade ? (byte) 1 : (byte) 0);
        dest.writeTypedList(adTrack);
    }

    public RectangleCard() {
    }

    protected RectangleCard(Parcel in) {
        this.dataType = in.readString();
        this.id = in.readInt();
        this.title = in.readString();
        this.image = in.readString();
        this.actionUrl = in.readString();
        this.shade = in.readByte() != 0;
        this.adTrack = in.createTypedArrayList(AdOrganizition.CREATOR);
    }

    public static final Parcelable.Creator<RectangleCard> CREATOR = new Parcelable.Creator<RectangleCard>() {
        public RectangleCard createFromParcel(Parcel source) {
            return new RectangleCard(source);
        }

        public RectangleCard[] newArray(int size) {
            return new RectangleCard[size];
        }
    };
}
