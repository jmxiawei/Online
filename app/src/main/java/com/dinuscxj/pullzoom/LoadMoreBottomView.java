package com.dinuscxj.pullzoom;

import android.content.Context;
import android.util.AttributeSet;

import cn.samir.online.views.LogoInnerOuterAnimView;

/**
 * Created by xiaw on 2017/4/19 0019.
 */

public class LoadMoreBottomView extends LogoInnerOuterAnimView implements ILoadMoreView {

    public LoadMoreBottomView(Context context) {
        this(context, null);
    }

    public LoadMoreBottomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setMode(Mode.BLACK);
    }

    @Override
    public void setState(int state) {

    }

    @Override
    public void onLoading() {
        setVisibility(VISIBLE);
    }

    @Override
    public void loadingComplete() {
        setVisibility(GONE);
    }
}
