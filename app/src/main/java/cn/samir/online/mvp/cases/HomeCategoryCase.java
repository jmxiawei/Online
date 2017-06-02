package cn.samir.online.mvp.cases;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.TabInfoResult;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.intefaces.IHomeCategoryCase;
import retrofit2.Call;

/**
 * Created by xiaw on 2017/4/6 0006.
 */

public class HomeCategoryCase implements IHomeCategoryCase {

    private BaseHttpHandler baseHttpHandler;

    public HomeCategoryCase(BaseHttpHandler baseHttpHandler) {
        this.baseHttpHandler = baseHttpHandler;
    }

    @Override
    public Call<TabInfoResult> loadCate() {
        return baseHttpHandler.getProxy(ApiService.class).loadFindCate();
    }

    @Override
    public Call<ApiResult<ArrayList<BaseModel>>> loadCateList(String url) {
        return baseHttpHandler.getProxy(ApiService.class).loadFindCateList(url);
    }
}
