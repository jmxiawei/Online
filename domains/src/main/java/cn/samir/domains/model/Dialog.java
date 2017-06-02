package cn.samir.domains.model;

import java.util.List;

/**
 * Created by xiaw on 2017/5/17 0017.
 */

public class Dialog {

    /**
     * id : 28
     * image : http://img.kaiyanapp.com/65399beef767ba67f58e122d057c38b3.png?imageMogr2/quality/60/format/jpg
     * title : 新增加观看记录
     * content : 登录之后，在「我的」-「观看记录」页面,
     * 可以看到和管理观看记录，该信息会同帐号保持同步。
     * buttonList : [{"text":"我知道啦","actionUrl":"","highlight":true}]
     * showTimes : 1
     * animateImage : null
     * animateType : leftToRight
     * animateTimeLength : 0
     * actionUrl : null
     * adTrack : null
     */

    private int id;
    private String image;
    private String title;
    private String content;
    private int showTimes;
    private Object animateImage;
    private String animateType;
    private int animateTimeLength;
    private Object actionUrl;
    private Object adTrack;
    private List<ButtonListEntity> buttonList;

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setShowTimes(int showTimes) {
        this.showTimes = showTimes;
    }

    public void setAnimateImage(Object animateImage) {
        this.animateImage = animateImage;
    }

    public void setAnimateType(String animateType) {
        this.animateType = animateType;
    }

    public void setAnimateTimeLength(int animateTimeLength) {
        this.animateTimeLength = animateTimeLength;
    }

    public void setActionUrl(Object actionUrl) {
        this.actionUrl = actionUrl;
    }

    public void setAdTrack(Object adTrack) {
        this.adTrack = adTrack;
    }

    public void setButtonList(List<ButtonListEntity> buttonList) {
        this.buttonList = buttonList;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getShowTimes() {
        return showTimes;
    }

    public Object getAnimateImage() {
        return animateImage;
    }

    public String getAnimateType() {
        return animateType;
    }

    public int getAnimateTimeLength() {
        return animateTimeLength;
    }

    public Object getActionUrl() {
        return actionUrl;
    }

    public Object getAdTrack() {
        return adTrack;
    }

    public List<ButtonListEntity> getButtonList() {
        return buttonList;
    }

    public static class ButtonListEntity {
        /**
         * text : 我知道啦
         * actionUrl :
         * highlight : true
         */

        private String text;
        private String actionUrl;
        private boolean highlight;

        public void setText(String text) {
            this.text = text;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public void setHighlight(boolean highlight) {
            this.highlight = highlight;
        }

        public String getText() {
            return text;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public boolean getHighlight() {
            return highlight;
        }
    }
}
