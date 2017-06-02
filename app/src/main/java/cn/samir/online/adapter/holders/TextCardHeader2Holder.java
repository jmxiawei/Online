package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.TextCard;
import cn.samir.online.R;
import cn.samir.online.adapter.ItemSettings;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiawei on 2017/3/15.
 */

public class TextCardHeader2Holder extends BaseTextCardHolder {


    @BindView(R.id.header_divider)
    LinearLayout headerDivider;
    @BindView(R.id.tv_desc)
    CustomFontTextView tvDesc;

    public TextCardHeader2Holder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_text_card_header2);

    }


    @Override
    public void bindData(BaseModel<TextCard> textFooterBaseModel, int position) {
        super.bindData(textFooterBaseModel,position);
        applySettings();
        if (position == 1) {
            headerDivider.setVisibility(View.GONE);
        } else {
            headerDivider.setVisibility(View.VISIBLE);
        }
        tvDesc.setText(textFooterBaseModel.getData().getText());
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
