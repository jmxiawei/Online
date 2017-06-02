package cn.samir.online.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dinuscxj.pullzoom.ILoadMoreView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.MessageListModel;
import cn.samir.online.R;
import cn.samir.online.adapter.BaseRecyclerAdapter;
import cn.samir.online.adapter.OnReachBottomListener;
import cn.samir.online.adapter.holders.MessageListHolder;
import cn.samir.online.adapter.holders.NoMoreHolder;
import cn.samir.online.adapter.holders.ViewHolderCreator;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.PresenterView;
import cn.samir.online.mvp.presenters.MessageListPresenter;
import cn.samir.online.ui.base.BaseFragment;
import cn.samir.online.util.Utils;

/**
 * Created by xiaw on 2017/5/26 0026.
 */

public class MessageListFragment extends BaseFragment implements PresenterView, OnReachBottomListener {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private RecyclerView.LayoutManager mLayoutManager;
    private MessageListAdapter messageListAdapter;
    private MessageListPresenter messageListPresenter;
    private MessageListModel messageListModel;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        messageListAdapter = new MessageListAdapter();
        recyclerView.setAdapter(messageListAdapter);
        messageListAdapter.setOnReachBottomListener(this);
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        messageListPresenter = new MessageListPresenter(this, new BaseHttpHandler());
        messageListPresenter.loadMessages();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public boolean onSuccess(Object... params) {
        if (params[0] instanceof MessageListModel) {
            messageListModel = (MessageListModel) params[0];
            messageListAdapter.setDataList(((MessageListModel) params[0]).getMessageList());
        }
        return false;
    }

    @Override
    public boolean onFail(Object... params) {
        return false;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onReachBottom() {
        if (messageListModel != null) {
            String url = messageListModel.getNextPageUrl();
            if (!Utils.isEmpty(url)) {
                messageListAdapter.setLoadMore(ILoadMoreView.STATE_LOADING);
            } else {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        messageListAdapter.setLoadMore(ILoadMoreView.STATE_NOMORE);
                    }
                }, 50);
            }
        }
    }

    public static final class MessageListAdapter extends BaseRecyclerAdapter<MessageListModel.MessageListEntity> {

        @Override
        public ViewHolderCreator createViewHolderCreator() {
            return mViewHolderCreator;
        }

        @Override
        public void setLoadMore(int loadMoreState, boolean addView) {

            if (this.loadMoreState != ILoadMoreView.STATE_NOMORE && loadMoreState == ILoadMoreView.STATE_NOMORE) {
                notifyDataSetChanged();
            }
            this.loadMoreState = loadMoreState;
        }

        @Override
        public int getItemCount() {

            if (this.loadMoreState == ILoadMoreView.STATE_NOMORE) {
                return super.getItemCount() + 1;
            }
            return super.getItemCount();
        }


        @Override
        public int getItemViewType(int position) {
            if (this.loadMoreState == ILoadMoreView.STATE_NOMORE && position == getItemCount() - 1) {
                return 1;
            } else {
                return 0;
            }
        }

        private final ViewHolderCreator mViewHolderCreator = new ViewHolderCreator() {
            @Override
            public CommonViewHolder createByViewGroupAndType(ViewGroup parent, int viewType, Object... p) {

                if (viewType == 1) {
                    return new NoMoreHolder(parent.getContext(), parent);
                } else {
                    return new MessageListHolder(parent.getContext(), parent);
                }


            }

            @Override
            public int getType(String key, BaseModel bm) {
                return 0;
            }
        };

    }


}
