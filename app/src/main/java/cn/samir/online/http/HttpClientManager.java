package cn.samir.online.http;

import android.content.Context;


import java.io.File;
import java.util.concurrent.TimeUnit;

import cn.samir.online.OnlineApplication;
import cn.samir.online.http.interceptors.AddParamsInterceptor;
import cn.samir.online.http.interceptors.GzipRequestInterceptor;
import cn.samir.online.http.interceptors.OnLineCacheInterceptor;
import cn.samir.online.util.FileUtil;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by xiaw on 2017/3/10 0010.
 */

public class HttpClientManager {


    private OkHttpClient okHttpClient;

    private HttpClientManager(Context context) {
        okHttpClient = newClient(context);
    }

    private OkHttpClient newClient(Context context) {
        //设置缓存路径
        File httpCacheDirectory = new File(FileUtil.getCacheDir(OnlineApplication.getInstance(), "responses"));
        //设置缓存 10M
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        return new OkHttpClient.Builder()
                .cache(new CacheProvider(context).provide())//缓存空间提供器
                //.addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new OnLineCacheInterceptor())
                .addInterceptor(new GzipRequestInterceptor())
                .addNetworkInterceptor(new AddParamsInterceptor())
                .retryOnConnectionFailure(true)
                .cache(cache)
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
    }


    private static HttpClientManager instance;

    public static HttpClientManager getInstance(Context context) {

        if (instance == null) {
            synchronized (HttpClientManager.class) {
                if (instance == null) {
                    instance = new HttpClientManager(context);
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

}
