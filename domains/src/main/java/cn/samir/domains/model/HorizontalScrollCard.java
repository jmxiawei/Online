package cn.samir.domains.model;

import java.util.ArrayList;

/**
 * Created by xiaw on 2017/3/28 0028.
 */

public class HorizontalScrollCard {
    public String dataType;
    public ArrayList<BaseModel> itemList;
    public int count;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public ArrayList<BaseModel> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<BaseModel> itemList) {
        this.itemList = itemList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
