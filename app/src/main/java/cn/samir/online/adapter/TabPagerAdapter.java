package cn.samir.online.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.samir.domains.model.Tab;

/**
 * Created by xiaw on 2017/4/7 0007.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Tab> tabs;

    private List<Fragment> fragments;

    public TabPagerAdapter(FragmentManager fm, ArrayList<Tab> tabs, List<Fragment> fragments) {
        super(fm);
        this.tabs = tabs;
        this.fragments = fragments;
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getName();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
