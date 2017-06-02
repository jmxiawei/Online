package cn.samir.online.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dinuscxj.pullzoom.ILoadMoreView;

import java.util.ArrayList;
import java.util.List;

import cn.elvin.customlib.L;
import cn.samir.domains.model.BaseModel;
import cn.samir.online.adapter.holders.ViewHolderCreator;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.LogUtil;

/**
 * Created by xiawei on 2017/3/16.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {


    protected List<T> dataList = new ArrayList<>();

    private boolean hasSnapHelp = true;
    protected boolean needMaskToolbar = false;

    protected ViewHolderCreator viewHolderCreator;
    protected OnReachBottomListener onReachBottomListener;
    protected int loadMoreState = ILoadMoreView.STATE_COMPLETE;
    private ItemSettings mItemSettings;

    public ItemSettings getItemSettings() {
        return mItemSettings;
    }

    public boolean isHasSnapHelp() {
        return hasSnapHelp;
    }

    public void setHasSnapHelp(boolean hasSnapHelp) {
        this.hasSnapHelp = hasSnapHelp;
    }

    public void setItemSettings(ItemSettings mItemSettings) {
        this.mItemSettings = mItemSettings;
    }

    public abstract ViewHolderCreator createViewHolderCreator();

    public OnReachBottomListener getOnReachBottomListener() {
        return onReachBottomListener;
    }

    public void setOnReachBottomListener(OnReachBottomListener onReachBottomListener) {
        this.onReachBottomListener = onReachBottomListener;
    }

    public void setNeedMaskToolbar(boolean needMaskToolbar) {
        this.needMaskToolbar = needMaskToolbar;
    }

    public BaseRecyclerAdapter() {
        viewHolderCreator = createViewHolderCreator();
        if (viewHolderCreator == null) {
            L.e("viewHolderCreator == null" + getClass().getSimpleName());
        }
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewHolderCreator == null) {
            //放在构造函数里，createViewHolderCreator 里的数据会在之类构造方法之前执行
            viewHolderCreator = createViewHolderCreator();
        }
        return viewHolderCreator.createByViewGroupAndType(parent, viewType);
    }


    private void updateDivider(CommonViewHolder holder, int position) {
        String pre;
        String me;
        T obj = getItem(position);
        if (obj instanceof BaseModel) {
            BaseModel bm = (BaseModel) obj;
            int prePosition = position - 1;
            me = bm.getType();
            if (prePosition < 0) {
                //第一个
                pre = null;
            } else {
                pre = ((BaseModel) getItem(prePosition)).getType();
            }
            holder.setNextPreType(pre, me, bm);
        } else {
            LogUtil.e(" not BaseModel");
        }
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        if (position >= 0 && position < getItemCount()) {
            // L.e("onBindViewHolder", getItem(position).toString() + "position=" + position + "cout=" + getItemCount());
            bindItemSettings(holder);
            holder.setExtras(this);
            updateDivider(holder, position);
            holder.setData(getItem(position), position);
        }

        if (position == getItemCount() - 1 && this.loadMoreState == ILoadMoreView.STATE_COMPLETE) {
            if (onReachBottomListener != null) {
                onReachBottomListener.onReachBottom();
            }
        }

    }

    private void bindItemSettings(CommonViewHolder holder) {
        if (holder.getItemSettings() == null) {
            if (mItemSettings == null) {
                mItemSettings = new ItemSettings(ItemSettings.BACKGROUND_LIGHT, isHasSnapHelp());
            }
            holder.setItemSettings(mItemSettings);
        }
    }

    public void setLoadMore(int loadMoreState) {
        setLoadMore(loadMoreState, true);
    }

    public void clearData() {
        dataList.clear();
    }

    public void setLoadMore(int loadMoreState, boolean addView) {
        this.loadMoreState = loadMoreState;
    }

    public void appendDataList(List<T> datas) {
        if (null != datas) {
            dataList.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void setDataList(List<T> datas) {
        dataList.clear();
        if (null != datas) {
            dataList.addAll(datas);
        }
        notifyDataSetChanged();
    }


    public T getItem(int position) {
        if (position < dataList.size()) {
            return dataList.get(position);
        } else {
            return null;
        }
    }

    public void addDataList(List<T> datas) {
        dataList.addAll(datas);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();

    }
}
