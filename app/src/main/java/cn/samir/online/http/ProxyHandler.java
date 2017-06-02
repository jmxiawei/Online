package cn.samir.online.http;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import cn.samir.online.util.LogUtil;

/**
 * Created by xiaw on 2017/3/10 0010.
 */

public class ProxyHandler implements InvocationHandler {


    private static String TAG = ProxyHandler.class.getSimpleName();
    private BaseHttpHandler baseHttpHandler;
    private Object proxyObject;

    public ProxyHandler(Object proxyObject, BaseHttpHandler baseHttpHandler) {
        this.proxyObject = proxyObject;
        this.baseHttpHandler = baseHttpHandler;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        LogUtil.e("ProxyHandler  methodName=" + method.getName());
        return method.invoke(proxyObject, args);
    }

    private void needRefreshToken() {

    }
}
