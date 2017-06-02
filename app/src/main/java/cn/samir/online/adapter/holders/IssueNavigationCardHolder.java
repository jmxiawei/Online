package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.IssueNavigationCard;
import cn.samir.domains.model.util.DateUtil;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.DensityUtil;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/5/2 0002.
 */

public class IssueNavigationCardHolder extends CommonViewHolder<BaseModel<IssueNavigationCard>> {


    @BindView(R.id.tv_date)
    CustomFontTextView tvDate;

    private IssueNavigationCard mCard;

    public IssueNavigationCardHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_issue_navigation_card);

        int sw = DensityUtil.getDisplayWidth(context);
        ViewGroup.LayoutParams ll = itemView.getLayoutParams();
        ll.width = sw / 2;
        ll.height = sw / 2;
        itemView.setLayoutParams(ll);
    }


    @Override
    public void bindData(BaseModel<IssueNavigationCard> issueNavigationCardBaseModel, int position) {
        mCard = issueNavigationCardBaseModel.getData();
        tvDate.setText(DateUtil.buildMonthAndDay(mCard.getDate()));
    }


    @Override
    public Parcelable getDataForBroadcast() {
        return this.mCard;
    }
}
