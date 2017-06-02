package cn.samir.online.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dinuscxj.pullzoom.ILoadMoreView;
import com.dinuscxj.pullzoom.PullZoomBaseView;
import com.dinuscxj.pullzoom.PullZoomRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.AppInfo;
import cn.samir.domains.model.BaseModel;
import cn.samir.online.R;
import cn.samir.online.adapter.EyeDataRecyclerViewAdapter;
import cn.samir.online.adapter.OnReachBottomListener;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.http.converts.CustomConvertFactory;
import cn.samir.online.mvp.intefaces.IHomeFeedView;
import cn.samir.online.mvp.presenters.HomeFeedPresenter;
import cn.samir.online.ui.base.BaseFragment;
import cn.samir.online.util.L;
import cn.samir.online.util.Utils;
import cn.samir.online.views.EyeTopImageDialog;
import cn.samir.online.views.HeaderZoomView;
import cn.samir.online.views.LoadingAnimView;


public class HomeFeedFragment extends BaseFragment implements IHomeFeedView, PullZoomBaseView.OnPullZoomListener, OnReachBottomListener {

    private HomeFeedPresenter homeFeedPresenter;
    public static final String TAG = HomeFeedFragment.class.getSimpleName();

    @BindView(R.id.hzv_header)
    HeaderZoomView hzvHeader;
    @BindView(R.id.pull_recycler)
    PullZoomRecyclerView pullZoomRecyclerView;

    private ApiResult<ArrayList<BaseModel>> apiResult;

    private boolean isPullRefresh = false;//顶部刷新，清空列表


    private EyeDataRecyclerViewAdapter eyeDataRecyclerViewAdapter;

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            checkPosition(getToolbar(), recyclerView);
        }
    };

    public static HomeFeedFragment newInstance() {
        HomeFeedFragment fragment = new HomeFeedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private void checkPosition(LinearLayout toolbar, RecyclerView target) {

        if (toolbar == null || target == null) {
            return;
        }

        int top = checkFirstItemCompletelyVisible(target);
        float toolbarAlpha = Math.abs(1 - (Math.abs(top) * 1.0f / childHeight));
        if (top != INVALID) {
            if (toolbarAlpha < 0.05f) {
                toolbarAlpha = 0;
            } else if (toolbarAlpha > 1) {
                toolbarAlpha = 1;
            }
            toolbar.setAlpha(toolbarAlpha);
        } else {
            toolbar.setAlpha(0);
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (pullZoomRecyclerView != null) {
                checkPosition(getToolbar(), pullZoomRecyclerView.getRecyclerView());
            }
        }
    }

    private static final int INVALID = -10000;
    private static final int INVALID_NULL_DATA = -20000;
    private static final int childHeight = 660;

    private int checkFirstItemCompletelyVisible(RecyclerView recyclerView) {
        RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
        if (lm == null) {
            return INVALID_NULL_DATA;
        }
        if (lm.getChildCount() == 0) {
            return INVALID_NULL_DATA;
        }
        View child = lm.getChildAt(0);
        int firstVisiblePosition = ((RecyclerView.LayoutParams) child.getLayoutParams()).getViewAdapterPosition();
        int top = child.getTop();
        if (firstVisiblePosition == 0) {
            return top;
        }
        return INVALID;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        pullZoomRecyclerView = (PullZoomRecyclerView) view.findViewById(R.id.pull_recycler);
        pullZoomRecyclerView.addOnPullZoomListener(this);
        pullZoomRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        eyeDataRecyclerViewAdapter = new EyeDataRecyclerViewAdapter(true, pullZoomRecyclerView.getRecyclerView());
        eyeDataRecyclerViewAdapter.setPullZoomRecyclerView(pullZoomRecyclerView);
        pullZoomRecyclerView.setAdapter(eyeDataRecyclerViewAdapter);
        pullZoomRecyclerView.getRecyclerView().addOnScrollListener(this.onScrollListener);
        eyeDataRecyclerViewAdapter.setOnReachBottomListener(this);
        homeFeedPresenter = new HomeFeedPresenter(new BaseHttpHandler(CustomConvertFactory.create()), this);
        homeFeedPresenter.loadHome();
        hzvHeader.setLoadingMode(LoadingAnimView.Mode.REFRESH, "");
//
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_home_feed;
    }

    @Override
    public boolean onSuccess(Object... params) {
        L.e("more onSuccess" + params[0].toString());
        if (params[0] instanceof ApiResult) {

            if (isPullRefresh) {
                eyeDataRecyclerViewAdapter.clearData();
            }
            this.apiResult = (ApiResult<ArrayList<BaseModel>>) params[0];
            if (eyeDataRecyclerViewAdapter.getItemCount() == 0) {
                eyeDataRecyclerViewAdapter.setDataList(this.apiResult.itemList);
            } else {
                eyeDataRecyclerViewAdapter.setLoadMore(ILoadMoreView.STATE_COMPLETE);
                eyeDataRecyclerViewAdapter.appendDataList(this.apiResult.itemList);
            }
        }
        if (this.apiResult != null) {
            AppInfo.instance().setDate(this.apiResult.date);
        }
        hzvHeader.setLoadingMode(LoadingAnimView.Mode.TEXT, AppInfo.instance().getDateString());

        if (apiResult.dialog != null) {
            EyeTopImageDialog dialog = new EyeTopImageDialog(getActivity());
            dialog.setDialogNode(apiResult.dialog);
        }
        return false;
    }


    @Override
    public boolean onFail(Object... params) {
        hzvHeader.setLoadingMode(LoadingAnimView.Mode.TEXT, AppInfo.instance().getDateString());
        return false;
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onPullUp(float newScrollValue) {
    }

    @Override
    public void onPullZooming(float newScrollValue) {

    }

    @Override
    public void onPullStart() {

    }

    @Override
    public void onPullZoomEnd(float newScrollValue) {
        if (Math.abs(newScrollValue) > HeaderZoomView.PULL_THRESHOLD) {
            //refresh
            isPullRefresh = true;
            hzvHeader.setLoadingMode(LoadingAnimView.Mode.REFRESH, "");
            homeFeedPresenter.loadHome();
        } else {
            hzvHeader.setLoadingMode(LoadingAnimView.Mode.TEXT, AppInfo.instance().getDateString());
        }
    }

    @Override
    public void onReachBottom() {

        if (apiResult != null) {
            String url = apiResult.nextPageUrl;
            if (!Utils.isEmpty(url)) {
                L.e("load more=" + url);
                isPullRefresh = false;
                eyeDataRecyclerViewAdapter.setLoadMore(ILoadMoreView.STATE_LOADING);
                homeFeedPresenter.nextPage(url);
            } else {
                eyeDataRecyclerViewAdapter.setLoadMore(ILoadMoreView.STATE_NOMORE);
            }
        }
    }
}
