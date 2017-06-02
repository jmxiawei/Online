package com.dinuscxj.pullzoom;

/**
 * Created by xiaw on 2017/4/19 0019.
 */

public interface ILoadMoreView {


    int STATE_LOADING = 0;//正在加载
    int STATE_COMPLETE = 1;//完成
    int STATE_NOMORE = 2;//没有更多

    void setState(int state);

    void onLoading();

    void loadingComplete();

}
