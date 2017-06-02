package cn.samir.online.mvp;

import cn.samir.online.http.BaseHttpHandler;

/**
 * Created by xiaw on 2017/4/14 0014.
 */

public  abstract class BaseHasNextPagePresenter extends BasePresenter {
    public BaseHasNextPagePresenter(BaseHttpHandler baseHttpHandler) {
        super(baseHttpHandler);
    }


    public abstract void nextPage(String url);
}
