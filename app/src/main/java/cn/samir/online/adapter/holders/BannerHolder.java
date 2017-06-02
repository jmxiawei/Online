package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import cn.samir.domains.model.Banner;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ParcelableString;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.PhotoUtil;


/**
 * Created by xiawei on 2017/3/16.
 */

public class BannerHolder extends CommonViewHolder<BaseModel<Banner>> {


    @BindView(R.id.img)
    SimpleDraweeView img;

    private Banner mBanner;

    public BannerHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_banner);
    }

    @Override
    public void bindData(BaseModel<Banner> banner, int position) {
        mBanner = banner.getData();
        PhotoUtil.showPhotoMaybeGif(img, banner.getData().getImage());
    }


    @Override
    public Parcelable getDataForBroadcast() {
        return new ParcelableString(this.mBanner.getActionUrl());
    }
}
