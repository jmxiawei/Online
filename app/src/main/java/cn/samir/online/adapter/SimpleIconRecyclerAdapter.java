package cn.samir.online.adapter;

import android.view.ViewGroup;

import cn.samir.domains.model.BaseModel;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.adapter.holders.VideoCollectionFollowHeaderIconViewHolder;
import cn.samir.online.adapter.holders.ViewHolderCreator;

/**
 * Created by xiawei on 2017/3/16.
 */

public class SimpleIconRecyclerAdapter extends BaseRecyclerAdapter<String> {


    @Override
    public ViewHolderCreator createViewHolderCreator() {
        return new ViewHolderCreator() {
            @Override
            public CommonViewHolder createByViewGroupAndType(ViewGroup parent, int viewType, Object... p) {
                return new VideoCollectionFollowHeaderIconViewHolder(parent.getContext(), parent);
            }

            @Override
            public int getType(String key, BaseModel bm) {
                return 0;
            }
        };
    }


}
