package cn.samir.domains.model;

/**
 * Created by xiaw on 2017/4/10 0010.
 */

public class TextCard {

    public static String HEADER1 = "header1";//footer1
    public static String HEADER2= "header2";//footer1
    public static String HEADER3 = "header3";//footer1


    public static String FOOTER1 = "footer1";
    /**
     * type: "textCard",
     * data: {
     * dataType: "TextCard",
     * id: 0,
     * type: "footer1",
     * text: "查看往期编辑精选",
     * actionUrl: "eyepetizer://feed?issueIndex=1",
     * adTrack: null
     * },
     * tag: null
     */

    String dataType;
    String type;
    String id;
    String text;
    String actionUrl;
    String adTrack;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "TextCard{" +
                "dataType='" + dataType + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", actionUrl='" + actionUrl + '\'' +
                ", adTrack='" + adTrack + '\'' +
                '}';
    }
}
