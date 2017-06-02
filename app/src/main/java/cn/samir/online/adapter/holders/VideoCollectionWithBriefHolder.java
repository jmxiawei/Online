package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.Header;
import cn.samir.domains.model.ItemCollection;
import cn.samir.domains.model.ParcelableString;
import cn.samir.online.R;
import cn.samir.online.adapter.VideoCollectionHorizontalAdapter;
import cn.samir.online.adapter.holders.base.CommonDividerHolder;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class VideoCollectionWithBriefHolder extends CommonDividerHolder<BaseModel<ItemCollection<BaseModel>>> {
    @BindView(R.id.img_header)
    SimpleDraweeView imgHeader;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.tv_description)
    CustomFontTextView tvDescription;
    @BindView(R.id.btn_follow)
    Button btnFollow;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    VideoCollectionHorizontalAdapter videoCollectionHorizontalAdapter;


    Header mHeader;

    public VideoCollectionWithBriefHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_video_collection_with_brief);
    }

    @Override
    public void bindData(BaseModel<ItemCollection<BaseModel>> model, int position) {
        this.mHeader = model.getData().getHeader();
        PhotoUtil.showPhoto(imgHeader, model.getData().getHeader().getIcon());
        tvTitle.setText(model.getData().getHeader().getTitle());
        tvDescription.setText(model.getData().getHeader().getDescription());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        videoCollectionHorizontalAdapter = new VideoCollectionHorizontalAdapter();
        recyclerView.setAdapter(videoCollectionHorizontalAdapter);
        videoCollectionHorizontalAdapter.setDataList(model.getData().getItemList());
    }


    @Override
    public Parcelable getDataForBroadcast() {
        return new ParcelableString(this.mHeader.actionUrl);
    }
}
