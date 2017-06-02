package cn.samir.online.ui;

import android.os.Bundle;
import android.view.View;

import cn.samir.domains.model.util.DateUtil;
import cn.samir.online.R;
import cn.samir.online.ui.base.ToolBarListActivity;
import cn.samir.online.ui.fragment.FeedHistoryFragment;
import cn.samir.online.views.SelectDatePopWindow;

/**
 * 推荐 历史记录
 * {@link cn.samir.online.http.ApiService} baseurl/api/v2/feed
 */
public class FeedHistoryActivity extends ToolBarListActivity {

    private SelectDatePopWindow popWindow;

    protected void setUrl(int number, long date) {
        FeedHistoryFragment recyclerFragment = FeedHistoryFragment.newInstance(number, date);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, recyclerFragment).show(recyclerFragment).commit();

    }

    // TODO: 2017/5/3  issuelist 解析，不是itemList
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(getString(R.string.str_feed_issue));
        setUrl(3, System.currentTimeMillis());
        popWindow = new SelectDatePopWindow(this);
        setRightNavigationIcon(0, DateUtil.buildMonthAndDay(System.currentTimeMillis()));
        setRightNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.showAsDropDown(toolbar);
            }
        });
    }
}
