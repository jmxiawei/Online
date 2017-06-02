package cn.samir.domains.model;

/**
 * Created by xiawei on 2017/3/14.
 */

public class TextHeader {

    /**
     * dataType: "TextHeader",
     * text: "- Mar. 13, Supper -",
     * font: "lobster",
     * adTrack: null
     */


    public String dataType;
    public String text;
    public String font;


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

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

}
