package cn.samir.online.adapter;

import android.view.ViewGroup;

import cn.samir.domains.model.BaseModel;
import cn.samir.online.adapter.holders.VideoCollectionOfAuthorWithCoverHolder;
import cn.samir.online.adapter.holders.VideoCollectionViewHolder;
import cn.samir.online.adapter.holders.VideoCollectionWithCoverHolder;
import cn.samir.online.adapter.holders.ViewHolderCreator;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.http.converts.TypeDataItem;
import cn.samir.online.http.converts.TypeProvider;

/**
 * 顶部有BANNER，下面是视频列表的适配器
 * {@link VideoCollectionWithCoverHolder}
 * {@link VideoCollectionWithCoverHolder}
 * {@link VideoCollectionOfAuthorWithCoverHolder}
 * Created by xiaw on 2017/4/12 0012.
 */
public class VideoCollectionHorizontalAdapter extends BaseLoopRecyclerAdapter {


    public static class CustomerHolderCreator implements ViewHolderCreator {

        public int getType(String key, BaseModel bm) {

            TypeDataItem item = TypeProvider.getInstance().getByKey(key);
            if (item != null) {
                return item.getAdapterItemType();
            } else {
                throw new NullPointerException("no type key =" + key);
            }
        }

        @Override
        public CommonViewHolder createByViewGroupAndType(ViewGroup parent, int viewType, Object... p) {
            TypeDataItem item = TypeProvider.getInstance().getByType(viewType);
            if (item != null) {
                if (item.adapterItemType == TypeProvider.TYPE_VIDEO) {
                    return new VideoCollectionViewHolder(parent.getContext(), parent);
                } else {
                    return item.getHolder(parent, viewType);
                }
            }
            return null;
        }
    }


    @Override
    public ViewHolderCreator createViewHolderCreator() {
        return new CustomerHolderCreator();
    }
}
