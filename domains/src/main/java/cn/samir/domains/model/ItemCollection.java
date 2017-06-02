package cn.samir.domains.model;

import java.util.ArrayList;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class ItemCollection<T> {

    String dataType;
    Header header;
    ArrayList<T> itemList;
    int count;
    ArrayList<AdOrganizition> adTrack;

    @Override
    public String toString() {
        return "ItemCollection{" +
                "dataType='" + dataType + '\'' +
                ", header=" + header +
                ", count=" + count +
                '}';
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ArrayList<T> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<T> itemList) {
        this.itemList = itemList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<AdOrganizition> getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(ArrayList<AdOrganizition> adTrack) {
        this.adTrack = adTrack;
    }
}
