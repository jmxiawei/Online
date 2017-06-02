package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.ViewGroup;

import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.BlankCard;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.DensityUtil;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class BlankCardHolder extends CommonViewHolder<BaseModel<BlankCard>> {

    public BlankCardHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_blank_card);
    }

    @Override
    public void bindData(BaseModel<BlankCard> blankCardBaseModel, int position) {
        int height = blankCardBaseModel.getData().getHeight();
        ViewGroup.LayoutParams ll = itemView.getLayoutParams();
        ll.height = DensityUtil.dip2px(getContext(), height);
        itemView.setLayoutParams(ll);


    }
}
