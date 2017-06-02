package cn.samir.online.mvp.cases;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.elvin.customlib.L;
import cn.samir.domains.model.Tab;
import cn.samir.domains.model.TabInfo;
import cn.samir.domains.model.TabInfoResult;
import cn.samir.online.adapter.ItemSettings;
import cn.samir.online.adapter.TabPagerAdapter;
import cn.samir.online.mvp.intefaces.ITabViewPagerView;
import cn.samir.online.ui.fragment.RecyclerFragment;
import cn.samir.online.views.CustomTabLayout;

/**
 * Created by xiaw on 2017/5/11 0011.
 */

public class TabViewPagerViewImp implements ITabViewPagerView {


    private TabLayout tabLayout;
    private CustomTabLayout customTabLayout;
    private int mTabLayoutType = 1;//使用CustomTabLayout 0使用TabLayout
    private ViewPager viewPager;

    private FragmentManager fragmentManager;

    private TabPagerAdapter tabPagerAdapter;
    private TabInfoResult tabInfoResult;
    private List<Fragment> fragments;

    Bundle argments;

    private int defaultIndex;
    private int type;

    private ItemSettings itemSettings;

    public ItemSettings getItemSettings() {
        return itemSettings;
    }

    public void setItemSettings(ItemSettings itemSettings) {
        this.itemSettings = itemSettings;
    }

    public TabViewPagerViewImp(FragmentManager fragmentManager, TabLayout tabLayout, ViewPager viewPager, Bundle argments) {
        this.tabLayout = tabLayout;
        mTabLayoutType = 0;
        this.viewPager = viewPager;
        this.argments = argments;
        this.fragmentManager = fragmentManager;

        if (this.argments != null) {
            defaultIndex = this.argments.getInt(PARAM_DEFAULT_INDEX);
            type = this.argments.getInt(PARAM_TAB_TYPE);
        }


    }


    public TabViewPagerViewImp(FragmentManager fragmentManager, CustomTabLayout tabLayout, ViewPager viewPager, Bundle argments) {
        this.customTabLayout = tabLayout;
        mTabLayoutType = 1;
        this.viewPager = viewPager;
        this.argments = argments;
        this.fragmentManager = fragmentManager;

        if (this.argments != null) {
            defaultIndex = this.argments.getInt(PARAM_DEFAULT_INDEX);
            type = this.argments.getInt(PARAM_TAB_TYPE);
        }


    }

    @Override
    public boolean onSuccess(Object... params) {
        if (params[0] instanceof TabInfoResult) {
            tabInfoResult = (TabInfoResult) params[0];
            L.e("onSuccess=" + tabInfoResult.toString());
            fragments = new ArrayList<>();
            int size = tabInfoResult.getTabInfo().getTabList().size();
            for (int i = 0; i < size; i++) {
                Tab tab = tabInfoResult.getTabInfo().getTabList().get(i);
                RecyclerFragment recyclerFragment = RecyclerFragment.newInstance(tab.getApiUrl(), true);
                recyclerFragment.setItemSettings(this.itemSettings);
                fragments.add(recyclerFragment);
                //fragments.add(MainFragment.getInstance(tab.getName()));
            }
            tabPagerAdapter = new TabPagerAdapter(this.fragmentManager, tabInfoResult.getTabInfo().getTabList(), fragments);
            viewPager.setOffscreenPageLimit(fragments.size());
            viewPager.setAdapter(tabPagerAdapter);
            if (mTabLayoutType == 0) {
                tabLayout.setupWithViewPager(viewPager);
            } else {
                customTabLayout.setupWithViewPager(viewPager);
            }
            viewPager.setCurrentItem(defaultIndex);

            //DensityUtil.setIndicator(getActivity(), tab, length);
        }

        return false;
    }

    @Override
    public boolean onFail(Object... params) {

        return false;
    }

    @Override
    public Context context() {

        return null;
    }

    @Override
    public View getTabLayout() {
        return tabLayout;
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        return tabPagerAdapter;
    }

    @Override
    public Fragment getFragmentByTab(Tab tab) {
        return RecyclerFragment.newInstance(tab.apiUrl);
    }

    @Override
    public void loadTab(String tabUrl) {

    }

    @Override
    public ViewPager getViewPager() {
        return viewPager;
    }


    @Override
    public int getDefaultIndex() {
        return defaultIndex;
    }

    @Override
    public int getType() {
        return type;
    }


    public static Bundle getArguments(ArrayList<Tab> tabs, int defaultIndex) {
        Bundle bundle = new Bundle();
        bundle.putInt(ITabViewPagerView.PARAM_TAB_TYPE, 1);
        TabInfo tabInfo = new TabInfo();
        tabInfo.setTabList(tabs);
        tabInfo.setDefaultIdx(defaultIndex);
        bundle.putParcelable(ITabViewPagerView.PARAM_TABS, tabInfo);
        bundle.putInt(ITabViewPagerView.PARAM_DEFAULT_INDEX, defaultIndex);
        return bundle;
    }


    public static Bundle getArguments(String tabUrl, int defaultIndex) {
        Bundle bundle = new Bundle();
        bundle.putString(ITabViewPagerView.PARAM_TAB_URL, tabUrl);
        bundle.putInt(ITabViewPagerView.PARAM_TAB_TYPE, 0);
        bundle.putInt(ITabViewPagerView.PARAM_DEFAULT_INDEX, defaultIndex);
        return bundle;
    }


}
