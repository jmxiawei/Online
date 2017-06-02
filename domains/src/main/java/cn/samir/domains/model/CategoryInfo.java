package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaw on 2017/5/10 0010.
 */

public class CategoryInfo implements Parcelable {

    /**
     * dataType : CategoryInfo
     * id : 36
     * name : 生活
     * description : 匠心、健康、生活感悟
     * headerImage : http://img.kaiyanapp.com/a1a1bf7ed3ac906ee4e8925218dd9fbe.png
     * actionUrl : eyepetizer://category/36/?title=%E7%94%9F%E6%B4%BB
     * follow : {"itemType":"category","itemId":36,"followed":false}
     */

    private String dataType;
    private int id;
    private String name;
    private String description;
    private String headerImage;
    private String actionUrl;
    private Follow follow;

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }


    public String getDataType() {
        return dataType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dataType);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.headerImage);
        dest.writeString(this.actionUrl);
        dest.writeParcelable(this.follow, 0);
    }

    public CategoryInfo() {
    }

    protected CategoryInfo(Parcel in) {
        this.dataType = in.readString();
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.headerImage = in.readString();
        this.actionUrl = in.readString();
        this.follow = in.readParcelable(Follow.class.getClassLoader());
    }

    public static final Parcelable.Creator<CategoryInfo> CREATOR = new Parcelable.Creator<CategoryInfo>() {
        public CategoryInfo createFromParcel(Parcel source) {
            return new CategoryInfo(source);
        }

        public CategoryInfo[] newArray(int size) {
            return new CategoryInfo[size];
        }
    };
}
