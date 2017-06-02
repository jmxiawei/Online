package cn.samir.online.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dinuscxj.pullzoom.ILoadMoreView;
import cn.samir.online.util.LogUtil;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.IssueList;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.presenters.FeedIssuePresenter;

public class FeedHistoryFragment extends RecyclerFragment {

    public static final String PARAM_NUM = "PARAM_NUM";
    public static final String PARAM_DATE = "PARAM_DATE";
    private FeedIssuePresenter feedIssuePresenter;


    private int number = 3;
    private long date;

    public static FeedHistoryFragment newInstance(int number, long date) {
        LogUtil.e("FeedHistoryFragment url =" + number);
        Bundle params = new Bundle();
        params.putInt(PARAM_NUM, number);
        params.putLong(PARAM_DATE, date);
        params.putBoolean(PARAM_MASK_TOOLBAR, true);
        FeedHistoryFragment rf = new FeedHistoryFragment();
        rf.setArguments(params);
        return rf;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            number = args.getInt(PARAM_NUM, 3);
            date = args.getLong(PARAM_DATE, System.currentTimeMillis());
        }
        feedIssuePresenter = new FeedIssuePresenter(new BaseHttpHandler(), this);
        feedIssuePresenter.loadFeedHistory(number, date);
    }


    @Override
    public boolean onSuccess(Object... params) {
        ApiResult<ArrayList<IssueList>> data = (ApiResult<ArrayList<IssueList>>) params[0];
        if (dataRecyclerViewAdapter.getItemCount() == 0) {
            setDatas((ArrayList<BaseModel>) data.caches);
        } else {
            dataRecyclerViewAdapter.setLoadMore(ILoadMoreView.STATE_COMPLETE);
            dataRecyclerViewAdapter.appendDataList((ArrayList<BaseModel>) data.caches);
        }
        this.apiResult = data;
        return true;
    }

    @Override
    public boolean onFail(Object... params) {
        return super.onFail(params);
    }
}




