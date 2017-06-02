package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ParcelableString;
import cn.samir.domains.model.TextCard;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.Utils;

/**
 * Created by xiaw on 2017/5/2 0002.
 */

public abstract class BaseTextCardHolder extends CommonViewHolder<BaseModel<TextCard>> {

    protected TextCard mTextCard;

    public BaseTextCardHolder(Context context, ViewGroup root, int layoutRes) {
        super(context, root, layoutRes);
    }


    @Override
    public void bindData(BaseModel<TextCard> textCardBaseModel, int position) {
        mTextCard = textCardBaseModel.getData();
    }


    @Override
    public Parcelable getDataForBroadcast() {
        if (!Utils.isEmpty(mTextCard.getActionUrl())) {
            return new ParcelableString(mTextCard.getActionUrl());
        }
        return null;
    }
}
