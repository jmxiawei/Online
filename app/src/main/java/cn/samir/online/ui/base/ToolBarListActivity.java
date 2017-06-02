package cn.samir.online.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.online.R;
import cn.samir.online.ui.fragment.RecyclerFragment;

/**
 * Created by xiaw on 2017/5/2 0002.
 */

public class ToolBarListActivity extends BaseToolBarActivity {

    @BindView(R.id.fl_container)
    protected FrameLayout flContainer;

    public static final String PARAM_TITLE = "PARAM_TITLE";
    public static final String PARAM_URL = "PARAM_URL";

    protected String title;
    protected String url;


    public static Intent newIntent(Context context, String title, String url) {
        Intent intent = new Intent(context, ToolBarListActivity.class);
        intent.putExtra(PARAM_TITLE, title);
        intent.putExtra(PARAM_URL, url);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_with_list);
        ButterKnife.bind(this);

        initData();
    }

    protected void initData() {

        title = getIntent().getStringExtra(PARAM_TITLE);
        url = getIntent().getStringExtra(PARAM_URL);
        setToolbar(title);
        setUrl(url);
    }


    /**
     * @param url
     */
    protected void setUrl(String url) {
        Fragment recyclerFragment = newInstance(url);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, recyclerFragment).show(recyclerFragment).commit();
    }

    protected Fragment newInstance(String url) {
        RecyclerFragment rf = RecyclerFragment.newInstance(url);
        rf.needMaskToolbar(true);
        return rf;
    }


}
