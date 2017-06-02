package cn.samir.online.mvp.presenters;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.http.converts.CustomConvertFactory;
import cn.samir.online.mvp.BaseHasNextPagePresenter;
import cn.samir.online.mvp.PresenterView;
import cn.samir.online.util.L;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaw on 2017/4/10 0010.
 */

public class RecyclerListPresenter extends BaseHasNextPagePresenter {


    public RecyclerListPresenter(BaseHttpHandler baseHttpHandler, PresenterView presenterView) {
        super(baseHttpHandler);
        this.presenterView = presenterView;
    }

    public RecyclerListPresenter(PresenterView presenterView) {
        super(new BaseHttpHandler(CustomConvertFactory.create()));
        this.presenterView = presenterView;
    }

    protected Callback callback = new Callback<ApiResult<ArrayList<BaseModel>>>() {
        @Override
        public void onResponse(Call<ApiResult<ArrayList<BaseModel>>> call, Response<ApiResult<ArrayList<BaseModel>>> response) {
            checkSuccess(response, presenterView);
        }

        @Override
        public void onFailure(Call<ApiResult<ArrayList<BaseModel>>> call, Throwable t) {
            fail(t, presenterView);
        }
    };


    public void loadData(String url) {
        L.e("url=" + url);
        Call<ApiResult<ArrayList<BaseModel>>> resultCall = baseHttpHandler.getProxy(ApiService.class).loadFindCateList(url);
        L.e("url=" + resultCall.request().url());
        resultCall.enqueue(callback);
    }

    @Override
    public void nextPage(String url) {

    }


    public void loadKeyWord(String keyWord) {
        Call<ApiResult<ArrayList<BaseModel>>> resultCall = baseHttpHandler.getProxy(ApiService.class).loadByKeyWord(keyWord);
        resultCall.enqueue(callback);
    }
}
