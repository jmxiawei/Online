package cn.samir.online.mvp.callback;

import cn.elvin.customlib.L;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaw on 2017/4/13 0013.
 */

public abstract class BaseCallBack<T> implements Callback<T> {

    public abstract void response(Call<T> call, Response<T> response);

    public abstract void fail(Call<T> call, Throwable t);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        L.e("BaseCallBack", "onResponse url=" + call.request().url() + "code=" + response.code());

        response(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        L.e("BaseCallBack", "onFailure url=" + call.request().url() + t.getMessage());

        fail(call, t);
    }
}
