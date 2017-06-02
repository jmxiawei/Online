package cn.samir.online.mvp.intefaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import cn.samir.domains.model.Tab;
import cn.samir.online.adapter.TabPagerAdapter;
import cn.samir.online.mvp.PresenterView;

/**
 * Created by xiaw on 2017/5/11 0011.
 */

public interface ITabViewPagerView extends PresenterView {

    String PARAM_TABS = "PARAM_TABS";
    String PARAM_TAB_TYPE = "PARAM_TAB_TYPE";
    String PARAM_TAB_URL = "PARAM_TAB_URL";
    String PARAM_DEFAULT_INDEX = "PARAM_DEFAULT_INDEX";
    String PARAM_TITLE = "PARAM_TITLE";
    /**
     * 接口请求tab数据
     */
    int TYPE_TABURL = 0;
    /**
     * 自带tab数据
     */
    int TYPE_DATA = 1;

    View getTabLayout();

    TabPagerAdapter getTabPagerAdapter();

    Fragment getFragmentByTab(Tab tab);

    void loadTab(String tabUrl);

    ViewPager getViewPager();


    int getDefaultIndex();

    int getType();
}
