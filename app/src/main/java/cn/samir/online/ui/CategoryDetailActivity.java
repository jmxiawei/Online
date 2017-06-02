package cn.samir.online.ui;

import android.content.Context;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.CategoryDetail;
import cn.samir.domains.model.CategoryInfo;
import cn.samir.online.R;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.PresenterView;
import cn.samir.online.mvp.cases.TabViewPagerViewImp;
import cn.samir.online.mvp.presenters.CategoryDetailPresenter;
import cn.samir.online.mvp.presenters.TabViewPagerPresenter;
import cn.samir.online.ui.base.BaseActivity;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.views.CustomFontTextView;
import cn.samir.online.views.CustomTabLayout;

/**
 * 某个分类的详情 tab部分
 */
public class CategoryDetailActivity extends BaseActivity implements PresenterView {


    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.tv_description)
    CustomFontTextView tvDescription;
    @BindView(R.id.tv_category)
    CustomFontTextView tvCategory;
    @BindView(R.id.btn_follow)
    CustomFontTextView btnFollow;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    CustomTabLayout tabLayout;
    @BindView(R.id.collapsingtoolbarlayout)
    CollapsingToolbarLayout collapsingtoolbarlayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    private CategoryDetailPresenter categoryDetailPresenter;
    private TabViewPagerPresenter tabViewPagerPresenter;
    private TabViewPagerViewImp tabViewPagerViewImp;
    private int id;
    private CategoryDetail mCategoryDetail;

    /**
     * /api/v4/categories/detail/tab?id=36
     *
     * @param savedInstanceState
     */

    Bundle mArguments;

    private boolean showTitle = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.mipmap.ic_player_close_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String data = getIntent().getDataString();
        Uri dataUri = Uri.parse(data);
        String path = dataUri.getPath().replace("/", "");
        id = Integer.parseInt(path);
        appbar.addOnOffsetChangedListener(onLayoutChangeListener);
        categoryDetailPresenter = new CategoryDetailPresenter(this, new BaseHttpHandler());
        categoryDetailPresenter.loadCategoryDetailTab(id);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

       
        return super.onTouchEvent(event);
    }

    AppBarLayout.OnOffsetChangedListener onLayoutChangeListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

            if (Math.abs(verticalOffset) > 100) {
                if (!showTitle) {
                    showTitle = true;
                    toolbar.setSubtitle(mCategoryDetail.getCategoryInfo().getName());
                }
            } else if (showTitle) {
                showTitle = false;
                toolbar.setSubtitle("");
            }
        }
    };

    @Override
    public boolean onSuccess(Object... params) {

        if (params[0] instanceof CategoryDetail) {
            mCategoryDetail = (CategoryDetail) params[0];
            showHeaderContent(mCategoryDetail);
            mArguments = TabViewPagerViewImp.getArguments(mCategoryDetail.getTabInfo().getTabList(), 0);
            tabViewPagerViewImp = new TabViewPagerViewImp(getSupportFragmentManager(), tabLayout, viewPager, mArguments);
            tabViewPagerPresenter = new TabViewPagerPresenter(new BaseHttpHandler(), tabViewPagerViewImp, mArguments);
        }
        return false;
    }


    /**
     * @param mCategoryDetail
     */
    private void showHeaderContent(CategoryDetail mCategoryDetail) {

        CategoryInfo categoryInfo = mCategoryDetail.getCategoryInfo();
        PhotoUtil.showPhoto(sdvHeader, categoryInfo.getHeaderImage());
        tvDescription.setText(categoryInfo.getDescription());
        tvCategory.setText(categoryInfo.getName());
    }

    @Override
    public boolean onFail(Object... params) {
        return false;
    }

    @Override
    public Context context() {
        return null;
    }

}
