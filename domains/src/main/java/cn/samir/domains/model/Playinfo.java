package cn.samir.domains.model;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto-generated: 2017-03-10 15:43:2
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Playinfo implements Parcelable {

    private int height;
    private int width;
    private List<Urllist> urllist;
    private String name;
    private String type;
    private String url;
    public void setHeight(int height) {
         this.height = height;
     }
     public int getHeight() {
         return height;
     }

    public void setWidth(int width) {
         this.width = width;
     }
     public int getWidth() {
         return width;
     }

    public void setUrllist(List<Urllist> urllist) {
         this.urllist = urllist;
     }
     public List<Urllist> getUrllist() {
         return urllist;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    @Override
    public String toString() {
        return "Playinfo{" +
                "height=" + height +
                ", width=" + width +
                ", urllist=" + urllist +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.height);
        dest.writeInt(this.width);
        dest.writeList(this.urllist);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.url);
    }

    public Playinfo() {
    }

    protected Playinfo(Parcel in) {
        this.height = in.readInt();
        this.width = in.readInt();
        this.urllist = new ArrayList<Urllist>();
        in.readList(this.urllist, List.class.getClassLoader());
        this.name = in.readString();
        this.type = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Playinfo> CREATOR = new Parcelable.Creator<Playinfo>() {
        public Playinfo createFromParcel(Parcel source) {
            return new Playinfo(source);
        }

        public Playinfo[] newArray(int size) {
            return new Playinfo[size];
        }
    };
}