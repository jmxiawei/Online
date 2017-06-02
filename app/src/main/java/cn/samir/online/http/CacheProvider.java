package cn.samir.online.http;

import android.content.Context;

import okhttp3.Cache;

/**
 * Created by xiaw on 2017/3/10 0010.
 */
public class CacheProvider {
    public static final int DEFAULT_CACHE_MAX_SIZE = 1024 * 1024;
    Context context;
    int maxSize = DEFAULT_CACHE_MAX_SIZE;

    public CacheProvider(Context context) {
        this.context = context;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public Cache provide() {
        return new Cache(context.getCacheDir(), maxSize);
    }
}
