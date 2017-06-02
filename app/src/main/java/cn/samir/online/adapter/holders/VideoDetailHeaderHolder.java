package cn.samir.online.adapter.holders;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.concurrent.ScheduledFuture;

import butterknife.BindView;
import butterknife.OnClick;
import cn.samir.domains.model.Author;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.Video;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.http.downloads.DownloadQueryTask;
import cn.samir.online.http.downloads.DownloadRecord;
import cn.samir.online.http.downloads.DownloadTask;
import cn.samir.online.http.task.NetWorkStatusUtil;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.util.Utils;
import cn.samir.online.views.CustomerDialog;
import cn.samir.online.views.FlowCustomTextView;

/**
 * Created by xiaw on 2017/4/25 0025.
 */

public class VideoDetailHeaderHolder extends CommonViewHolder<BaseModel<Video>> implements View.OnAttachStateChangeListener {


    @BindView(R.id.tv_title)
    FlowCustomTextView tvTitle;
    @BindView(R.id.tv_cate_duration)
    FlowCustomTextView tvCateDuration;
    @BindView(R.id.tv_description)
    FlowCustomTextView tvDescription;
    @BindView(R.id.img_fav)
    ImageView imgFav;
    @BindView(R.id.tv_fav_text)
    FlowCustomTextView tvFavText;
    @BindView(R.id.ll_fav)
    LinearLayout llFav;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.tv_share_text)
    FlowCustomTextView tvShareText;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.img_reply)
    ImageView imgReply;
    @BindView(R.id.tv_reply_text)
    FlowCustomTextView tvReplyText;
    @BindView(R.id.ll_reply)
    LinearLayout llReply;
    @BindView(R.id.img_download)
    ImageView imgDownload;
    @BindView(R.id.tv_download_text)
    FlowCustomTextView tvDownloadText;
    @BindView(R.id.ll_download)
    LinearLayout llDownload;
    @BindView(R.id.img_header)
    SimpleDraweeView imgHeader;
    @BindView(R.id.tv_author_name)
    FlowCustomTextView tvAuthorName;
    @BindView(R.id.tv_author_desc)
    FlowCustomTextView tvAuthorDesc;
    @BindView(R.id.btn_follow)
    Button btnFollow;
    @BindView(R.id.ll_author)
    LinearLayout llAuthor;
    @BindView(R.id.line_author)
    View lineAuthor;

    private Video mVideo;
    //private DownloadRecord record;

    private ScheduledFuture mScheduledFuture;

    private DownloadTask mDownloadTask;
    DownloadQueryTask mDownloadQueryTask;

    public VideoDetailHeaderHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.layout_video_detail_header);

        itemView.addOnAttachStateChangeListener(this);

    }

    @Override
    public void bindData(BaseModel<Video> model, int position) {
        mVideo = model.getData();
        tvCateDuration.setFlowText(Utils.buildCategoryAndDuration(mVideo.getCategory(), mVideo.getDuration()));
        tvDescription.setFlowText(mVideo.getDescription());
        tvTitle.setFlowText(mVideo.getTitle());
        tvFavText.setFlowText(String.valueOf(mVideo.getConsumption().getCollectionCount()));
        tvShareText.setFlowText(String.valueOf(mVideo.getConsumption().getShareCount()));
        tvReplyText.setFlowText(String.valueOf(mVideo.getConsumption().getReplyCount()));

        if (mVideo.isCollected()) {
            imgFav.setImageResource(R.mipmap.ic_action_favorites_without_padding);
        } else {
            imgFav.setImageResource(R.mipmap.ic_action_favorites_added_without_padding);
        }

        DownloadRecord record = DownloadRecord.find(String.valueOf(this.mVideo.getId()));
        Uri downloadUri = null;
        if (record != null) {
            if (record.isSuccess()) {
                downloadUri = Uri.parse(record.getLocalPath());
                disableButtonBecouseDownloaded();
            } else {
                //正在下载
                if (this.mVideo.getDownloadId() <= 0) {
                    //失效
                    enableButtonForDownload();
                } else {
                    //正在下载
                    if (mDownloadQueryTask == null) {
                        mDownloadQueryTask = new DownloadQueryTask(getContext(), record);
                    }

                    if (mScheduledFuture != null) {
                        mScheduledFuture.cancel(true);
                    }
                    mScheduledFuture = mDownloadQueryTask.startQuery(onProgressListener);
                }
            }
        } else {
            enableButtonForDownload();
        }
        this.mVideo.setDownloadUri(downloadUri);
        Author author = mVideo.getAuthor();
        if (author != null) {
            llAuthor.setVisibility(View.VISIBLE);
            lineAuthor.setVisibility(View.VISIBLE);
            tvAuthorDesc.setFlowText(author.getDescription());
            tvAuthorName.setFlowText(author.getName());
            PhotoUtil.showPhoto(imgHeader, author.getIcon(), PhotoUtil.DEFAULT_ICON_SIZE, PhotoUtil.DEFAULT_ICON_SIZE);
        } else {
            llAuthor.setVisibility(View.GONE);
            lineAuthor.setVisibility(View.GONE);
        }

    }

    private void disableButtonBecouseDownloaded() {
        tvDownloadText.setEnabled(false);
        tvDownloadText.setText(R.string.text_downloaded);
    }

    private void enableButtonForDownload() {
        //没下载
        llDownload.setEnabled(true);
        tvDownloadText.setText(R.string.text_download);
    }

    @OnClick({R.id.ll_fav, R.id.ll_share, R.id.ll_reply, R.id.ll_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_fav:
                break;
            case R.id.ll_share:
                break;
            case R.id.ll_reply:
                break;
            case R.id.ll_download:


                int type = NetWorkStatusUtil.getNetWorkType(getContext());
                if (type == ConnectivityManager.TYPE_MOBILE) {
                    //提醒流量
                    CustomerDialog dialog = new CustomerDialog(getContext());
                    dialog.setTitle("下载提醒");
                    dialog.setMessage(R.string.text_download_net_notify).addPositiveListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startDownload(v);
                        }
                    }).show();
                } else if (type == ConnectivityManager.TYPE_WIFI) {
                    //wifi下载
                    startDownload(view);
                }


                break;
        }
    }

    /**
     * 开始下载
     *
     * @param v
     */
    private void startDownload(View v) {
        if (mDownloadTask == null) {
            mDownloadTask = new DownloadTask(v.getContext(), mVideo.getPlayUrl(), mVideo.getId());
        }
        mDownloadTask.start(onProgressListener);
        mVideo.setDownloadId(mDownloadTask.getDownloadId());
        llDownload.setEnabled(false);
    }


    private DownloadTask.onProgressListener onProgressListener = new DownloadTask.onProgressListener() {

        @Override
        public void before() {

        }

        @Override
        public void onProgress(long total, long current) {

            if (total == current) {
                disableButtonBecouseDownloaded();
            } else {
                tvDownloadText.setEnabled(false);
                tvDownloadText.setText(percent(total, current));
            }
        }

        @Override
        public void onComplete(Uri destUri) {
            if (destUri != null) {
                disableButtonBecouseDownloaded();
                mVideo.setDownloadUri(destUri);
            }
        }
    };

    @Override
    public void onViewAttachedToWindow(View v) {

    }


    private String percent(long total, long current) {
        return (current * 100 / total) + " %";
    }


    @Override
    public void onViewDetachedFromWindow(View v) {


        if (mScheduledFuture != null) {
            mScheduledFuture.cancel(true);
        }

    }
}
