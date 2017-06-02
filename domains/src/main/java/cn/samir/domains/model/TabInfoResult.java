package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaw on 2017/4/6 0006.
 */

public class TabInfoResult implements Parcelable {

    TabInfo tabInfo;

    @Override
    public String toString() {
        return "TabInfoResult{" +
                "tabInfo=" + tabInfo +
                '}';
    }

    public TabInfo getTabInfo() {
        return tabInfo;
    }

    public void setTabInfo(TabInfo tabInfo) {
        this.tabInfo = tabInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.tabInfo, 0);
    }

    public TabInfoResult() {
    }

    protected TabInfoResult(Parcel in) {
        this.tabInfo = in.readParcelable(TabInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<TabInfoResult> CREATOR = new Parcelable.Creator<TabInfoResult>() {
        public TabInfoResult createFromParcel(Parcel source) {
            return new TabInfoResult(source);
        }

        public TabInfoResult[] newArray(int size) {
            return new TabInfoResult[size];
        }
    };
}
