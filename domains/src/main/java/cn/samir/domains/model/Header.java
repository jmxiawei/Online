package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by xiawei on 2017/3/14.
 */

public class Header implements Parcelable {

    public int id;
    public String title;
    public String font;

    public String icon;
    public String iconType;
    public String cover;
    public Label label;
    public String actionUrl;
    public ArrayList<String> iconList;
    public ArrayList<Label> labelList;
    public String description;


    public String subTitle;

    Follow follow;

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

    public ArrayList<Label> getLabelList() {
        return labelList;
    }

    public void setLabelList(ArrayList<Label> labelList) {
        this.labelList = labelList;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getCover() {
        return cover;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public ArrayList<String> getIconList() {
        return iconList;
    }

    public void setIconList(ArrayList<String> iconList) {
        this.iconList = iconList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * id: 0,
     * title: "热门作者 / 精选分类关注推荐",
     * font: "bold",
     * cover: "http://img.kaiyanapp.com/2d5b6990c04ec7a21d77779eabed7b3f.png",
     * label: null,
     * actionUrl: "eyepetizer://pgcs/all",
     * iconList: [
     * "http://img.kaiyanapp.com/5f58d6b2a6382c9b796093afc36abea7.jpeg?imageMogr2/quality/60",
     * "http://img.kaiyanapp.com/6e0126b63e72aeb293eceba32b7d2df1.jpeg?imageMogr2/quality/60/format/jpg",
     * "http://img.kaiyanapp.com/cb3bbf752698362941215499620e6259.jpeg?imageMogr2/quality/60/format/jpg"
     * ],
     * description: "TheUnreasonable / 嫩食记 / 御史房"
     */


    @Override
    public String toString() {
        return "Header{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", font='" + font + '\'' +
                ", cover='" + cover + '\'' +
                ", label='" + label + '\'' +
                ", actionUrl='" + actionUrl + '\'' +
                ", iconList=" + iconList +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.font);
        dest.writeString(this.icon);
        dest.writeString(this.iconType);
        dest.writeString(this.cover);
        dest.writeParcelable(this.label, 0);
        dest.writeString(this.actionUrl);
        dest.writeStringList(this.iconList);
        dest.writeTypedList(labelList);
        dest.writeString(this.description);
        dest.writeString(this.subTitle);
        dest.writeParcelable(this.follow, 0);
    }

    public Header() {
    }

    protected Header(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.font = in.readString();
        this.icon = in.readString();
        this.iconType = in.readString();
        this.cover = in.readString();
        this.label = in.readParcelable(Label.class.getClassLoader());
        this.actionUrl = in.readString();
        this.iconList = in.createStringArrayList();
        this.labelList = in.createTypedArrayList(Label.CREATOR);
        this.description = in.readString();
        this.subTitle = in.readString();
        this.follow = in.readParcelable(Follow.class.getClassLoader());
    }

    public static final Parcelable.Creator<Header> CREATOR = new Parcelable.Creator<Header>() {
        public Header createFromParcel(Parcel source) {
            return new Header(source);
        }

        public Header[] newArray(int size) {
            return new Header[size];
        }
    };
}
