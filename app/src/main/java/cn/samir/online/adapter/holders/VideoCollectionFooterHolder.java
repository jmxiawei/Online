package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.ViewGroup;

import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;

/**
 * Created by xiawei on 2017/3/16.
 */

public class VideoCollectionFooterHolder extends CommonViewHolder {
    public VideoCollectionFooterHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_video_collection_footer);
        int w = root.getHeight();
        ViewGroup.LayoutParams ll = itemView.getLayoutParams();
        ll.width = w;
        ll.height = w;
        itemView.setLayoutParams(ll);

    }

    @Override
    public void bindData(Object o, int position) {

    }
}
