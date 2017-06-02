package cn.samir.online.ui;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.elvin.customlib.FragmentPagerAdapter;
import cn.samir.online.R;
import cn.samir.online.ui.base.BaseFragment;
import cn.samir.online.ui.base.BaseTitleActivity;
import cn.samir.online.ui.home.HomeCategoryFragment;
import cn.samir.online.ui.home.HomeFeedFragment;
import cn.samir.online.ui.home.HomeFollowFragment;
import cn.samir.online.ui.home.HomeProfileFragment;
import cn.samir.online.util.LogUtil;
import cn.samir.online.views.BottomNavigationView;
import cn.samir.online.views.CustomViewPager;

public class MainActivity extends BaseTitleActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.navigation)
    BottomNavigationView navigationView;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.view_pager)
    CustomViewPager contentViewPager;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    ArrayList<BottomNavigationView.MenuItem> items = new ArrayList<BottomNavigationView.MenuItem>();
    //private HashMap<String, BaseFragment> fragments = new HashMap<>();
    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    private FragmentPagerAdapter fragmentPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("onCreate");
        setContentView(R.layout.activity_bottom_navigation_main);
        ButterKnife.bind(this);

//        container.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        initBottomNavigation();

//        fragments.put(HomeFeedFragment.TAG, newFragment(HomeFeedFragment.TAG));
//        fragments.put(HomeCategoryFragment.TAG, newFragment(HomeCategoryFragment.TAG));
//        fragments.put(HomeFollowFragment.TAG, newFragment(HomeFollowFragment.TAG));
//        fragments.put(HomeProfileFragment.TAG, newFragment(HomeProfileFragment.TAG));

        fragments.add(newFragment(HomeFeedFragment.TAG));
        fragments.add(newFragment(HomeCategoryFragment.TAG));
        fragments.add(newFragment(HomeFollowFragment.TAG));
        fragments.add(newFragment(HomeProfileFragment.TAG));

        contentViewPager.setOffscreenPageLimit(3);
        contentViewPager.addOnPageChangeListener(this);
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragments);
        contentViewPager.setAdapter(fragmentPagerAdapter);
        initState(savedInstanceState);

//        Account account = new Account(getString(R.string.app_name), Constant.ACCOUNT_TYPE);
//        AccountManager accountManager = (AccountManager) getApplicationContext().getSystemService(ACCOUNT_SERVICE);
//        if (accountManager.addAccountExplicitly(account, null, null)) {
//
//        }
//        ContentResolver.addStatusChangeListener(ContentResolver.SYNC_OBSERVER_TYPE_ACTIVE
//                | ContentResolver.SYNC_OBSERVER_TYPE_PENDING | ContentResolver.SYNC_OBSERVER_TYPE_SETTINGS, new SyncStatusObserver() {
//            @Override
//            public void onStatusChanged(int which) {
//                LogUtil.e("onStatusChanged=" + which);
//            }
//        });
//        ContentResolver.setIsSyncable(account, Constant.AUTHORITY, 1);
//        ContentResolver.setMasterSyncAutomatically(true);
//        ContentResolver.addPeriodicSync(account, Constant.AUTHORITY, Bundle.EMPTY, 60);

    }

    private void initState(Bundle savedInstanceState) {
        BottomNavigationView.MenuItem index = null;
        if (savedInstanceState != null) {
            index = savedInstanceState.getParcelable("currentIndex");
        }
        if (index == null) {
            index = items.get(0);
        }
        onNavigationItemClickListener.onItemClick(index, null);
    }


    private void initBottomNavigation() {
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        items.clear();
        items.add(new BottomNavigationView.MenuItem(R.id.menu_feed, R.mipmap.ic_tab_strip_icon_feed, R.mipmap.ic_tab_strip_icon_feed_selected, getString(R.string.title_feed), true));
        items.add(new BottomNavigationView.MenuItem(R.id.menu_category, R.mipmap.ic_tab_strip_icon_category, R.mipmap.ic_tab_strip_icon_category_selected, getString(R.string.title_category)));
        items.add(new BottomNavigationView.MenuItem(R.id.menu_follow, R.mipmap.ic_tab_strip_icon_follow, R.mipmap.ic_tab_strip_icon_follow_selected, getString(R.string.title_follow)));
        items.add(new BottomNavigationView.MenuItem(R.id.menu_profile, R.mipmap.ic_tab_strip_icon_profile, R.mipmap.ic_tab_strip_icon_profile_selected, getString(R.string.title_profile)));
        navigationView.setMenuItems(items);
        navigationView.setonNavigationItemClickListener(onNavigationItemClickListener);
    }


    BottomNavigationView.OnNavigationItemClickListener onNavigationItemClickListener = new BottomNavigationView.OnNavigationItemClickListener() {
        @Override
        public void onItemClick(BottomNavigationView.MenuItem item, View view1) {

            int itemId = item.getId();

            switch (itemId) {

                case R.id.menu_feed:
                    updateHeader(0);

                    //switchFragment(HomeFeedFragment.TAG);
                    contentViewPager.setCurrentItem(0);
                    break;
                case R.id.menu_category:
                    contentViewPager.setCurrentItem(1);
                    updateHeader(1);
                    //switchFragment(HomeCategoryFragment.TAG);
                    break;
                case R.id.menu_follow:

                    contentViewPager.setCurrentItem(2);
                    updateHeader(2);
                    //switchFragment(HomeFollowFragment.TAG);
                    break;

                case R.id.menu_profile:

                    contentViewPager.setCurrentItem(3);
                    updateHeader(3);
                    //switchFragment(HomeProfileFragment.TAG);
                    break;
            }
        }
    };


    /**
     * @param tag
     * @param params
     * @return
     */
    public BaseFragment newFragment(String tag, Object... params) {

        BaseFragment rs = null;
        if (tag.equals(HomeFeedFragment.TAG)) {
            rs = getFragmentByTag(tag) == null ? HomeFeedFragment.newInstance() : (BaseFragment) getFragmentByTag(tag);
        } else if (tag.equals(HomeCategoryFragment.TAG)) {
            rs = getFragmentByTag(tag) == null ? HomeCategoryFragment.newInstance() : (BaseFragment) getFragmentByTag(tag);
        } else if (tag.equals(HomeFollowFragment.TAG)) {
            rs = getFragmentByTag(tag) == null ? HomeFollowFragment.newInstance() : (BaseFragment) getFragmentByTag(tag);
        } else if (tag.equals(HomeProfileFragment.TAG)) {
            rs = getFragmentByTag(tag) == null ? HomeProfileFragment.newInstance() : (BaseFragment) getFragmentByTag(tag);
        }
        if (rs != null) {
            rs.setToolbar(tooBar);
        }

        return rs;
    }


    public Fragment getFragmentByTag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        navigationView.setSelect(position);
        updateHeader(position);
    }


    @Override
    public void onSearchClick() {

        if (mMode == TitleMode.LEFT_CENTER_SEARCH || mMode == TitleMode.ONLY_SEARCH) {
            QueryByKeyWordActivity.toThis(this);
        }
    }

    private void updateHeader(int position) {
        switch (position) {
            case 0:
                initHeaderTitle(TitleMode.ONLY_SEARCH);
                break;
            case 1:
                tooBar.setAlpha(1);
                initHeaderTitle(TitleMode.LEFT_CENTER_SEARCH, getString(R.string.str_discover), getString(R.string.str_all_cate), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivitySchema(getString(R.string.schema_categories_all));
                    }
                });
                break;
            case 2:
                tooBar.setAlpha(1);
                initHeaderTitle(TitleMode.ONLY_SEARCH);
                initHeaderTitle(TitleMode.LEFT_CENTER_SEARCH, getString(R.string.str_subscription), getString(R.string.str_all_author), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivitySchema(getString(R.string.schema_pgcs_all));
                    }
                });
                break;
            case 3:
                tooBar.setAlpha(1);
                initHeaderTitle(TitleMode.MORE_SETTINGS);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
