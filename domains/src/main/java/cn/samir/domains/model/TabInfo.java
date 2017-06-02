package cn.samir.domains.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by xiaw on 2017/4/6 0006.
 */

public class TabInfo implements Parcelable {

    @Override
    public String toString() {
        return "TabInfo{" +
                "tabList=" + tabList +
                ", defaultIdx=" + defaultIdx +
                '}';
    }

    public ArrayList<Tab> tabList;
    public int defaultIdx;

    public ArrayList<Tab> getTabList() {
        return tabList;
    }

    public void setTabList(ArrayList<Tab> tabList) {
        this.tabList = tabList;
    }

    public int getDefaultIdx() {
        return defaultIdx;
    }

    public void setDefaultIdx(int defaultIdx) {
        this.defaultIdx = defaultIdx;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(tabList);
        dest.writeInt(this.defaultIdx);
    }

    public TabInfo() {
    }

    protected TabInfo(Parcel in) {
        this.tabList = in.createTypedArrayList(Tab.CREATOR);
        this.defaultIdx = in.readInt();
    }

    public static final Parcelable.Creator<TabInfo> CREATOR = new Parcelable.Creator<TabInfo>() {
        public TabInfo createFromParcel(Parcel source) {
            return new TabInfo(source);
        }

        public TabInfo[] newArray(int size) {
            return new TabInfo[size];
        }
    };
}
