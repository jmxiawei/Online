package cn.samir.online.mvp.cases;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.intefaces.IHomeFeedCase;
import retrofit2.Call;

/**
 * Created by xiaw on 2017/4/7 0007.
 */

public class HomeFeedCase implements IHomeFeedCase {

    private BaseHttpHandler baseHttpHandler;

    public HomeFeedCase(BaseHttpHandler baseHttpHandler) {
        this.baseHttpHandler = baseHttpHandler;
    }

    @Override
    public Call<ApiResult<ArrayList<BaseModel>>> loadHome() {
        return baseHttpHandler.getProxy(ApiService.class).loadHome();
    }

    @Override
    public Call<ApiResult<ArrayList<BaseModel>>> nextPage(String url) {
        return baseHttpHandler.getProxy(ApiService.class).loadUrl(url);
    }


}
