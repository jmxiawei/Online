package cn.samir.online.adapter.holders;

import android.view.ViewGroup;

import cn.samir.domains.model.BaseModel;
import cn.samir.online.adapter.holders.base.CommonViewHolder;

/**
 * Created by xiawei on 2017/3/15.
 */


public interface ViewHolderCreator<VH extends CommonViewHolder> {
    public VH createByViewGroupAndType(ViewGroup parent, int viewType, Object... p);

    public int getType(String key, BaseModel bm);
}