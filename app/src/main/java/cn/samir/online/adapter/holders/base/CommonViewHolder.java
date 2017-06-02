package cn.samir.online.adapter.holders.base;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cn.samir.online.R;
import cn.samir.online.adapter.ItemSettings;
import cn.samir.online.events.Sender;


public abstract class CommonViewHolder<T> extends RecyclerView.ViewHolder {

    protected ItemSettings mItemSettings;

    protected Object[] mExtras;

    public CommonViewHolder(Context context, ViewGroup root, int layoutRes) {
        super(LayoutInflater.from(context).inflate(layoutRes, root, false));
        ButterKnife.bind(this, itemView);
        setItemViewClickListener();
    }


    public void setExtras(Object... extras) {
        this.mExtras = extras;
    }

    /**
     *
     */
    protected void setItemViewClickListener() {
        itemView.setOnClickListener(itemViewClickListener);
    }

    /**
     * 点击事件
     */
    protected View.OnClickListener itemViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Parcelable p = getDataForBroadcast();
            if (p != null) {
                Sender.send(getContext(), p);
//                Intent event = new Intent(Constant.ACTION_EVENT);
//                event.putExtra(Constant.ACTION_EVENT_DATA, getDataForBroadcast());
//                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(event);
            }
        }
    };

    protected void updateSize() {
        if (mItemSettings.getWidth() != ItemSettings.INVALID && mItemSettings.getHeight() != ItemSettings.INVALID) {
            ViewGroup.LayoutParams ll = itemView.getLayoutParams();
            ll.width = mItemSettings.width;
            ll.height = mItemSettings.height;
            itemView.setLayoutParams(ll);
        }
    }

    public Parcelable getDataForBroadcast() {
        return (Parcelable) null;
    }

    public ItemSettings getItemSettings() {
        return mItemSettings;
    }

    public void setItemSettings(ItemSettings mItemSettings) {
        this.mItemSettings = mItemSettings;
    }

    public CommonViewHolder(View layoutView) {
        super(layoutView);
    }


    public Context getContext() {
        return itemView.getContext();
    }

    public abstract void bindData(T t, int position);

    public void setData(T t, int position) {

        if (mItemSettings.isNeedWhiteBackground()) {
            itemView.setBackgroundResource(R.color.color_white);
        } else {
            itemView.setBackgroundResource(0);
        }

        bindData(t, position);
    }

    public void setNextPreType(String pre, String me, Object... params) {

    }


    protected void noBackground() {
        itemView.setBackgroundResource(0);
    }

    public <T extends View> T getView(int id) {
        return (T) itemView.findViewById(id);
    }

    public interface ViewHolderCreator<VH extends CommonViewHolder> {
        public VH createByViewGroupAndType(ViewGroup parent, int viewType);
    }


}