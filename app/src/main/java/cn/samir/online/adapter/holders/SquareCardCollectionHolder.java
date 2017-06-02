package cn.samir.online.adapter.holders;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ItemCollection;
import cn.samir.online.R;
import cn.samir.online.adapter.EyeDataRecyclerViewAdapter;
import cn.samir.online.adapter.decorations.SquareCardRightDecorations;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class SquareCardCollectionHolder extends CommonViewHolder<BaseModel<ItemCollection<BaseModel>>> {

    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    EyeDataRecyclerViewAdapter eyeDataRecyclerViewAdapter;
    @BindView(R.id.header_divider)
    LinearLayout headerDivider;

    public SquareCardCollectionHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_square_card_collection);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        eyeDataRecyclerViewAdapter = new EyeDataRecyclerViewAdapter(false, recyclerView);
        recyclerView.addItemDecoration(new SquareCardRightDecorations());
        recyclerView.setAdapter(eyeDataRecyclerViewAdapter);

    }


    @Override
    public void bindData(BaseModel<ItemCollection<BaseModel>> itemCollectionBaseModel, int position) {

        if (position == 0) {
            headerDivider.setVisibility(View.GONE);
        } else {
            headerDivider.setVisibility(View.VISIBLE);
        }

        tvTitle.setText(itemCollectionBaseModel.getData().getHeader().getTitle());
        eyeDataRecyclerViewAdapter.setDataList(itemCollectionBaseModel.getData().getItemList());
    }


}
