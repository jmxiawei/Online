package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.ViewGroup;

import cn.samir.domains.model.BaseModel;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;

/**
 * Created by xiaw on 2017/4/19 0019.
 */

public class LoadMoreHolder extends CommonViewHolder<BaseModel> {

    public LoadMoreHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_load_more);
    }

    @Override
    public void bindData(BaseModel baseModel, int position) {

    }
}
