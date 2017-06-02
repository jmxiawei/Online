package cn.samir.online.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.Tab;
import cn.samir.online.R;
import cn.samir.online.mvp.intefaces.ITabViewPagerView;
import cn.samir.online.ui.base.BaseToolBarActivity;
import cn.samir.online.ui.fragment.TabViewPagerFragment;

public class TabViewPagerActivity extends BaseToolBarActivity {

    @BindView(R.id.container)
    FrameLayout container;


    public static Intent newIntent(Context context, String title, String tabUrl, int defaultIndex) {
        Intent intent = new Intent(context, TabViewPagerActivity.class);
        intent.putExtra(ITabViewPagerView.PARAM_TITLE, title);
        intent.putExtra(ITabViewPagerView.PARAM_TAB_TYPE, ITabViewPagerView.TYPE_TABURL);
        intent.putExtra(ITabViewPagerView.PARAM_DEFAULT_INDEX, defaultIndex);
        intent.putExtra(ITabViewPagerView.PARAM_TAB_URL, tabUrl);
        return intent;
    }

    public static Intent newWithData(Context context, String title, ArrayList<Tab> tabs, int defaultIndex) {
        Intent intent = new Intent(context, TabViewPagerActivity.class);
        intent.putExtra(ITabViewPagerView.PARAM_TITLE, title);
        intent.putExtra(ITabViewPagerView.PARAM_TAB_TYPE, ITabViewPagerView.TYPE_DATA);
        intent.putExtra(ITabViewPagerView.PARAM_TABS, tabs);
        intent.putExtra(ITabViewPagerView.PARAM_DEFAULT_INDEX, defaultIndex);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view_pager);
        ButterKnife.bind(this);

        String title = getIntent().getStringExtra(ITabViewPagerView.PARAM_TITLE);
        String tabUrl = getIntent().getStringExtra(ITabViewPagerView.PARAM_TAB_URL);
        int defaultIndex = getIntent().getIntExtra(ITabViewPagerView.PARAM_DEFAULT_INDEX, 0);
        setToolbar(title);
        int type = getIntent().getIntExtra(ITabViewPagerView.PARAM_TAB_TYPE, ITabViewPagerView.TYPE_TABURL);
        if (type == ITabViewPagerView.TYPE_DATA) {
            ArrayList<Tab> tabs = getIntent().getParcelableArrayListExtra(ITabViewPagerView.PARAM_TABS);
            TabViewPagerFragment tvpf = TabViewPagerFragment.newInstanceHasTabs(tabs, defaultIndex);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, tvpf).show(tvpf).commit();
        } else {
            TabViewPagerFragment tvpf = TabViewPagerFragment.newInstance(tabUrl, defaultIndex);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, tvpf).show(tvpf).commit();
        }
    }
}
