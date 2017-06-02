package cn.samir.online.http;

import java.lang.reflect.Proxy;

import cn.samir.online.OnlineApplication;
import cn.samir.online.mvp.PresenterView;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiawei on 2017/3/14.
 */

public class BaseHttpHandler  {

    private Retrofit retrofit;
    private Converter.Factory converter;

    public BaseHttpHandler() {
        this(null);
    }

    /**
     *
     * @param converter
     */
    public BaseHttpHandler(Converter.Factory converter) {
        this.converter = converter;
        if (this.converter == null) {
            this.converter = GsonConverterFactory.create();
        }
        retrofit = new Retrofit.Builder().baseUrl(ApiService.baseUrl).client(HttpClientManager.getInstance(OnlineApplication.getInstance()).getOkHttpClient()).addConverterFactory(this.converter).build();
    }


    public void setConverter(Converter.Factory converter) {
        this.converter = converter;
        if (this.converter == null) {
            this.converter = GsonConverterFactory.create();
        }
        retrofit = new Retrofit.Builder().baseUrl(ApiService.baseUrl).client(HttpClientManager.getInstance(OnlineApplication.getInstance()).getOkHttpClient()).addConverterFactory(this.converter).build();
    }

    public <T> T getProxy(Class<T> clazz) {
        T t = retrofit.create(clazz);
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new ProxyHandler(t, this));
    }


}
