package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import cn.elvin.customlib.L;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ParcelableString;
import cn.samir.domains.model.TextCard;
import cn.samir.online.R;
import cn.samir.online.adapter.ItemSettings;
import cn.samir.online.util.Utils;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiawei on 2017/3/15.
 */

public class TextCardHeader1Footer1Holder extends BaseTextCardHolder {


    @BindView(R.id.tv_desc)
    CustomFontTextView tvDesc;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.headerline)
    View headerline;
    @BindView(R.id.headerdivider)
    View headerdivider;
    @BindView(R.id.footerline)
    View footerline;
    @BindView(R.id.footer_divider)
    View footerDivider;



    public TextCardHeader1Footer1Holder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_text_card_header1_footer1);
    }


    @Override
    public void bindData(BaseModel<TextCard> textFooterBaseModel, int position) {
        super.bindData(textFooterBaseModel,position);

        applySettings();
        TextCard tc = textFooterBaseModel.getData();
        if (tc.getType().equals(TextCard.HEADER1)) {
            imgRight.setVisibility(View.GONE);
            headerline.setVisibility(View.VISIBLE);
            headerdivider.setVisibility(View.VISIBLE);
            footerline.setVisibility(View.GONE);
            footerDivider.setVisibility(View.GONE);
            tvDesc.setText(Utils.applySpace(textFooterBaseModel.getData().getText(), 0));
        } else {
            imgRight.setVisibility(View.VISIBLE);
            footerline.setVisibility(View.VISIBLE);
            footerDivider.setVisibility(View.VISIBLE);
            headerline.setVisibility(View.GONE);
            headerdivider.setVisibility(View.GONE);
            tvDesc.setText(Utils.applySpace(textFooterBaseModel.getData().getText(), 1));
        }
        L.e(textFooterBaseModel.getData().getText());
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
