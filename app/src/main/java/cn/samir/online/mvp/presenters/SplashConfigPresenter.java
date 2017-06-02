package cn.samir.online.mvp.presenters;

import cn.samir.domains.model.configs.Configs;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.BasePresenter;
import cn.samir.online.mvp.PresenterView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaw on 2017/5/3 0003.
 */

public class SplashConfigPresenter extends BasePresenter {

    public SplashConfigPresenter(BaseHttpHandler baseHttpHandler, PresenterView presenterView) {
        super(baseHttpHandler);
        this.presenterView = presenterView;
    }


    public void loadConfig() {


        Call<Configs> resultCall = baseHttpHandler.getProxy(ApiService.class).loadConfigs();
        resultCall.enqueue(new Callback<Configs>() {
            @Override
            public void onResponse(Call<Configs> call, Response<Configs> response) {
                checkSuccess(response, presenterView);
            }

            @Override
            public void onFailure(Call<Configs> call, Throwable t) {
                fail(t, presenterView);

            }
        });


    }
}



