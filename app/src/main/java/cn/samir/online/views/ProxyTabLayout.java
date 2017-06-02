package cn.samir.online.views;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xiaw on 2017/5/10 0010.
 */
public class ProxyTabLayout<T> {


    private T tabLayout;

    public ProxyTabLayout(T tabLayout) {
        this.tabLayout = tabLayout;
    }
    public T getProxy(Class clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new ProxyHandler(tabLayout));
    }

    /**
     *
     */
    public static class ProxyHandler<T> implements InvocationHandler {

        private T tabLayout;

        public ProxyHandler(T tabLayout) {
            this.tabLayout = tabLayout;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Log.e("ProxyHandler", "method=" + method.getName());
            return method.invoke(tabLayout, args);
        }
    }


}
