package cn.samir.online.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.CategoryDetail;
import cn.samir.domains.model.PgcInfo;
import cn.samir.online.R;
import cn.samir.online.adapter.ItemSettings;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.PresenterView;
import cn.samir.online.mvp.cases.TabViewPagerViewImp;
import cn.samir.online.mvp.presenters.PCGSDetailPresenter;
import cn.samir.online.mvp.presenters.TabViewPagerPresenter;
import cn.samir.online.ui.base.BaseActivity;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.views.CustomFontTextView;
import cn.samir.online.views.CustomTabLayout;

/**
 * 某个作者的详情
 */
public class PGCSDetailActivity extends BaseActivity implements PresenterView {

    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.tv_author_name)
    CustomFontTextView tvAuthorName;
    @BindView(R.id.tv_category)
    CustomFontTextView tvCategory;
    @BindView(R.id.btn_follow)
    CustomFontTextView btnFollow;
    @BindView(R.id.tv_desc)
    CustomFontTextView tvDesc;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    CustomTabLayout tabLayout;
    @BindView(R.id.collapsingtoolbarlayout)
    CollapsingToolbarLayout collapsingtoolbarlayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private PCGSDetailPresenter pcgsDetailPresenter;
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

//
//    public static String buildActionUrl(String id) {
//        /**
//         * android:host="pgc"
//         android:pathPrefix="/detail"
//         android:scheme="eyepetizer"
//         */
//
//        StringBuilder action = new StringBuilder("eyepetizer:://");
//        action.append("/pgc").append("/detail/").append(id);
//        return action.toString();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgcs_detail);
        ButterKnife.bind(this);
        toolbar.setTitleTextColor(getResources().getColor(R.color.text_color_black));
        toolbar.setNavigationIcon(R.mipmap.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String data = getIntent().getDataString();
        Uri dataUri = Uri.parse(data);
        String path = dataUri.getLastPathSegment();
        id = Integer.parseInt(path);
        appbar.addOnOffsetChangedListener(onLayoutChangeListener);
        pcgsDetailPresenter = new PCGSDetailPresenter(this, new BaseHttpHandler());
        pcgsDetailPresenter.loadPcgsDetailTab(id);
    }


    AppBarLayout.OnOffsetChangedListener onLayoutChangeListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

            if (Math.abs(verticalOffset) > 100) {
                if (!showTitle) {
                    showTitle = true;
                    toolbar.setTitle(mCategoryDetail.getPgcInfo().getName());
                }
            } else if (showTitle) {
                showTitle = false;
                toolbar.setTitle("");
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
            ItemSettings itemSettings = new ItemSettings();
            itemSettings.setBackground(ItemSettings.BACKGROUND_LIGHT);
            tabViewPagerViewImp.setItemSettings(itemSettings);
            tabViewPagerPresenter = new TabViewPagerPresenter(new BaseHttpHandler(), tabViewPagerViewImp, mArguments);
        }
        return false;
    }


    /**
     * @param mCategoryDetail
     */
    private void showHeaderContent(CategoryDetail mCategoryDetail) {

        PgcInfo categoryInfo = mCategoryDetail.getPgcInfo();
        PhotoUtil.showPhoto(sdvHeader, categoryInfo.getIcon());
        tvDesc.setText(categoryInfo.getDescription());
        tvCategory.setText(categoryInfo.getBrief());
        tvAuthorName.setText(categoryInfo.getName());


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
