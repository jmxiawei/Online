package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.Video;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.http.downloads.DownloadUtils;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.util.Utils;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/19 0019.
 */

public class VideoSmallCardHolder extends CommonViewHolder<BaseModel<Video>> {
    @BindView(R.id.sdv_banner)
    SimpleDraweeView sdvBanner;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.tv_cate_duration)
    CustomFontTextView tvCateDuration;


    private Video mVideo;
    public VideoSmallCardHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_video_small_card);

    }


    @Override
    public void bindData(BaseModel<Video> baseModel, int position) {
        this.mVideo = baseModel.getData();
        DownloadUtils.checkExist(getContext(),mVideo);
        PhotoUtil.showPhoto(sdvBanner, baseModel.getData().getCover().getFeed(), PhotoUtil.getSimpleDraweeViewWidthForDisplay(sdvBanner)
                , PhotoUtil.getSimpleDraweeViewHeightForDisplay(sdvBanner));
        tvTitle.setText(baseModel.getData().getTitle());
        tvCateDuration.setText(Utils.buildCategoryAndDuration(baseModel.getData().getCategory(), baseModel.getData().getDuration()));

    }

    @Override
    public Parcelable getDataForBroadcast() {
        return this.mVideo;
    }
}
