package cn.samir.online.adapter.holders;

import android.content.Context;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.PhotoUtil;

import static cn.samir.online.R.id.sdv_icon;

/**
 * Created by xiawei on 2017/3/16.
 */

public class VideoCollectionFollowHeaderIconViewHolder extends CommonViewHolder<String> {


    @BindView(sdv_icon)
    SimpleDraweeView sdvIcon;

    public VideoCollectionFollowHeaderIconViewHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_video_collection_follow_header_icon);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(String s, int position) {

        itemView.setBackgroundResource(0);
        PhotoUtil.showPhoto(sdvIcon, s, 100
                , 100);
    }
}
