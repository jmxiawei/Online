package cn.samir.online.views;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.dinuscxj.pullzoom.ILoadMoreView;
import cn.samir.online.util.LogUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.IssueNavigationCard;
import cn.samir.online.R;
import cn.samir.online.adapter.EyeDataRecyclerViewAdapter;
import cn.samir.online.adapter.OnReachBottomListener;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.PresenterView;
import cn.samir.online.mvp.presenters.FeedIssuePresenter;
import cn.samir.online.util.Utils;

/**
 * Created by xiaw on 2017/5/2 0002.
 */

public class SelectDatePopWindow extends PopupWindow implements OnReachBottomListener, PresenterView {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    FeedIssuePresenter feedIssuePresenter;

    private ApiResult<ArrayList<BaseModel>> arrayListApiResult;
    EyeDataRecyclerViewAdapter selectDateAdapter;
    private ArrayList<IssueNavigationCard> issueNavigationCards = new ArrayList<>();
    private View contentView;

    public SelectDatePopWindow(Context context) {
        super(context);

        contentView = LayoutInflater.from(context).inflate(R.layout.pop_selelct_date, null);
        ButterKnife.bind(this, contentView);
        //设置SelectPicPopupWindow的View
        this.setContentView(contentView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setTouchable(true);
        this.setOutsideTouchable(true);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.u_pop_select_date_anim_style);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);


        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setHasFixedSize(true);
        selectDateAdapter = new EyeDataRecyclerViewAdapter(false, recyclerView);
        selectDateAdapter.setOnReachBottomListener(this);
        recyclerView.setAdapter(selectDateAdapter);


        feedIssuePresenter = new FeedIssuePresenter(new BaseHttpHandler(), this);
        feedIssuePresenter.loadIssueNavigationCard(System.currentTimeMillis());

    }

    @Override
    public void onReachBottom() {

        if (arrayListApiResult != null) {
            if (!Utils.isEmpty(arrayListApiResult.nextPageUrl)) {
                feedIssuePresenter.loadIssueNavigationCard(arrayListApiResult.nextPageUrl);
                selectDateAdapter.setLoadMore(ILoadMoreView.STATE_LOADING, false);
                LogUtil.e("onReachButtom=" + arrayListApiResult.nextPageUrl);

            } else {
                selectDateAdapter.setLoadMore(ILoadMoreView.STATE_NOMORE, false);
            }
        }
    }

    @Override
    public boolean onSuccess(Object... params) {

        if (params[0] instanceof ApiResult) {
            arrayListApiResult = (ApiResult<ArrayList<BaseModel>>) params[0];
            selectDateAdapter.addDataList(arrayListApiResult.itemList);
            selectDateAdapter.setLoadMore(ILoadMoreView.STATE_COMPLETE, false);
            return true;
        }

        return false;
    }

    @Override
    public boolean onFail(Object... params) {
        return false;
    }

    @Override
    public Context context() {
        return getContentView().getContext();
    }


}
