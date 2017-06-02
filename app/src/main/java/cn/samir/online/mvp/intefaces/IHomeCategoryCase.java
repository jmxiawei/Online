package cn.samir.online.mvp.intefaces;

import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.TabInfoResult;
import retrofit2.Call;

/** 获取数据
 * Created by xiaw on 2017/4/6 0006.
 */

public interface IHomeCategoryCase {


    public Call<TabInfoResult> loadCate();


    public  Call<ApiResult<ArrayList<BaseModel>>> loadCateList(String url);
}
