package cn.samir.online.mvp.presenters;

import android.os.Bundle;

import cn.samir.online.util.LogUtil;

import cn.samir.domains.model.TabInfo;
import cn.samir.domains.model.TabInfoResult;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.BasePresenter;
import cn.samir.online.mvp.intefaces.ITabViewPagerView;
import cn.samir.online.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaw on 2017/5/9 0009.
 */

public class TabViewPagerPresenter extends BasePresenter {


    private Bundle argments;

    private ITabViewPagerView tabViewPagerView;

    public TabViewPagerPresenter(BaseHttpHandler baseHttpHandler, ITabViewPagerView imp, Bundle argments) {
        super(baseHttpHandler);
        this.tabViewPagerView = imp;
        this.argments = argments;
        start();
    }

    private void start() {
        int type = this.argments.getInt(ITabViewPagerView.PARAM_TAB_TYPE);
        String tabUrl = this.argments.getString(ITabViewPagerView.PARAM_TAB_URL);
        int defaultIndex = this.argments.getInt(ITabViewPagerView.PARAM_DEFAULT_INDEX);
        if (type == ITabViewPagerView.TYPE_TABURL) {
            loadTab(tabUrl);
        } else {
            TabInfo tabInfo = this.argments.getParcelable(ITabViewPagerView.PARAM_TABS);
            tabInfo.setDefaultIdx(defaultIndex);
            TabInfoResult result = new TabInfoResult();
            result.setTabInfo(tabInfo);
            this.tabViewPagerView.onSuccess(result);
        }
    }


    public void loadTab(String tabUrl) {

        LogUtil.e("loadTab=" + tabUrl);
        Call<TabInfoResult> result = baseHttpHandler.getProxy(ApiService.class).loadTab(tabUrl);
        result.enqueue(new Callback<TabInfoResult>() {
            @Override
            public void onResponse(Call<TabInfoResult> call, Response<TabInfoResult> response) {
                if (response.isSuccessful()) {
                    TabInfoResult tabInfoResult = response.body();
                    tabViewPagerView.onSuccess(tabInfoResult);
                    LogUtil.e(tabInfoResult.toString());
                } else {
                    tabViewPagerView.onFail(Constant.ERROR_CODE_NET);
                }
            }

            @Override
            public void onFailure(Call<TabInfoResult> call, Throwable t) {
                tabViewPagerView.onFail(Constant.ERROR_CODE_NET);
            }
        });
    }


}
