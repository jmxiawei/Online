package cn.samir.online.mvp.presenters;

import cn.samir.domains.model.CategoryDetail;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.BasePresenter;
import cn.samir.online.mvp.PresenterView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaw on 2017/5/19 0019.
 */

public class PCGSDetailPresenter extends BasePresenter {


    public PCGSDetailPresenter(PresenterView presenterView, BaseHttpHandler baseHttpHandler) {
        super(presenterView, baseHttpHandler);
    }


    public void loadPcgsDetailTab(int id) {

        Call<CategoryDetail> call = baseHttpHandler.getProxy(ApiService.class).loadPcgsDetailTab(id);

        call.enqueue(new Callback<CategoryDetail>() {
            @Override
            public void onResponse(Call<CategoryDetail> call, Response<CategoryDetail> response) {
                checkSuccess(response, presenterView);
            }

            @Override
            public void onFailure(Call<CategoryDetail> call, Throwable t) {
                presenterView.onFail(t);
            }
        });


    }
}
