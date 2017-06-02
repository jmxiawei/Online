package cn.samir.online.mvp;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.util.L;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by JakeWang on 8/14/16.
 */
public class BasePresenter {


    protected String TAG = getClass().getSimpleName();
    protected PresenterView presenterView;
    protected BaseHttpHandler baseHttpHandler;

    public BasePresenter(PresenterView presenterView, BaseHttpHandler baseHttpHandler) {
        this.presenterView = presenterView;
        this.baseHttpHandler = baseHttpHandler;
    }

    public BasePresenter(BaseHttpHandler baseHttpHandler) {
        this.baseHttpHandler = baseHttpHandler;
    }

    public void checkSuccess(Response response, PresenterView presenterView) {
        if (response.isSuccessful()) {
            presenterView.onSuccess(response.body());
        } else {
            presenterView.onFail(response.code());
        }
    }

    public void fail(Throwable t, PresenterView presenterView) {
        L.e("BasePresenter", "fail" + t);
        presenterView.onFail(t);
    }


    public void nextPage(String url) {
        Call<ApiResult<ArrayList<BaseModel>>> resultCall = this.baseHttpHandler.getProxy(ApiService.class).loadUrl(url);
        resultCall.enqueue(new Callback<ApiResult<ArrayList<BaseModel>>>() {
            @Override
            public void onResponse(Call<ApiResult<ArrayList<BaseModel>>> call, Response<ApiResult<ArrayList<BaseModel>>> response) {
                checkSuccess(response, presenterView);
            }

            @Override
            public void onFailure(Call<ApiResult<ArrayList<BaseModel>>> call, Throwable t) {
                fail(t, presenterView);
            }
        });
    }

}
