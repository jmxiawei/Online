package cn.samir.online.adapter.holders;


import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ItemCollection;
import cn.samir.online.R;
import cn.samir.online.adapter.BaseLoopRecyclerAdapter;
import cn.samir.online.adapter.VideoCollectionWithDescAdapter;
import cn.samir.online.adapter.holders.base.CommonRecyclerPagerHolder;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class VideoCollectionOfHorizontalScrollCardHolder extends CommonRecyclerPagerHolder<BaseModel<ItemCollection<BaseModel>>> {

    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.tv_subtitle)
    CustomFontTextView tvSubtitle;
    @BindView(R.id.btn_follow)
    Button btnFollow;

    public VideoCollectionOfHorizontalScrollCardHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_video_collection_of_horizontal_scroll_card);
    }

    @Override
    public void bindData(BaseModel<ItemCollection<BaseModel>> model, int position) {
        tvSubtitle.setText(model.getData().getHeader().getSubTitle());
        tvTitle.setText(model.getData().getHeader().getTitle());
        setPagerData(model.getData().getItemList());
    }

    @Override
    public BaseLoopRecyclerAdapter getBaseLoopRecyclerAdapter() {
        return new VideoCollectionWithDescAdapter();
    }
}
