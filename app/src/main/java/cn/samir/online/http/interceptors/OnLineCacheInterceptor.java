package cn.samir.online.http.interceptors;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.elvin.customlib.L;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存拦截
 * Created by xiaw on 2017/3/10 0010.
 */

public class OnLineCacheInterceptor implements Interceptor {
    public static final String TAG = OnLineCacheInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {


        Request originalRequest = chain.request();


        CacheControl cacheControl = new CacheControl.Builder()
                //不使用缓存，但是会保存缓存数据
                //.noCache()
                //不使用缓存，同时也不保存缓存数据
                // .noStore()
                //只使用缓存，（如果我们要加载的数据本身就是本地数据时，可以使用这个，不过目前尚未发现使用场景）
                //.onlyIfCached()
                //.noTransform()
                //手机可以接收响应时间小于当前时间加上10s的响应
                //.minFresh(10, TimeUnit.SECONDS)
                //手机可以接收有效期不大于10s的响应
                .maxAge(43200, TimeUnit.SECONDS)
                //手机可以接收超出5s的响应
                //.maxStale(43200, TimeUnit.SECONDS)
                .build();

        originalRequest = originalRequest.newBuilder().cacheControl(cacheControl).build();
        //L.e("has net work");

        long t1 = System.currentTimeMillis();
        Response originalResponse = chain.proceed(originalRequest);

//        System.out.println("Response 2 response:          " + originalResponse);
//        System.out.println("Response 2 cache response:    " + originalResponse.cacheResponse());
//        System.out.println("Response 2 network response:  " + originalResponse.networkResponse());

        long t2 = System.currentTimeMillis();
        double time = (t2 - t1);
        Log.i(TAG, "time:" + time + " ms");
        return originalResponse;
//        return originalResponse.newBuilder()
//                .header("Cache-Control", cacheControl.toString())
//                .removeHeader("Pragma")
//                .build();

    }
}
