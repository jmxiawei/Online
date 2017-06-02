package cn.samir.online.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.Tab;
import cn.samir.domains.model.TabInfo;
import cn.samir.online.R;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.cases.TabViewPagerViewImp;
import cn.samir.online.mvp.intefaces.ITabViewPagerView;
import cn.samir.online.mvp.presenters.TabViewPagerPresenter;
import cn.samir.online.ui.base.BaseFragment;
import cn.samir.online.views.CustomTabLayout;
import cn.samir.online.views.ProxyTabLayout;

/**
 * Created by xiaw on 2017/5/9 0009.
 */

public class TabViewPagerFragment extends BaseFragment {


    public static final String TAG = "TabViewPagerFragment";
    @BindView(R.id.tab)
    CustomTabLayout tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private TabViewPagerPresenter tabViewPagerPresenter;

    private TabViewPagerViewImp tabViewPagerViewImp;

    public static TabViewPagerFragment newInstance(String tabUrl, int defaultIndex) {
        Bundle bundle = new Bundle();
        bundle.putString(ITabViewPagerView.PARAM_TAB_URL, tabUrl);
        bundle.putInt(ITabViewPagerView.PARAM_TAB_TYPE, 0);
        bundle.putInt(ITabViewPagerView.PARAM_DEFAULT_INDEX, defaultIndex);
        TabViewPagerFragment t = new TabViewPagerFragment();
        t.setArguments(bundle);
        return t;
    }

    public static TabViewPagerFragment newInstanceHasTabs(ArrayList<Tab> tabs, int defaultIndex) {
        Bundle bundle = new Bundle();
        bundle.putInt(ITabViewPagerView.PARAM_TAB_TYPE, 1);
        TabInfo tabInfo = new TabInfo();
        tabInfo.setTabList(tabs);
        tabInfo.setDefaultIdx(defaultIndex);
        bundle.putParcelable(ITabViewPagerView.PARAM_TABS, tabInfo);
        bundle.putInt(ITabViewPagerView.PARAM_DEFAULT_INDEX, defaultIndex);
        TabViewPagerFragment t = new TabViewPagerFragment();
        t.setArguments(bundle);
        return t;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_tab_viewpager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
       // ProxyTabLayout<CustomTabLayout> tabs = new ProxyTabLayout<CustomTabLayout>(tab);
        //tab = tabs.getProxy(tabs.getClass());
        tabViewPagerViewImp = new TabViewPagerViewImp(getChildFragmentManager(), tab, viewPager, getArguments());
        tabViewPagerPresenter = new TabViewPagerPresenter(new BaseHttpHandler(), tabViewPagerViewImp, getArguments());
        return rootView;
    }

}
