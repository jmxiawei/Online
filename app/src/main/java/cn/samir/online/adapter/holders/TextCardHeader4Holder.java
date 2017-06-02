package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.ViewGroup;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.TextCard;
import cn.samir.online.R;
import cn.samir.online.adapter.ItemSettings;
import cn.samir.online.util.Utils;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiawei on 2017/3/15.
 */

public class TextCardHeader4Holder extends BaseTextCardHolder {


    @BindView(R.id.tv_desc)
    CustomFontTextView tvDesc;


    public TextCardHeader4Holder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_text_card_header4);
    }

    @Override
    public void bindData(BaseModel<TextCard> textFooterBaseModel, int position) {
        super.bindData(textFooterBaseModel,position);

        applySettings();

        tvDesc.setText(Utils.applySpace(textFooterBaseModel.getData().getText(), 1));


        //// TODO: 2017/4/14 0014  
    }

    private void applySettings() {

        if (getItemSettings() != null) {
            ItemSettings settings = getItemSettings();

            if (settings.getBackground() == ItemSettings.BACKGROUND_DARK) {
                tvDesc.setTextColor(getContext().getResources().getColor(R.color.color_white));
            } else {
                tvDesc.setTextColor(getContext().getResources().getColor(R.color.text_color_black));
            }

            if (settings.isNeedWhiteBackground()) {
                itemView.setBackgroundResource(R.color.color_white);
            } else {
                itemView.setBackgroundResource(R.color.transparent);
            }

        }
    }


}
