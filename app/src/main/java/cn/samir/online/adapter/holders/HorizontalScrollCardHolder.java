package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import butterknife.BindView;
import cn.samir.domains.model.Banner;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.HorizontalScrollCard;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonDividerHolder;
import cn.samir.online.util.Constant;

/**
 * Created by xiaw on 2017/3/28 0028.
 */

public class HorizontalScrollCardHolder extends CommonDividerHolder<BaseModel<HorizontalScrollCard>> {


    @BindView(R.id.convenient_banner)
    ConvenientBanner convenientBanner;

    CBViewHolderCreator<ConvenientScrollCardHolder> holderCBViewHolderCreator;

    public HorizontalScrollCardHolder(final Context context, final ViewGroup root) {
        super(context, root, R.layout.item_holder_horizontal_scroll_card);

        convenientBanner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                // .setOnPageChangeListener(this)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);


        holderCBViewHolderCreator = new CBViewHolderCreator<ConvenientScrollCardHolder>() {
            @Override
            public ConvenientScrollCardHolder createHolder() {
                return new ConvenientScrollCardHolder(context, root);
            }
        };
    }

    @Override
    public void bindData(BaseModel<HorizontalScrollCard> horizontalScrollCardBaseModel, int position) {

        convenientBanner.setPages(holderCBViewHolderCreator, horizontalScrollCardBaseModel.getData().getItemList());
        convenientBanner.startTurning(Constant.BANNER_SWITCH_DELAY);
        // setPagerData(horizontalScrollCardBaseModel.getData().getItemList());
    }

//    @Override
//    public RPagerSnapHelper getRPagerSnapHelper() {
//        return new RPagerSnapHelper(false);
//    }
//
//    @Override
//    public BaseLoopRecyclerAdapter getBaseLoopRecyclerAdapter() {
//        return new BannerVideoCollectionAdapter(true);
//    }

    public static class ConvenientScrollCardHolder implements Holder<BaseModel<Banner>> {

        private BannerVideoCollectionHolder holder;
        private Context context;
        private ViewGroup parent;


        public ConvenientScrollCardHolder(Context context, ViewGroup parent) {
            this.context = context;
            this.parent = parent;
        }

        @Override
        public View createView(Context context) {
            holder = new BannerVideoCollectionHolder(context, parent, true);
            return holder.itemView;
        }

        @Override
        public void UpdateUI(Context context, int position, BaseModel<Banner> data) {
            holder.bindData(data, position);
        }

    }

}
