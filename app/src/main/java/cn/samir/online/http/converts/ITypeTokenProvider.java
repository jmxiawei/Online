package cn.samir.online.http.converts;

import com.google.gson.Gson;

/**根据type字段解析出实体的类型
 * Created by xiaw on 2017/3/28 0028.
 */
public interface ITypeTokenProvider<T> {
    public T get(Gson mGson, String type, String videoJson);
}
