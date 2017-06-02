package cn.samir.online.http.converts;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.List;

import cn.elvin.customlib.L;
import cn.samir.domains.model.BaseModel;


/**
 * Created by xiaw on 2017/3/28 0028.
 */
public class BasicModelTypeTokenProviderImp implements ITypeTokenProvider<BaseModel> {


    public static BasicModelTypeTokenProviderImp newInstance() {
        return new BasicModelTypeTokenProviderImp();
    }

    private List<BaseModel> hasItemList(BaseModel bm) {
        Class clazz = bm.getClass();
        try {
            Field fields = clazz.getDeclaredField("data");
            fields.setAccessible(true);
            Object data = fields.get(bm);
            Field dataField = null;
            try {
                dataField = data.getClass().getDeclaredField("itemList");
            } catch (NoSuchFieldException e) {
                dataField = data.getClass().getDeclaredField("issueList");
            }
            //dataField = data.getClass().getDeclaredField("itemList");
            dataField.setAccessible(true);
            List<BaseModel> rs = (List<BaseModel>) dataField.get(data);
            // L.e("hasItemList=" + rs);
            return rs;
        } catch (Exception e) {
            // no log
            //e.printStackTrace();
        }
        return null;

    }


    @Override
    public BaseModel get(Gson mGson, String type, String json) {

        try {
            TypeDataItem typeDataItem = TypeProvider.getInstance().getByKey(type);
            if (typeDataItem != null) {
                BaseModel bm = mGson.fromJson(json, typeDataItem.getType());
                List<BaseModel> list = hasItemList(bm);
                if (list != null) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        BaseModel b = list.get(i);
                        String stype = b.getType();
                        String sjson = mGson.toJson(b);
                        //L.e("stype=" + stype + ",sjson" + sjson);
                        BaseModel sb = get(mGson, stype, sjson);
                        if (sb == null) {
                            L.e("type=" + type + " 没有解析出数据");
                        }
                        list.set(i, sb);
                    }
                }

                return bm;
            } else {
                L.e("type=" + type + " 没有匹配到typeDataItem");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
