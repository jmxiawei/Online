package cn.samir.online.adapter;

import android.view.ViewGroup;

import cn.samir.domains.model.BaseModel;
import cn.samir.online.adapter.holders.VideoWithDescHolder;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.adapter.holders.ViewHolderCreator;
import cn.samir.online.http.converts.TypeDataItem;
import cn.samir.online.http.converts.TypeProvider;

/**
 * 类似viewpager 底部有标题时长
 * Created by xiaw on 2017/4/14 0014.
 */

public class VideoCollectionWithDescAdapter extends BaseLoopRecyclerAdapter {



    @Override
    public ViewHolderCreator createViewHolderCreator() {
        return new ViewHolderCreator() {
            @Override
            public CommonViewHolder createByViewGroupAndType(ViewGroup parent, int viewType, Object... p) {

                if (viewType == TypeProvider.TYPE_VIDEO) {
                    return new VideoWithDescHolder(parent.getContext(), parent);
                }
                return TypeProvider.getInstance().getByType(viewType).getHolder(parent, viewType);
            }

            @Override
            public int getType(String key, BaseModel bm) {

                TypeDataItem item = TypeProvider.getInstance().getByKey(key);
                if (item != null) {
                    return item.getAdapterItemType();
                } else {
                    throw new NullPointerException(" VideoCollectionWithDescAdapter no type key =" + key);
                }
            }
        };
    }
}
