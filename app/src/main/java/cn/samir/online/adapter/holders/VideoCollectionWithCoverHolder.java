package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ItemCollection;
import cn.samir.domains.model.ParcelableString;
import cn.samir.online.R;
import cn.samir.online.adapter.VideoCollectionHorizontalAdapter;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.PhotoUtil;

/**
 * Created by xiawei on 2017/3/16.
 */

public class VideoCollectionWithCoverHolder extends CommonViewHolder<BaseModel<ItemCollection<BaseModel>>> {


    SimpleDraweeView sdv;
    RecyclerView recyclerView;
    VideoCollectionHorizontalAdapter videoCollectionRecyclerAdapter;
    private ItemCollection<BaseModel> mData;
    public VideoCollectionWithCoverHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_video_collection_of_cover);

        sdv = getView(R.id.sdv_header);
        recyclerView = getView(R.id.item_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        videoCollectionRecyclerAdapter = new VideoCollectionHorizontalAdapter();
        recyclerView.setAdapter(videoCollectionRecyclerAdapter);

    }

    @Override
    public void bindData(BaseModel<ItemCollection<BaseModel>> videoCollectionWithCoverBaseModel, int position) {
        this.mData = videoCollectionWithCoverBaseModel.getData();
        PhotoUtil.showPhoto(sdv, videoCollectionWithCoverBaseModel.getData().getHeader().getCover(), PhotoUtil.getSimpleDraweeViewWidthForDisplay(sdv)
                , PhotoUtil.getSimpleDraweeViewHeightForDisplay(sdv));
        videoCollectionRecyclerAdapter.setDataList(videoCollectionWithCoverBaseModel.getData().getItemList());
    }


    @Override
    public Parcelable getDataForBroadcast() {
        return new ParcelableString(this.mData.getHeader().getActionUrl());
    }
}
