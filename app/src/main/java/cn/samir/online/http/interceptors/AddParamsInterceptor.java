package cn.samir.online.http.interceptors;

import cn.samir.online.util.LogUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.samir.domains.model.AppInfo;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * udid=7c7e8036375b40abb81c7f9a5f73293ccb6969b7&
 * vc=175
 * &vn=3.4.3
 * &deviceModel=Android%20SDK%20built%20for%20x86
 * &first_channel=eyepetizer_baidu_market
 * &last_channel=eyepetizer_baidu_market
 * &system_version_code=25
 * Created by xiaw on 2017/4/7 0007.
 */

public class AddParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();

        HttpUrl url = oldRequest.url();

        HttpUrl.Builder builder = url.newBuilder();

        HashMap<String, String> params = AppInfo.instance().getParams();
        for (Map.Entry<String, String> p :
                params.entrySet()) {
            builder.addQueryParameter(p.getKey(), p.getValue());
        }
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(),oldRequest.body())
                .url(builder.build())
                .headers(oldRequest.headers())
                .build();
        LogUtil.e("AddParamsInterceptor="+newRequest.url());
        return chain.proceed(newRequest);
    }
}
