package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.ViewGroup;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.online.R;
import cn.samir.online.adapter.ItemSettings;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/19 0019.
 */

public class NoMoreHolder extends CommonViewHolder<BaseModel> {

    @BindView(R.id.tv_end)
    CustomFontTextView tvEnd;

    public NoMoreHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_no_more);
    }

    @Override
    public void bindData(BaseModel baseModel, int position) {

        if (mItemSettings.getBackground() == ItemSettings.BACKGROUND_DARK) {
            tvEnd.setTextColor(getContext().getResources().getColor(R.color.color_white));
        } else {
            tvEnd.setTextColor(getContext().getResources().getColor(R.color.text_color_black));
        }
    }
}
