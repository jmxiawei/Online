package cn.samir.online.mvp.intefaces;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import retrofit2.Call;

/**
 * Created by Administrator on 2017/4/15.
 */

public interface ISearchByKeyWordCase {


    Call<ArrayList<String>> loadHotKeyWord();

    Call<ApiResult<ArrayList<BaseModel>>>  loadByKeyWord(String query);

}
