package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.TextHeader;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.views.CustomFontTextView;


/**
 * Created by xiawei on 2017/3/16.
 */

public class TextHeaderHolder extends CommonViewHolder<BaseModel<TextHeader>> {


    @BindView(R.id.tv_desc)
    CustomFontTextView tvDesc;
    @BindView(R.id.header_divider)
    LinearLayout headerDivider;

    public TextHeaderHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_text_header);

    }

    @Override
    public void bindData(BaseModel<TextHeader> textHeaderBaseModel, int position) {

        if (position == 0) {
            headerDivider.setVisibility(View.GONE);
        } else {
            headerDivider.setVisibility(View.VISIBLE);
        }

        tvDesc.setText(textHeaderBaseModel.getData().getText());
    }

}
