package cn.samir.online.mvp.presenters;

import cn.samir.online.util.LogUtil;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.TabInfoResult;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.BaseHasNextPagePresenter;
import cn.samir.online.mvp.cases.HomeCategoryCase;
import cn.samir.online.mvp.intefaces.IHomeCategoryCase;
import cn.samir.online.mvp.intefaces.IHomeCategoryView;
import cn.samir.online.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaw on 2017/4/6 0006.
 */

public class HomeCategoryPresenter extends BaseHasNextPagePresenter {


    private IHomeCategoryCase homeFindModel;

    public HomeCategoryPresenter(BaseHttpHandler baseHttpHandler, IHomeCategoryView homeFindView) {
        super(baseHttpHandler);
        presenterView = homeFindView;
        this.homeFindModel = new HomeCategoryCase(baseHttpHandler);

    }

    public void loadCate() {
        Call<TabInfoResult> result = this.homeFindModel.loadCate();
        result.enqueue(new Callback<TabInfoResult>() {
            @Override
            public void onResponse(Call<TabInfoResult> call, Response<TabInfoResult> response) {
                if (response.isSuccessful()) {
                    TabInfoResult tabInfoResult = response.body();
                    presenterView.onSuccess(tabInfoResult);
                    LogUtil.e(tabInfoResult.toString());
                } else {
                    presenterView.onFail(Constant.ERROR_CODE_NET);
                }
            }

            @Override
            public void onFailure(Call<TabInfoResult> call, Throwable t) {
                presenterView.onFail(Constant.ERROR_CODE_NET);
            }
        });
    }


    public void loadFindCateList(String url) {
        Call<ApiResult<ArrayList<BaseModel>>> resultCall = this.homeFindModel.loadCateList(url);
        resultCall.enqueue(new Callback<ApiResult<ArrayList<BaseModel>>>() {
            @Override
            public void onResponse(Call<ApiResult<ArrayList<BaseModel>>> call, Response<ApiResult<ArrayList<BaseModel>>> response) {
                if (response.isSuccessful()) {
                    ApiResult<ArrayList<BaseModel>> tabInfoResult = response.body();
                    presenterView.onSuccess(tabInfoResult);
                    LogUtil.e(tabInfoResult.toString());
                } else {
                    presenterView.onFail(response.code());
                }
            }

            @Override
            public void onFailure(Call<ApiResult<ArrayList<BaseModel>>> call, Throwable t) {
                presenterView.onFail(Constant.ERROR_CODE_NET);
            }
        });
    }

    @Override
    public void nextPage(String url) {

    }
}
