package cn.samir.online.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.elvin.customlib.L;
import cn.samir.domains.model.Tab;
import cn.samir.domains.model.TabInfoResult;
import cn.samir.online.R;
import cn.samir.online.adapter.TabPagerAdapter;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.intefaces.IHomeCategoryView;
import cn.samir.online.mvp.presenters.HomeCategoryPresenter;
import cn.samir.online.ui.base.BaseFragment;
import cn.samir.online.ui.fragment.RecyclerFragment;
import cn.samir.online.views.CustomTabLayout;

/**
 * Created by xiaw on 2017/4/7 0007.
 */

public class HomeCategoryFragment extends BaseFragment implements IHomeCategoryView {

    public static final String TAG = HomeCategoryFragment.class.getSimpleName();
    HomeCategoryPresenter homeCategoryPresenter;
    @BindView(R.id.tab)
    CustomTabLayout tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    TabPagerAdapter tabPagerAdapter;
    TabInfoResult tabInfoResult;
    List<Fragment> fragments;


    public static HomeCategoryFragment newInstance() {
        return new HomeCategoryFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        viewPager.setOffscreenPageLimit(2);
        homeCategoryPresenter = new HomeCategoryPresenter(new BaseHttpHandler(), this);
        homeCategoryPresenter.loadCate();
        tab.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home_category;
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
                fragments.add(RecyclerFragment.newInstance(tab.getApiUrl()));
            }
            tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager(), tabInfoResult.getTabInfo().getTabList(), fragments);
            viewPager.setAdapter(tabPagerAdapter);
            viewPager.setCurrentItem(tabInfoResult.getTabInfo().defaultIdx);
            //DensityUtil.setIndicator(getActivity(), tab, 80);
        }

        return false;
    }

    @Override
    public boolean onFail(Object... params) {
        //Toast.makeText(getActivity(), MsgUtils.getNotifyMessage(params[0]), Toast.LENGTH_SHORT).show();
        return false;
    }
    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
