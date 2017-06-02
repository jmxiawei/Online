package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.ViewGroup;

import cn.samir.domains.model.BaseModel;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;

/**
 * Created by xiaw on 2017/5/5 0005.
 */

public class MaskToolBarHolder extends CommonViewHolder<BaseModel> {

    public MaskToolBarHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_mask_toolbar);
    }

    @Override
    public void bindData(BaseModel baseModel, int position) {

    }
}
