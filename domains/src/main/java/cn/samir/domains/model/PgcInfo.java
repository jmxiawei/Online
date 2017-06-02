package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaw on 2017/5/19 0019.
 */

public class PgcInfo implements Parcelable {

    /**
     * dataType : PgcInfo
     * id : 761
     * icon : http://img.kaiyanapp.com/938b9fcbe750d6e54e0696c1d9036e49.jpeg?imageMogr2/quality/60/format/jpg
     * name : Mr. Hacker
     * brief : 34 个视频  /  4746 次收藏  /  2294 次分享
     * description : 跟 Hacker 先生学习更多生活小窍门、小技巧。
     * actionUrl : eyepetizer://pgc/detail/761/?title=Mr.%20Hacker
     * follow : {"itemType":"author","itemId":761,"followed":false}
     */

    private String dataType;
    private int id;
    private String icon;
    private String name;
    private String brief;
    private String description;
    private String actionUrl;
    private FollowEntity follow;

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public void setFollow(FollowEntity follow) {
        this.follow = follow;
    }

    public String getDataType() {
        return dataType;
    }

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getBrief() {
        return brief;
    }

    public String getDescription() {
        return description;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public FollowEntity getFollow() {
        return follow;
    }

    public static class FollowEntity implements Parcelable {
        /**
         * itemType : author
         * itemId : 761
         * followed : false
         */

        private String itemType;
        private int itemId;
        private boolean followed;

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public String getItemType() {
            return itemType;
        }

        public int getItemId() {
            return itemId;
        }

        public boolean getFollowed() {
            return followed;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.itemType);
            dest.writeInt(this.itemId);
            dest.writeByte(followed ? (byte) 1 : (byte) 0);
        }

        public FollowEntity() {
        }

        protected FollowEntity(Parcel in) {
            this.itemType = in.readString();
            this.itemId = in.readInt();
            this.followed = in.readByte() != 0;
        }

        public static final Creator<FollowEntity> CREATOR = new Creator<FollowEntity>() {
            public FollowEntity createFromParcel(Parcel source) {
                return new FollowEntity(source);
            }

            public FollowEntity[] newArray(int size) {
                return new FollowEntity[size];
            }
        };
    }

    public PgcInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dataType);
        dest.writeInt(this.id);
        dest.writeString(this.icon);
        dest.writeString(this.name);
        dest.writeString(this.brief);
        dest.writeString(this.description);
        dest.writeString(this.actionUrl);
        dest.writeParcelable(this.follow, 0);
    }

    protected PgcInfo(Parcel in) {
        this.dataType = in.readString();
        this.id = in.readInt();
        this.icon = in.readString();
        this.name = in.readString();
        this.brief = in.readString();
        this.description = in.readString();
        this.actionUrl = in.readString();
        this.follow = in.readParcelable(FollowEntity.class.getClassLoader());
    }

    public static final Creator<PgcInfo> CREATOR = new Creator<PgcInfo>() {
        public PgcInfo createFromParcel(Parcel source) {
            return new PgcInfo(source);
        }

        public PgcInfo[] newArray(int size) {
            return new PgcInfo[size];
        }
    };
}
