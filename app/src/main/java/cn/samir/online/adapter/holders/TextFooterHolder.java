package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.ViewGroup;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.TextFooter;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.Utils;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiawei on 2017/3/15.
 */

public class TextFooterHolder extends CommonViewHolder<BaseModel<TextFooter>> {


    @BindView(R.id.tv_desc)
    CustomFontTextView tvDesc;

    public TextFooterHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_text_footer);
    }

    @Override
    public void bindData(BaseModel<TextFooter> textFooterBaseModel, int position) {
        tvDesc.setText(Utils.applySpace(textFooterBaseModel.getData().text, 1));
    }
}
