package cn.samir.domains.model;

import java.util.List;

/**
 * Created by xiaw on 2017/5/26 0026.
 */

public class MessageListModel {

    /**
     * messageList : [{"id":383741,"title":"官方通知","content":"「麻烦家族」送票活动 12 日中奖用户已经公布~ 快看看自己有没有中奖吧~！点击查看详情 >>","date":1494613261000,"actionUrl":"eyepetizer://webview/?title=%E8%8E%B7%E5%A5%96%E7%94%A8%E6%88%B7%E9%80%9A%E7%9F%A5&url=https%3A%2F%2Fjinshuju.net%2Ff%2FZtU0lb%3Fx_field_1%3D0_7c7e8036375b40abb81c7f9a5f73293ccb6969b7","icon":"http://img.wdjimg.com/image/video/418d281e65bf010c38c7b07bdd7b6a94_0_0.png"},{"id":190074,"title":"官方通知","content":" 开眼调研：开眼即将上线更多分类内容，希望您能用 2min 填写问卷，让我们更能了解您的喜好，之后的视频推荐和推送通知也会更符合您的口味~ (๑\u2022̀ω\u2022́๑)","date":1489334399000,"actionUrl":"eyepetizer://webview/?title=%E5%BC%80%E7%9C%BC%E8%B0%83%E7%A0%94&url=https%3A%2F%2Fwww.diaochapai.com%2Fsurvey2501704%3Fext%3D0_7c7e8036375b40abb81c7f9a5f73293ccb6969b7","icon":"http://img.wdjimg.com/image/video/418d281e65bf010c38c7b07bdd7b6a94_0_0.png"},{"id":96959,"title":"活动通知","content":"2016 开眼年终盘点，重拾你的记忆碎片！即刻点击这条消息参与活动，回顾过去一年你与开眼的点点滴滴吧！\n","date":1484222400000,"actionUrl":"eyepetizer://webview/?title=%E5%BC%80%E7%9C%BC%20Eyepetizer%20%E5%B9%B4%E7%BB%88%E7%9B%98%E7%82%B9&url=http%3A%2F%2Fwww.eyepetizer.net%2Fcampaign%2Fcylinder.html%3Fuid%3D0%26udid%3D7c7e8036375b40abb81c7f9a5f73293ccb6969b7%26shareable%3Dtrue","icon":"http://img.wdjimg.com/image/video/8a47b9fd167779342188b518f38775ef_0_0.png"}]
     * updateTime : 1494613261000
     * nextPageUrl : null
     */

    private long updateTime;
    private String nextPageUrl;
    private List<MessageListEntity> messageList;

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public void setMessageList(List<MessageListEntity> messageList) {
        this.messageList = messageList;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public List<MessageListEntity> getMessageList() {
        return messageList;
    }

    public static class MessageListEntity  extends BaseModel{
        /**
         * id : 383741
         * title : 官方通知
         * content : 「麻烦家族」送票活动 12 日中奖用户已经公布~ 快看看自己有没有中奖吧~！点击查看详情 >>
         * date : 1494613261000
         * actionUrl : eyepetizer://webview/?title=%E8%8E%B7%E5%A5%96%E7%94%A8%E6%88%B7%E9%80%9A%E7%9F%A5&url=https%3A%2F%2Fjinshuju.net%2Ff%2FZtU0lb%3Fx_field_1%3D0_7c7e8036375b40abb81c7f9a5f73293ccb6969b7
         * icon : http://img.wdjimg.com/image/video/418d281e65bf010c38c7b07bdd7b6a94_0_0.png
         */

        private int id;
        private String title;
        private String content;
        private long date;
        private String actionUrl;
        private String icon;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public long getDate() {
            return date;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public String getIcon() {
            return icon;
        }
    }
}
