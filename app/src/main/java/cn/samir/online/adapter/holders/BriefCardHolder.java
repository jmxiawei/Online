package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.BriefCard;
import cn.samir.domains.model.ParcelableString;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class BriefCardHolder extends CommonViewHolder<BaseModel<BriefCard>> {

    @BindView(R.id.img_header)
    SimpleDraweeView imgHeader;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.tv_description)
    CustomFontTextView tvDescription;
    @BindView(R.id.btn_follow)
    Button btnFollow;

    private BriefCard mCard;

    public BriefCardHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_brief_card);
    }

    @Override
    public void bindData(BaseModel<BriefCard> briefCardBaseModel, int position) {
        this.mCard = briefCardBaseModel.getData();
        PhotoUtil.showPhoto(imgHeader, briefCardBaseModel.getData().getIcon());
        tvTitle.setText(briefCardBaseModel.getData().getTitle());
        tvDescription.setText(briefCardBaseModel.getData().getDescription());

    }

    @Override
    public Parcelable getDataForBroadcast() {
        return new ParcelableString(this.mCard.getActionUrl());
    }
}
