package cn.samir.online.mvp.presenters;

import com.google.gson.Gson;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.IssueList;
import cn.samir.domains.model.IssueNavigationCard;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.http.converts.BasicModelTypeTokenProviderImp;
import cn.samir.online.http.converts.CustomConvert;
import cn.samir.online.mvp.BasePresenter;
import cn.samir.online.mvp.PresenterView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaw on 2017/5/2 0002.
 */

public class FeedIssuePresenter extends BasePresenter {


    public FeedIssuePresenter(BaseHttpHandler baseHttpHandler, PresenterView presenterView) {
        super(baseHttpHandler);
        this.presenterView = presenterView;
    }


    public void loadIssueNavigationCard(long date) {

        Call<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>> resultCall = baseHttpHandler.getProxy(ApiService.class).loadIssueNavigationCard(date);
        resultCall.enqueue(new Callback<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>>() {
            @Override
            public void onResponse(Call<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>> call, Response<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>> response) {
                checkSuccess(response, presenterView);
            }

            @Override
            public void onFailure(Call<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>> call, Throwable t) {
                fail(t, presenterView);
            }
        });
    }

    public void loadIssueNavigationCard(String url) {

        Call<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>> resultCall = baseHttpHandler.getProxy(ApiService.class).loadIssueNavigationCard(url);
        resultCall.enqueue(new Callback<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>>() {
            @Override
            public void onResponse(Call<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>> call, Response<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>> response) {
                checkSuccess(response, presenterView);
            }

            @Override
            public void onFailure(Call<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>> call, Throwable t) {
                fail(t, presenterView);
            }
        });
    }


    public void loadFeedHistory(int number, long date) {

        Call<ApiResult<ArrayList<IssueList>>> resultCall = baseHttpHandler.getProxy(ApiService.class).loadFeedHistory(number, date);

        resultCall.enqueue(new Callback<ApiResult<ArrayList<IssueList>>>() {
            @Override
            public void onResponse(Call<ApiResult<ArrayList<IssueList>>> call, Response<ApiResult<ArrayList<IssueList>>> response) {


                if (response.isSuccessful()) {
                    ApiResult<ArrayList<IssueList>> resResult = response.body();
                    if (resResult.issueList != null) {
                        int size = resResult.issueList.size();
                        ArrayList<BaseModel> results = new ArrayList<BaseModel>();
                        for (int i = 0; i < size; i++) {
                            results.addAll(resResult.issueList.get(i).itemList);
                        }
                        resResult.setCaches(results);
                        CustomConvert.convertList(new Gson(), BasicModelTypeTokenProviderImp.newInstance(), results);
                        presenterView.onSuccess(resResult);
                    }
                } else {
                    fail(null, presenterView);
                }

            }

            @Override
            public void onFailure(Call<ApiResult<ArrayList<IssueList>>> call, Throwable t) {
                fail(t, presenterView);
            }
        });

    }


}
