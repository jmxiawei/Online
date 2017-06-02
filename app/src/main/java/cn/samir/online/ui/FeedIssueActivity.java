package cn.samir.online.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cn.samir.domains.model.IssueNavigationCard;
import cn.samir.domains.model.util.DateUtil;
import cn.samir.online.http.ApiService;
import cn.samir.online.ui.base.ToolBarListActivity;
import cn.samir.online.util.Constant;

/** 某一天的推荐
 * Created by xiaw on 2017/5/2 0002.
 */
public class FeedIssueActivity extends ToolBarListActivity {

    private IssueNavigationCard mIssueNavigationCard;

    public static Intent toThis(Context context, IssueNavigationCard card) {
        Intent to = new Intent(context, FeedIssueActivity.class);
        to.putExtra(Constant.ACTION_EVENT_DATA, card);
        return to;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIssueNavigationCard = getIntent().getParcelableExtra(Constant.ACTION_EVENT_DATA);
        if (mIssueNavigationCard == null) {
            mIssueNavigationCard = new IssueNavigationCard();
            mIssueNavigationCard.setDate(System.currentTimeMillis());
        }
        setUrl(ApiService.baseUrl + ApiService.url_api_v3 + "issue" + "?date=" + mIssueNavigationCard.getDate());
        String indexDay = DateUtil.buildMonthAndDay(mIssueNavigationCard.getDate());
        String today = DateUtil.buildMonthAndDay(System.currentTimeMillis());
        if (today.equals(indexDay)) {
            setToolbar("Today");
        } else {
            setToolbar(indexDay);
        }
    }
}
