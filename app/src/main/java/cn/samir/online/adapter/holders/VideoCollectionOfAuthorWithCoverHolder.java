package cn.samir.online.adapter.holders;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ItemCollection;
import cn.samir.online.R;
import cn.samir.online.adapter.VideoCollectionHorizontalAdapter;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/19 0019.
 */

public class VideoCollectionOfAuthorWithCoverHolder extends CommonViewHolder<BaseModel<ItemCollection<BaseModel>>> {


    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.tv_author_name)
    CustomFontTextView tvAuthorName;
    @BindView(R.id.tv_author_desc)
    CustomFontTextView tvAuthorDesc;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.item_recycler)
    RecyclerView itemRecycler;
    @BindView(R.id.sdv_cover)
    SimpleDraweeView sdvCover;
    @BindView(R.id.btn_follow)
    CustomFontTextView btnFollow;

    VideoCollectionHorizontalAdapter videoCollectionRecyclerAdapter;

    public VideoCollectionOfAuthorWithCoverHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_video_collection_author_with_cover);
        itemRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        videoCollectionRecyclerAdapter = new VideoCollectionHorizontalAdapter();
        itemRecycler.setAdapter(videoCollectionRecyclerAdapter);
    }


    @Override
    public void bindData(BaseModel<ItemCollection<BaseModel>> itemCollectionBaseModel, int position) {
        PhotoUtil.showPhoto(sdvHeader, itemCollectionBaseModel.getData().getHeader().getIcon());
        PhotoUtil.showPhoto(sdvCover, itemCollectionBaseModel.getData().getHeader().getCover(),
                PhotoUtil.getSimpleDraweeViewHeightForDisplay(sdvCover),
                PhotoUtil.getSimpleDraweeViewHeightForDisplay(sdvCover));

        tvAuthorName.setText(itemCollectionBaseModel.getData().getHeader().getTitle());
        tvAuthorDesc.setText(itemCollectionBaseModel.getData().getHeader().getDescription());

        videoCollectionRecyclerAdapter.setDataList(itemCollectionBaseModel.getData().getItemList());

    }

    @OnClick(R.id.btn_follow)
    public void onClick() {
    }
}
