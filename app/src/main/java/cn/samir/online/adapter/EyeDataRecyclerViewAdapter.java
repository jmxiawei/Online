package cn.samir.online.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dinuscxj.pullzoom.ILoadMoreView;
import com.dinuscxj.pullzoom.PullZoomRecyclerView;

import java.util.List;

import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.TextCard;
import cn.samir.online.adapter.holders.ViewHolderCreator;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.http.converts.TypeDataItem;
import cn.samir.online.http.converts.TypeProvider;

/**
 * 每次定义一个类型的holder ，都要在{@link TypeProvider} 里添加一个数据
 * <p>
 * <p>
 * <p>
 * <p>
 * Created by xiawei on 2017/3/15.
 */
public class EyeDataRecyclerViewAdapter extends BaseRecyclerAdapter<BaseModel> {


    private boolean hasHeader = false;

    private RecyclerView mRecyclerView;


    public EyeDataRecyclerViewAdapter(boolean hasHeader, RecyclerView mRecyclerView) {
        this.hasHeader = hasHeader;
        this.mRecyclerView = mRecyclerView;
        viewHolderCreator = createViewHolderCreator();
    }

    public void setPullZoomRecyclerView(PullZoomRecyclerView pullZoomRecyclerView) {
        if (viewHolderCreator instanceof HolderCreator) {
            ((HolderCreator) viewHolderCreator).setPullZoomRecyclerView(pullZoomRecyclerView);
        }
    }

    @Override
    public ViewHolderCreator createViewHolderCreator() {
        return new HolderCreator(this.mRecyclerView);
    }


    @Override
    public void setLoadMore(int loadMoreState) {
        this.setLoadMore(loadMoreState, true);
    }

    @Override
    public void setLoadMore(int loadMoreState, boolean addView) {
        super.setLoadMore(loadMoreState, addView);
        if (!addView) {
            return;
        }
        if (this.loadMoreState == ILoadMoreView.STATE_LOADING) {
            //开始加载更多

            if (dataList.size() > 0) {
                BaseModel bm = dataList.get(dataList.size() - 1);
                if (bm.getType().equals(TypeProvider.LOAD_MORE_FOOTER)) {
                    dataList.remove(bm);
                }
            }
            BaseModel bm = new BaseModel();
            bm.setType(TypeProvider.LOAD_MORE_FOOTER);
            dataList.add(bm);
            if (!mRecyclerView.isComputingLayout()) {
                notifyItemChanged(getItemCount() - 1);
            }
        } else if (this.loadMoreState == ILoadMoreView.STATE_COMPLETE) {
            if (dataList.size() > 0) {
                BaseModel bm = dataList.get(dataList.size() - 1);
                if (bm.getType().equals(TypeProvider.LOAD_MORE_FOOTER)) {
                    dataList.remove(bm);
                }
                if (!mRecyclerView.isComputingLayout()) {
                    notifyItemChanged(getItemCount() - 1);
                }
            }

        } else if (this.loadMoreState == ILoadMoreView.STATE_NOMORE) {
            BaseModel bm = new BaseModel();
            bm.setType(TypeProvider.LOAD_NO_MORE_FOOTER);
            dataList.add(bm);
            if (!mRecyclerView.isComputingLayout()) {
                notifyItemChanged(getItemCount() - 1);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        BaseModel bs = getItem(position);
        return viewHolderCreator.getType(bs.getType(), bs);
    }

    public void setDataList(List<BaseModel> datas) {
        dataList.clear();

        if (hasHeader) {//添加 头部
            BaseModel<String> headerItem = new BaseModel<>();
            headerItem.setType(TypeProvider.PULL_HEADER);
            dataList.add(headerItem);
        }

        if (needMaskToolbar) {
            BaseModel<String> maskToolbar = new BaseModel<>();
            maskToolbar.setType(TypeProvider.maskToolbar);
            dataList.add(maskToolbar);
        }

        if (null != datas) {
            dataList.addAll(datas);
        }
        notifyDataSetChanged();
    }


    public BaseModel getItem(int position) {
        return dataList.get(position);
    }


    @Override
    public int getItemCount() {
        int count = dataList.size();
        return count;

    }


    public static class HolderCreator implements ViewHolderCreator {

        PullZoomRecyclerView pullZoomRecyclerView;

        RecyclerView recyclerView;

        public HolderCreator(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        public void setPullZoomRecyclerView(PullZoomRecyclerView pullZoomRecyclerView) {
            this.pullZoomRecyclerView = pullZoomRecyclerView;
        }

        @Override
        public int getType(String key, BaseModel bm) {

            if (TypeProvider.TEXT_CARD.equals(key)) {
                BaseModel<TextCard> bmtc = bm;
                key += "_" + bmtc.getData().getType();
            }
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
                if (item.adapterItemType == TypeProvider.TYPE_HEADER) {
                    return item.getHolder(parent, viewType, pullZoomRecyclerView);
                } else {
                    return item.getHolder(parent, viewType);
                }
            }
            return null;
        }
    }
}
