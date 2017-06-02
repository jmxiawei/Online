package cn.samir.online.mvp.presenters;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.BasePresenter;
import cn.samir.online.mvp.cases.SearchByKeyWordCase;
import cn.samir.online.mvp.intefaces.ISearchByKeyWordCase;
import cn.samir.online.mvp.intefaces.ISearchByKeyWordView;
import cn.samir.online.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/4/15.
 */

public class SearchByKeyWordPresenter extends BasePresenter {


    private ISearchByKeyWordCase iSearchByKeyWordCase;

    public SearchByKeyWordPresenter(BaseHttpHandler baseHttpHandler, ISearchByKeyWordView iSearchByKeyWordView) {
        super(baseHttpHandler);
        iSearchByKeyWordCase = new SearchByKeyWordCase(baseHttpHandler);
        this.presenterView = iSearchByKeyWordView;
    }


    public void loadHotKeyWord() {
        Call<ArrayList<String>> hotCall = iSearchByKeyWordCase.loadHotKeyWord();
        hotCall.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                checkSuccess(response, presenterView);
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                presenterView.onFail(t);
            }
        });
    }


    public void loadByKeyWord(String query) {

        Call<ApiResult<ArrayList<BaseModel>>> resultCall = iSearchByKeyWordCase.loadByKeyWord(query);

        resultCall.enqueue(new Callback<ApiResult<ArrayList<BaseModel>>>() {
            @Override
            public void onResponse(Call<ApiResult<ArrayList<BaseModel>>> call, Response<ApiResult<ArrayList<BaseModel>>> response) {
                checkSuccess(response, presenterView);
            }

            @Override
            public void onFailure(Call<ApiResult<ArrayList<BaseModel>>> call, Throwable t) {
                presenterView.onSuccess(Constant.ERROR_CODE_NET);
            }
        });

    }
}
