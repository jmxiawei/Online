package cn.samir.online.mvp.presenters;

import cn.samir.domains.model.MessageListModel;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.BasePresenter;
import cn.samir.online.mvp.PresenterView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaw on 2017/5/26 0026.
 */

public class MessageListPresenter extends BasePresenter {
    public MessageListPresenter(PresenterView presenterView, BaseHttpHandler baseHttpHandler) {
        super(presenterView, baseHttpHandler);
    }

    public MessageListPresenter(BaseHttpHandler baseHttpHandler) {
        super(baseHttpHandler);
    }


    public void loadMessages() {
        Call<MessageListModel> rs = baseHttpHandler.getProxy(ApiService.class).loadMessages();
        rs.enqueue(new Callback<MessageListModel>() {
            @Override
            public void onResponse(Call<MessageListModel> call, Response<MessageListModel> response) {
                checkSuccess(response, presenterView);
            }

            @Override
            public void onFailure(Call<MessageListModel> call, Throwable t) {
                fail(t, presenterView);
            }
        });
    }
}
