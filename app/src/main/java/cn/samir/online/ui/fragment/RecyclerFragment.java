package cn.samir.online.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dinuscxj.pullzoom.ILoadMoreView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.online.R;
import cn.samir.online.adapter.BaseRecyclerAdapter;
import cn.samir.online.adapter.EyeDataRecyclerViewAdapter;
import cn.samir.online.adapter.ItemSettings;
import cn.samir.online.adapter.OnReachBottomListener;
import cn.samir.online.adapter.decorations.SquareCardRightDecorations;
import cn.samir.online.mvp.PresenterView;
import cn.samir.online.mvp.presenters.RecyclerListPresenter;
import cn.samir.online.ui.base.BaseFragment;
import cn.samir.online.util.L;
import cn.samir.online.util.Utils;

/**
 * Created by xiaw on 2017/4/7 0007.
 */

public class RecyclerFragment extends BaseFragment implements PresenterView, OnReachBottomListener {


    public static final String TAG = RecyclerFragment.class.getSimpleName();

    public static final String PARAM_URL = "RecyclerFragmentPARAM_URL";

    public static final String PARAM_MASK_TOOLBAR = "PARAM_MASK_TOOLBAR";

    public static final String PARAM_HAS_SNAP_HELP = "PARAM_HAS_SNAP_HELP";

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    protected RecyclerView.LayoutManager mLayoutManager;
    protected ArrayList<BaseModel> datas;
    protected BaseRecyclerAdapter<BaseModel> dataRecyclerViewAdapter;
    protected ItemSettings mItemSettings;

    protected String url;
    protected RecyclerListPresenter recyclerListPresenter;
    protected ApiResult apiResult;


    private boolean needMaskToolbar = false;
    private boolean hasSnapHelp = false;

    public static RecyclerFragment newInstance(String url) {
        return newInstance(url, true);
    }

    public static RecyclerFragment newInstance(String url, boolean hasSnapHelp) {
        Bundle params = new Bundle();
        params.putString(PARAM_URL, url);
        params.putBoolean(PARAM_HAS_SNAP_HELP, hasSnapHelp);
        RecyclerFragment rf = new RecyclerFragment();
        rf.setArguments(params);
        return rf;
    }

    public ItemSettings getItemSettings() {
        return mItemSettings;
    }

    public void setItemSettings(ItemSettings mItemSettings) {
        this.mItemSettings = mItemSettings;
    }

    public void setDatas(ArrayList<BaseModel> datas) {
        this.datas = datas;
        dataRecyclerViewAdapter.setDataList(this.datas);
    }

    public void needMaskToolbar(boolean needMakskToolbar) {
        this.needMaskToolbar = needMakskToolbar;
        if (dataRecyclerViewAdapter != null) {
            dataRecyclerViewAdapter.setNeedMaskToolbar(this.needMaskToolbar);
        }
    }

    public void loadUrl(String url) {
        if (recyclerListPresenter != null) {
            recyclerListPresenter.loadData(url);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        recyclerListPresenter = new RecyclerListPresenter(this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            url = getArguments().getString(PARAM_URL);
            hasSnapHelp = arguments.getBoolean(PARAM_HAS_SNAP_HELP, true);
            if (!Utils.isEmpty(url)) {
                recyclerListPresenter.loadData(url);
            }
            needMaskToolbar = getArguments().getBoolean(PARAM_MASK_TOOLBAR, false);
        }

        mLayoutManager = getLayoutManager();
        recyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = getItemDecorations();
        if (itemDecoration != null) {
            recyclerView.addItemDecoration(itemDecoration);
        }
        dataRecyclerViewAdapter = getAdapter();
        dataRecyclerViewAdapter.setItemSettings(getItemSettings());
        dataRecyclerViewAdapter.setNeedMaskToolbar(needMaskToolbar);
        dataRecyclerViewAdapter.setHasSnapHelp(hasSnapHelp);
        recyclerView.setAdapter(dataRecyclerViewAdapter);

        dataRecyclerViewAdapter.setOnReachBottomListener(this);
    }


    protected RecyclerView.ItemDecoration getItemDecorations() {
        return new SquareCardRightDecorations();
    }


    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    }

    public BaseRecyclerAdapter<BaseModel> getAdapter() {
        EyeDataRecyclerViewAdapter edr = new EyeDataRecyclerViewAdapter(false, recyclerView);
        edr.setHasSnapHelp(hasSnapHelp);
        return edr;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public boolean onSuccess(Object... params) {
        if (params[0] instanceof ApiResult) {
            ApiResult<ArrayList<BaseModel>> apiResult = (ApiResult<ArrayList<BaseModel>>) params[0];
            if (dataRecyclerViewAdapter.getItemCount() == 0) {


                setDatas(apiResult.itemList);
            } else {
                dataRecyclerViewAdapter.setLoadMore(ILoadMoreView.STATE_COMPLETE);
                dataRecyclerViewAdapter.appendDataList(apiResult.itemList);
            }
            this.apiResult = apiResult;
        }
        return false;
    }

    @Override
    public boolean onFail(Object... params) {
               return false;
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onReachBottom() {
        if (apiResult != null) {
            String url = apiResult.nextPageUrl;
            if (!Utils.isEmpty(url)) {
                L.e("load more=" + url);
                dataRecyclerViewAdapter.setLoadMore(ILoadMoreView.STATE_LOADING);
                recyclerListPresenter.loadData(url);
            } else {
                dataRecyclerViewAdapter.setLoadMore(ILoadMoreView.STATE_NOMORE);
            }
        }
    }
}
