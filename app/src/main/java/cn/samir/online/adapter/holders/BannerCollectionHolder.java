package cn.samir.online.adapter.holders;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import butterknife.BindView;
import cn.samir.domains.model.Banner;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ItemCollection;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonDividerHolder;
import cn.samir.online.util.Constant;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/11 0011.
 */
public class BannerCollectionHolder extends CommonDividerHolder<BaseModel<ItemCollection<BaseModel>>> {


    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.ll_title)
    RelativeLayout llTitle;
    @BindView(R.id.convenient_banner)
    ConvenientBanner<BaseModel> convenientBanner;


    private CBViewHolderCreator<ConvenientBannerHolder> holderCBViewHolderCreator;

    public BannerCollectionHolder(final Context context, final ViewGroup root) {
        super(context, root, R.layout.item_holder_banner_collection);

        convenientBanner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                // .setOnPageChangeListener(this)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);


        holderCBViewHolderCreator = new CBViewHolderCreator<ConvenientBannerHolder>() {
            @Override
            public ConvenientBannerHolder createHolder() {
                return new ConvenientBannerHolder(context, root);
            }
        };

    }

    @Override
    protected void setItemViewClickListener() {
    }

    @Override
    public void bindData(BaseModel<ItemCollection<BaseModel>> model, int position) {
        tvTitle.setText(model.getData().getHeader().getTitle());
        convenientBanner.setPages(holderCBViewHolderCreator, model.getData().getItemList());

        if (model.getData().getItemList().size() > 1) {
            convenientBanner.startTurning(Constant.BANNER_SWITCH_DELAY);
        }
    }


    public static class ConvenientBannerHolder implements Holder<BaseModel<Banner>> {

        private BannerHolder holder;
        private Context context;
        private ViewGroup parent;


        public ConvenientBannerHolder(Context context, ViewGroup parent) {
            this.context = context;
            this.parent = parent;
        }

        @Override
        public View createView(Context context) {
            holder = new BannerHolder(context, parent);
            return holder.itemView;
        }

        @Override
        public void UpdateUI(Context context, int position, BaseModel<Banner> data) {
            holder.bindData(data, position);
        }

    }
}
