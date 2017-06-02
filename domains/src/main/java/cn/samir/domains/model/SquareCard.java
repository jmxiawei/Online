package cn.samir.domains.model;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class SquareCard {


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

    public boolean isShade() {
        return shade;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
    }

    @Override
    public String toString() {
        return "SquareCardSub{" +
                "dataType='" + dataType + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", actionUrl='" + actionUrl + '\'' +
                ", shade=" + shade +
                '}';
    }

    /**
     * dataType: "SquareCard",
     * id: 8,
     * title: "#预告",
     * image: "http://img.kaiyanapp.com/003829087e85ce7310b2210d9575ce67.jpeg",
     * actionUrl: "eyepetizer://category/8/?title=%E9%A2%84%E5%91%8A",
     * shade: true,
     * adTrack: null
     */


    String dataType;
    int id;
    String title;
    String image;
    String actionUrl;
    boolean shade = false;
}
