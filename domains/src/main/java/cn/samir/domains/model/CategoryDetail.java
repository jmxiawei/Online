package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaw on 2017/5/10 0010.
 */

public class CategoryDetail implements Parcelable {

    /**
     * categoryInfo : {"dataType":"CategoryInfo","id":36,"name":"生活","description":"匠心、健康、生活感悟","headerImage":"http://img.kaiyanapp.com/a1a1bf7ed3ac906ee4e8925218dd9fbe.png","actionUrl":"eyepetizer://category/36/?title=%E7%94%9F%E6%B4%BB","follow":{"itemType":"category","itemId":36,"followed":false}}
     * tabInfo : {"tabList":[{"id":0,"name":"首页","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/detail/index?id=36"},{"id":1,"name":"全部","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/videoList?id=36"},{"id":2,"name":"作者","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/detail/pgcs?id=36"},{"id":3,"name":"专辑","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/detail/playlist?id=36"}],"defaultIdx":0}
     */
    private PgcInfo pgcInfo;
    private CategoryInfo categoryInfo;
    private TabInfo tabInfo;

    public PgcInfo getPgcInfo() {
        return pgcInfo;
    }

    public void setPgcInfo(PgcInfo pgcInfo) {
        this.pgcInfo = pgcInfo;
    }

    public CategoryInfo getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(CategoryInfo categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public TabInfo getTabInfo() {
        return tabInfo;
    }

    public void setTabInfo(TabInfo tabInfo) {
        this.tabInfo = tabInfo;
    }

    public CategoryDetail() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.pgcInfo, 0);
        dest.writeParcelable(this.categoryInfo, 0);
        dest.writeParcelable(this.tabInfo, 0);
    }

    protected CategoryDetail(Parcel in) {
        this.pgcInfo = in.readParcelable(PgcInfo.class.getClassLoader());
        this.categoryInfo = in.readParcelable(CategoryInfo.class.getClassLoader());
        this.tabInfo = in.readParcelable(TabInfo.class.getClassLoader());
    }

    public static final Creator<CategoryDetail> CREATOR = new Creator<CategoryDetail>() {
        public CategoryDetail createFromParcel(Parcel source) {
            return new CategoryDetail(source);
        }

        public CategoryDetail[] newArray(int size) {
            return new CategoryDetail[size];
        }
    };
}
