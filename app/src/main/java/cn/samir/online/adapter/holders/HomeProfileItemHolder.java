package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.events.HomeProfileEvent;
import cn.samir.online.ui.home.HomeProfileFragment;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/14 0014.
 */

public class HomeProfileItemHolder extends CommonViewHolder<HomeProfileFragment.ProfileItem> {
    @BindView(R.id.tv_text)
    CustomFontTextView tvText;
    @BindView(R.id.view_mark)
    View viewMark;
    HomeProfileFragment.ProfileItem mProfileItem;

    public HomeProfileItemHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_home_profile);
    }

    @Override
    public void bindData(HomeProfileFragment.ProfileItem profileItem, int position) {
        this.mProfileItem = profileItem;
        tvText.setText(profileItem.getText());
        viewMark.setVisibility((profileItem.getHasMark() == 1) ? View.VISIBLE : View.INVISIBLE);
    }


    @Override
    public Parcelable getDataForBroadcast() {
        return new HomeProfileEvent(this.mProfileItem.getId());
    }
}
