package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ParcelableString;
import cn.samir.domains.model.SquareCard;
import cn.samir.online.R;
import cn.samir.online.adapter.ItemSettings;
import cn.samir.online.adapter.holders.base.CommonShadeViewHolder;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class SquareCardHolder extends CommonShadeViewHolder<BaseModel<SquareCard>> {


    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;


    private SquareCard mSquareCard;

    public SquareCardHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_square_card);
    }

    @Override
    public void setItemSettings(ItemSettings mItemSettings) {
        super.setItemSettings(mItemSettings);
        updateSize();
    }

    @Override
    public void bindData(BaseModel<SquareCard> squareCard, int position) {
        this.mSquareCard = squareCard.getData();
        setShade(this.mSquareCard.isShade());
        tvTitle.setText(squareCard.getData().getTitle());
        PhotoUtil.showPhoto(sdvHeader, squareCard.getData().getImage());
    }

    @Override
    public View getShadeView() {
        return tvTitle;
    }


    @Override
    public Parcelable getDataForBroadcast() {
        return new ParcelableString(mSquareCard.getActionUrl());
    }
}
