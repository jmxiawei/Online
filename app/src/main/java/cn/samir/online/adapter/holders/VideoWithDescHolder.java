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
import cn.samir.online.util.DensityUtil;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.util.Utils;
import cn.samir.online.views.CustomFontTextView;

/** 无限滑动，不满全屏
 * Created by xiaw on 2017/4/14 0014.
 */
public class VideoWithDescHolder extends CommonViewHolder<BaseModel<Video>> {


    @BindView(R.id.img_banner)
    SimpleDraweeView imgBanner;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.tv_cate_duration)
    CustomFontTextView tvCateDuration;

    private Video mVideo;
    public VideoWithDescHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_banner_collection_with_desc);

        ViewGroup.LayoutParams ll = itemView.getLayoutParams();
        ll.width = DensityUtil.getDisplayWidth(context) -
                2 * DensityUtil.dip2px(context,
                        context.getResources().getDimension(R.dimen.video_collection_item_margin));
        itemView.setLayoutParams(ll);

    }

    @Override
    public void bindData(BaseModel<Video> video, int position) {
        this.mVideo = video.getData();
        DownloadUtils.checkExist(getContext(),mVideo);
        //PhotoUtil.showPhoto(imgBanner, video.getData().getCover().getFeed(), 0, 0);
        PhotoUtil.showPhoto(imgBanner, video.getData().getCover().getFeed(), PhotoUtil.getSimpleDraweeViewWidthForDisplay(imgBanner)
                , PhotoUtil.getSimpleDraweeViewHeightForDisplay(imgBanner));
        tvTitle.setText(video.getData().getTitle());
        tvCateDuration.setText(Utils.buildCategoryAndDuration(video.getData().getCategory(), video.getData().getDuration()));
    }

    @Override
    public Parcelable getDataForBroadcast() {
        return this.mVideo;
    }
}
