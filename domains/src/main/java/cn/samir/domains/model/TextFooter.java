package cn.samir.domains.model;

/**
 * Created by xiawei on 2017/3/14.
 */

public class TextFooter{

/**
 *
 * dataType: "TextFooter",
 text: "查看往期编辑精选",
 font: "normal",
 actionUrl: "eyepetizer://feed?issueIndex=1",
 adTrack: null
 */

    public String dataType;
    public String text;
    public String normal;
    public String actionUrl;
    public String adTrack;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
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
        return "TextFooter{" +
                "dataType='" + dataType + '\'' +
                ", text='" + text + '\'' +
                ", normal='" + normal + '\'' +
                ", actionUrl='" + actionUrl + '\'' +
                ", adTrack='" + adTrack + '\'' +
                '}';
    }
}
