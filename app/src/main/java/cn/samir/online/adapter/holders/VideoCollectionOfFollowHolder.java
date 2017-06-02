package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ItemCollection;
import cn.samir.domains.model.ParcelableString;
import cn.samir.online.R;
import cn.samir.online.adapter.SimpleIconRecyclerAdapter;
import cn.samir.online.adapter.VideoCollectionHorizontalAdapter;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.views.CustomFontTextView;


/**
 * Created by xiawei on 2017/3/16.
 */

public class VideoCollectionOfFollowHolder extends CommonViewHolder<BaseModel<ItemCollection<BaseModel>>> {


    VideoCollectionHorizontalAdapter recyclerAdapter;
    SimpleIconRecyclerAdapter simpleIconRecyclerAdapter;
    ItemCollection<BaseModel> mData;
    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.recycler_icon_list)
    RecyclerView recyclerIconList;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.tv_desc)
    CustomFontTextView tvDesc;
    @BindView(R.id.item_recycler)
    RecyclerView itemRecycler;

    public VideoCollectionOfFollowHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_video_collection_of_follow);

        itemRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerAdapter = new VideoCollectionHorizontalAdapter();
        itemRecycler.setAdapter(recyclerAdapter);
        recyclerIconList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        simpleIconRecyclerAdapter = new SimpleIconRecyclerAdapter();
        recyclerIconList.setAdapter(simpleIconRecyclerAdapter);

    }

    @Override
    public void bindData(BaseModel<ItemCollection<BaseModel>> model, int position) {

        this.mData = model.getData();
        tvDesc.setText(model.getData().getHeader().getDescription());
        tvTitle.setText(model.getData().getHeader().getTitle());

        PhotoUtil.showPhoto(sdvHeader, model.getData().getHeader().getCover(), PhotoUtil.getSimpleDraweeViewWidthForDisplay(sdvHeader)
                , PhotoUtil.getSimpleDraweeViewHeightForDisplay(sdvHeader));

        recyclerAdapter.setDataList(model.getData().getItemList());
        simpleIconRecyclerAdapter.setDataList(model.getData().getHeader().getIconList());
    }


    @Override
    public Parcelable getDataForBroadcast() {
        return new ParcelableString(this.mData.getHeader().getActionUrl());
    }
}
