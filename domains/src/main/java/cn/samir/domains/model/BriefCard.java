package cn.samir.domains.model;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class BriefCard {
    /**
     * dataType: "BriefCard",
     * id: 280,
     * icon: "http://img.kaiyanapp.com/a442b4028364e9e083918c0e64dcac48.png",
     * iconType: "round",
     * title: "瞎看什么",
     * subTitle: null,
     * description: "别用肉眼看电影了，我们教你用别的眼看。",
     * actionUrl: "eyepetizer://pgc/detail/280/?title=%E7%9E%8E%E7%9C%8B%E4%BB%80%E4%B9%88",
     * adTrack: null,
     * follow: {
     * itemType: "author",
     * itemId: 280,
     * followed: false
     * }
     */
    int id;
    String dataType;
    String icon;
    String iconType;
    String title;
    String subTitle;
    String description;
    String actionUrl;
    String adTrack;
    Follow follow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }
}
