package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import cn.samir.domains.model.Author;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.Video;
import cn.samir.online.R;
import cn.samir.online.adapter.BaseRecyclerAdapter;
import cn.samir.online.adapter.holders.base.CommonShadeViewHolder;
import cn.samir.online.events.VideoEvent;
import cn.samir.online.http.downloads.DownloadUtils;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.util.Utils;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiawei on 2017/3/15.
 */

public class VideoHolder extends CommonShadeViewHolder<BaseModel<Video>> {

    @BindView(R.id.img_bg)
    SimpleDraweeView imgBg;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.tv_cate_duration)
    CustomFontTextView tvCateDuration;
    @BindView(R.id.tv_author_name)
    CustomFontTextView tvAuthorName;
    @BindView(R.id.view_shade)
    RelativeLayout rlTextData;

    private Video mVideo;
    private int indexPosition;

    private RecyclerView recyclerView;

    public VideoHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_video);
        this.recyclerView = (RecyclerView) root;
    }

    @Override
    public void bindData(BaseModel<Video> baseModel, int position) {

        mVideo = baseModel.getData();
        DownloadUtils.checkExist(getContext(),mVideo);
        PhotoUtil.showPhoto(imgBg, baseModel.getData().getCover().getFeed(), PhotoUtil.getSimpleDraweeViewWidthForDisplay(imgBg)
                , PhotoUtil.getSimpleDraweeViewHeightForDisplay(imgBg));
        tvTitle.setText(baseModel.getData().getTitle());
        Author author = baseModel.getData().getAuthor();
        if (author != null && !TextUtils.isEmpty(author.name)) {
            tvAuthorName.setVisibility(View.VISIBLE);
            tvAuthorName.setText(author.name);
        } else {
            tvAuthorName.setVisibility(View.INVISIBLE);
        }


        tvCateDuration.setText(Utils.buildCategoryAndDuration(baseModel.getData().getCategory(), baseModel.getData().getDuration()));
    }


    @Override
    public Parcelable getDataForBroadcast() {
        ArrayList<Video> videos = findNextVideos();
        if (videos == null) {
            return this.mVideo;
        } else {
            return new VideoEvent(videos, indexPosition, mVideo);
        }
    }

    private ArrayList<Video> findNextVideos() {
        ArrayList<Video> videos = null;
        //recyclerView = findNestedRecyclerView(itemView);
        if (recyclerView != null) {
            BaseRecyclerAdapter adapter = (BaseRecyclerAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                int position = getAdapterPosition();

                for (int i = position - 10; i < position + 10; i++) {
                    if (i >= 0 && i < adapter.getItemCount()) {
                        Object object = adapter.getItem(i);
                        if (object instanceof BaseModel) {
                            BaseModel b = (BaseModel) object;
                            Object data = b.getData();
                            if (data instanceof Video) {
                                if (videos == null) {
                                    videos = new ArrayList<>();
                                }
                                videos.add((Video) data);
                            }
                        }
                    }
                }
            }
        }


        if (videos != null) {
            indexPosition = videos.indexOf(this.mVideo);
            if (indexPosition == -1) {
                videos.clear();
                videos = null;
            }
        }

        return videos;
    }


    /**
     * Utility method for finding an internal RecyclerView, if present
     */
    @Nullable
    static RecyclerView findNestedRecyclerView(@NonNull View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        final ViewGroup parent = (ViewGroup) view;
        final int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView descendant = findNestedRecyclerView(child);
            if (descendant != null) {
                return descendant;
            }
        }
        return null;
    }

    @Override
    public View getShadeView() {
        return rlTextData;
    }
}
