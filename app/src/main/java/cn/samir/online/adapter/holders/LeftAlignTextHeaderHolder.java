package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.ViewGroup;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.LeftAlignTextHeader;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class LeftAlignTextHeaderHolder extends CommonViewHolder<BaseModel<LeftAlignTextHeader>> {
    @BindView(R.id.tv_text)
    CustomFontTextView tvText;

    public LeftAlignTextHeaderHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_left_align_text_header);
    }

    @Override
    public void bindData(BaseModel<LeftAlignTextHeader> leftAlignTextHeaderBaseModel, int position) {
        tvText.setText(leftAlignTextHeaderBaseModel.getData().getText());
    }
}
