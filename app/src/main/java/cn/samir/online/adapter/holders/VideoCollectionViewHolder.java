package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.Video;
import cn.samir.online.R;
import cn.samir.online.adapter.VideoCollectionHorizontalAdapter;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.http.downloads.DownloadUtils;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.util.Utils;
import cn.samir.online.views.CustomFontTextView;

/**
 * 小图片加 2行文字（一行标题，一行时长）
 * item for {@link VideoCollectionHorizontalAdapter}
 * <p>
 * Created by xiawei on 2017/3/16.
 */

public class VideoCollectionViewHolder extends CommonViewHolder<BaseModel<Video>> {


    @BindView(R.id.sdv_feed)
    SimpleDraweeView sdvFeed;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.tv_cate_duration)
    CustomFontTextView tvCateDuration;


    private Video mVideo;

    public VideoCollectionViewHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_video_collection);
    }

    @Override
    public void bindData(BaseModel<Video> video, int position) {
        this.mVideo = video.getData();
        DownloadUtils.checkExist(getContext(), mVideo);
        PhotoUtil.showPhoto(sdvFeed, video.getData().getCover().getFeed(), PhotoUtil.getSimpleDraweeViewWidthForDisplay(sdvFeed)
                , PhotoUtil.getSimpleDraweeViewHeightForDisplay(sdvFeed));
        tvTitle.setText(video.getData().getTitle());
        tvCateDuration.setText(Utils.buildCategoryAndDuration(video.getData().getCategory(), video.getData().getDuration()));
    }


    @Override
    public Parcelable getDataForBroadcast() {
        return this.mVideo;
    }
}
