package cn.samir.online.adapter;

import android.view.ViewGroup;

import cn.samir.domains.model.BaseModel;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.adapter.holders.RectangleCardHolder;
import cn.samir.online.adapter.holders.SquareCardHolder;
import cn.samir.online.adapter.holders.ViewHolderCreator;
import cn.samir.online.http.converts.TypeDataItem;
import cn.samir.online.http.converts.TypeProvider;
import cn.samir.online.util.DensityUtil;

/**
 * Created by xiaw on 2017/5/5 0005.
 */

public class AllCategoriesAdapter extends BaseRecyclerAdapter<BaseModel> {

    public static final int DIVIDER_WIDTH = 4;

    @Override
    public int getItemViewType(int position) {
        BaseModel bm = getItem(position);
        return viewHolderCreator.getType(bm.getType(), bm);
    }

    @Override
    public ViewHolderCreator createViewHolderCreator() {
        return new ViewHolderCreator() {
            @Override
            public CommonViewHolder createByViewGroupAndType(ViewGroup parent, int viewType, Object... p) {
                TypeDataItem item = TypeProvider.getInstance().getByType(viewType);
                CommonViewHolder holder = item.getHolder(parent, viewType);
                if (holder instanceof SquareCardHolder) {
                    //屏幕一半宽高
                    SquareCardHolder holder1 = (SquareCardHolder) holder;
                    //配合itemDe..分割线
                    int w = DensityUtil.getDisplayWidth(parent.getContext()) / 2 - DIVIDER_WIDTH / 2;
                    ItemSettings itemSettings = new ItemSettings(w, w);
                    holder1.setItemSettings(itemSettings);
                } else if (holder instanceof RectangleCardHolder) {
                    int w = DensityUtil.getDisplayWidth(parent.getContext());
                    ItemSettings itemSettings = new ItemSettings(w, w / 2);
                    holder.setItemSettings(itemSettings);
                }
                return holder;
            }

            @Override
            public int getType(String key, BaseModel bm) {

                TypeDataItem item = TypeProvider.getInstance().getByKey(key);
                if (item != null) {
                    return item.getAdapterItemType();
                } else {
                    throw new NullPointerException("no type key =" + key);
                }
            }
        };
    }


}
