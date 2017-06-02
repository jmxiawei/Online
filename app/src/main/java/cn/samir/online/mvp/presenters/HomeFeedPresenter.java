package cn.samir.online.mvp.presenters;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.BaseHasNextPagePresenter;
import cn.samir.online.mvp.cases.HomeFeedCase;
import cn.samir.online.mvp.intefaces.IHomeFeedCase;
import cn.samir.online.mvp.intefaces.IHomeFeedView;
import cn.samir.online.util.Constant;
import cn.samir.online.util.L;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 精选
 * Created by xiaw on 2017/4/6 0006.
 */

public class HomeFeedPresenter extends BaseHasNextPagePresenter {


    private IHomeFeedCase homeFeedCase;

    public HomeFeedPresenter(BaseHttpHandler baseHttpHandler, IHomeFeedView homeFeedView) {
        super(baseHttpHandler);
        this.presenterView = homeFeedView;
        homeFeedCase = new HomeFeedCase(baseHttpHandler);
    }

    protected Callback<ApiResult<ArrayList<BaseModel>>> callback = new Callback<ApiResult<ArrayList<BaseModel>>>() {
        @Override
        public void onResponse(Call<ApiResult<ArrayList<BaseModel>>> call, Response<ApiResult<ArrayList<BaseModel>>> response) {
            checkSuccess(response, presenterView);
        }

        @Override
        public void onFailure(Call<ApiResult<ArrayList<BaseModel>>> call, Throwable t) {
            L.e("onFailure=" + t.getMessage());
            presenterView.onFail(Constant.ERROR_CODE_NET);
        }
    };


    public void loadHome() {
        Call<ApiResult<ArrayList<BaseModel>>> resultCall = homeFeedCase.loadHome();
        loadFromNet(resultCall);
    }

    private void loadFromNet(Call<ApiResult<ArrayList<BaseModel>>> resultCall) {
        L.e("resultCall" + resultCall.request().url().toString());
        resultCall.enqueue(callback);
    }


    public void nextPage(String url) {
        Call<ApiResult<ArrayList<BaseModel>>> resultCall = homeFeedCase.nextPage(url);
        loadFromNet(resultCall);
    }
}
