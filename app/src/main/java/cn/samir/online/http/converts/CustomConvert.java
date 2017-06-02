package cn.samir.online.http.converts;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.IssueList;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by xiawei on 2017/3/10.
 */

public class CustomConvert implements Converter<ResponseBody, ApiResult<ArrayList<BaseModel>>> {


    private Gson mGson;


    private BasicModelTypeTokenProviderImp imp = new BasicModelTypeTokenProviderImp();


    public CustomConvert(Gson mGson) {
        this.mGson = mGson;
    }


    @Override
    public ApiResult<ArrayList<BaseModel>> convert(ResponseBody responseBody) throws IOException {

        ApiResult<ArrayList<BaseModel>> apiResult = mGson.fromJson(responseBody.charStream(), new TypeToken<ApiResult<ArrayList<Object>>>() {
        }.getType());

        if (apiResult.itemList != null) {
            convertList(mGson, imp, apiResult.itemList);
        } else if (apiResult.issueList != null) {
            convertList(mGson, imp, apiResult.issueList);
        }
        return apiResult;
    }

    /**
     * 项目解析数据最复杂的方法，对照返回参数才能理解
     * @param mGson
     * @param imp
     * @param l
     */
    public static void convertList(Gson mGson, BasicModelTypeTokenProviderImp imp, ArrayList l) {

        if (l == null) {
            return;
        }
        int size = l.size();
        for (int i = 0; i < size; i++) {
            Object o = l.get(i);
            if (o instanceof LinkedTreeMap) {
                convertMap(mGson, imp, l, i, o);
            } else if (o instanceof IssueList) {
                convertList(mGson, imp, ((IssueList) o).getItemList());
            } else if (o instanceof ArrayList) {
                convertList(mGson, imp, (ArrayList) o);
            }
        }
    }

    private static void convertMap(Gson mGson, BasicModelTypeTokenProviderImp imp, List l, int i, Object o) {
        LinkedTreeMap<String, String> maps = (LinkedTreeMap<String, String>) o;
        String type = maps.get("type");
        String videoJson = mGson.toJson(maps);
        BaseModel v = imp.get(mGson, type, videoJson);
        if (v == null) {
           // L.e(o.toString());
        } else {
            //L.e("ok" + o.toString());
            l.set(i, v);
        }
    }
}

