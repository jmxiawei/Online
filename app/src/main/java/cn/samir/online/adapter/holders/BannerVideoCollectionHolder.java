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
import cn.samir.online.util.DensityUtil;

/**
 * Created by xiaw on 2017/4/13 0013.
 */

public class BannerVideoCollectionHolder extends CommonViewHolder<BaseModel<Banner>> {

    @BindView(R.id.img_banner)
    SimpleDraweeView imgBanner;


    private Banner mBanner;

    public BannerVideoCollectionHolder(Context context, ViewGroup root, boolean isFullWidth) {
        super(context, root, R.layout.item_holder_banner_video_collection);
        if (!isFullWidth) {
            ViewGroup.LayoutParams ll = itemView.getLayoutParams();
            ll.width = DensityUtil.getDisplayWidth(context) -
                    2 * DensityUtil.dip2px(context,
                            context.getResources().getDimension(R.dimen.video_collection_item_margin));
            itemView.setLayoutParams(ll);


//            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f);
//            scaleAnimation.setDuration(2000);
//            scaleAnimation.setFillAfter(true);
//            itemView.startAnimation(scaleAnimation);
        }
    }


    @Override
    public Parcelable getDataForBroadcast() {
        return new ParcelableString(this.mBanner.getActionUrl());
    }

    @Override
    public void bindData(BaseModel<Banner> videoBaseModel, int position) {
        this.mBanner = videoBaseModel.getData();
        imgBanner.setImageURI(videoBaseModel.getData().getImage());
    }
}
