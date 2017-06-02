package cn.samir.online.adapter.holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import cn.samir.domains.model.MessageListModel;
import cn.samir.domains.model.ParcelableString;
import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.util.Utils;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/5/26 0026.
 */

public class MessageListHolder extends CommonViewHolder<MessageListModel.MessageListEntity> {


    @BindView(R.id.sdv_icon)
    SimpleDraweeView sdvIcon;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.tv_date)
    CustomFontTextView tvDate;
    @BindView(R.id.tv_content)
    CustomFontTextView tvContent;

    protected MessageListModel.MessageListEntity mEntity;

    public MessageListHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_holder_message_list);
    }

    @Override
    public void bindData(MessageListModel.MessageListEntity messageListEntity, int position) {
        this.mEntity = messageListEntity;
        PhotoUtil.showPhoto(sdvIcon, messageListEntity.getIcon());
        tvContent.setText(messageListEntity.getContent());
        tvTitle.setText(messageListEntity.getTitle());
        tvDate.setText(Utils.formatDayOffset(messageListEntity.getDate()));
    }


    @Override
    public Parcelable getDataForBroadcast() {
        return new ParcelableString(this.mEntity.getActionUrl());
    }
}
