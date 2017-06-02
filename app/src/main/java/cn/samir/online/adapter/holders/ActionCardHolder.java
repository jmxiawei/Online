package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import cn.samir.domains.model.ActionCard;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ParcelableString;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;

/**
 * 查看更多的显示，根据actionUrl跳转
 *
 *
 * Created by xiaw on 2017/4/11 0011.
 */

public class ActionCardHolder extends CommonViewHolder<BaseModel<ActionCard>> {

    @BindView(R.id.tv_text)
    TextView tvText;

    private ActionCard mActionCard;

    public ActionCardHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_action_card);
    }

    @Override
    public void bindData(BaseModel<ActionCard> actionCardBaseModel, int position) {
        this.mActionCard = actionCardBaseModel.getData();
        tvText.setText(actionCardBaseModel.getData().getText());
    }

    @Override
    public Parcelable getDataForBroadcast() {
        return new ParcelableString(this.mActionCard.getActionUrl());
    }
}
