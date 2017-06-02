package cn.samir.online.mvp.cases;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.http.converts.CustomConvertFactory;
import cn.samir.online.mvp.intefaces.ISearchByKeyWordCase;
import retrofit2.Call;

/**
 * Created by Administrator on 2017/4/15.
 */

public class SearchByKeyWordCase implements ISearchByKeyWordCase {


    private BaseHttpHandler baseHttpHandler;

    public SearchByKeyWordCase(BaseHttpHandler baseHttpHandler) {
        this.baseHttpHandler = baseHttpHandler;
    }

    @Override
    public Call<ArrayList<String>> loadHotKeyWord() {
        baseHttpHandler.setConverter(null);
        return baseHttpHandler.getProxy(ApiService.class).loadHotKeyWord();
    }

    @Override
    public Call<ApiResult<ArrayList<BaseModel>>> loadByKeyWord(String query) {
        baseHttpHandler.setConverter(CustomConvertFactory.create());
        return baseHttpHandler.getProxy(ApiService.class).loadByKeyWord(query);
    }
}
