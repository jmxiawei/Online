package cn.samir.online.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.online.R;
import cn.samir.online.views.CustomFontTextView;
import cn.samir.online.views.LogoInnerOuterAnimView;

/**
 * Created by xiaw on 2017/4/19 0019.
 */

public class SearchResultFragment extends RecyclerFragment {


    @BindView(R.id.loading_view)
    LogoInnerOuterAnimView loadingView;
    @BindView(R.id.tv_result)
    CustomFontTextView tvResult;

    protected String keyWord;

    @Override
    public int getLayout() {
        return R.layout.fragment_search_result;
    }

    protected boolean isReload = false;

    public void query(String keyWord) {
        this.keyWord = keyWord;

        isReload = true;
        if (recyclerListPresenter != null) {
            loadingView.setVisibility(View.VISIBLE);
            tvResult.setVisibility(View.GONE);
            recyclerListPresenter.loadKeyWord(keyWord);
        }
    }

    @Override
    public boolean onSuccess(Object... params) {
        if (isReload) {
            isReload = false;
            dataRecyclerViewAdapter.clearData();
        }
        super.onSuccess(params);
        showResultInfo();
        return false;
    }

    private void showResultInfo() {
        loadingView.setVisibility(View.GONE);
        tvResult.setVisibility(View.VISIBLE);
        tvResult.setText(String.format(getString(R.string.text_show_search_result), keyWord, apiResult.total));
    }

    @Override
    public boolean onFail(Object... params) {
        super.onFail(params);
        return false;
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingView.setVisibility(View.VISIBLE);
        tvResult.setVisibility(View.GONE);
        recyclerListPresenter.loadKeyWord(keyWord);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(this.getLayout(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
