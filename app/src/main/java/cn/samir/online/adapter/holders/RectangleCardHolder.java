package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ParcelableString;
import cn.samir.domains.model.RectangleCard;
import cn.samir.online.R;
import cn.samir.online.adapter.ItemSettings;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.util.Utils;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/5/5 0005.
 */

public class RectangleCardHolder extends CommonViewHolder<BaseModel<RectangleCard>> {
    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;

    private RectangleCard mRectangleCard;

    public RectangleCardHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_rectangle_card);

    }

    @Override
    public void setItemSettings(ItemSettings mItemSettings) {
        super.setItemSettings(mItemSettings);
        updateSize();
    }

    @Override
    public void bindData(BaseModel<RectangleCard> rectangleCardBaseModel, int position) {
        this.mRectangleCard = rectangleCardBaseModel.getData();
        noBackground();
        PhotoUtil.showPhoto(sdvHeader, rectangleCardBaseModel.getData().getImage());
        if (!Utils.isEmpty(this.mRectangleCard.getTitle())) {
            tvTitle.setText(this.mRectangleCard.getTitle());
        }
    }


    @Override
    public Parcelable getDataForBroadcast() {
        return new ParcelableString(mRectangleCard.getActionUrl());
    }
}
