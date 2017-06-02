package cn.samir.online.http.converts;

import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.samir.online.adapter.holders.base.CommonViewHolder;

/**
 * Created by xiaw on 2017/4/7 0007.
 */

public abstract class TypeDataItem {


    public String typeKey;
    public int adapterItemType;
    public TypeToken typeToken;
    public Type type;

    public TypeDataItem(String typeKey, int adapterItemType, TypeToken typeToken) {
        this.typeKey = typeKey;
        this.adapterItemType = adapterItemType;
        this.typeToken = typeToken;
        type = typeToken.getType();
    }

    public String getTypeKey() {
        return typeKey;
    }

    public int getAdapterItemType() {
        return adapterItemType;
    }

    public TypeToken getTypeToken() {
        return typeToken;
    }

    public Type getType() {
        return type;
    }

    public abstract CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p);


}
