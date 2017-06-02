package cn.samir.online.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.elvin.customlib.FlowLayout;
import cn.samir.online.R;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.intefaces.ISearchByKeyWordView;
import cn.samir.online.mvp.presenters.SearchByKeyWordPresenter;
import cn.samir.online.ui.base.BaseActivity;
import cn.samir.online.ui.fragment.SearchResultFragment;
import cn.samir.online.util.Utils;
import cn.samir.online.views.CustomFontTextView;

public class QueryByKeyWordActivity extends BaseActivity implements ISearchByKeyWordView, TextView.OnEditorActionListener {

    SearchByKeyWordPresenter searchByKeyWordPresenter;
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.search_notify)
    CustomFontTextView searchNotify;
    @BindView(R.id.hotkey)
    CustomFontTextView hotkey;
    @BindView(R.id.result_container)
    FrameLayout resultContainer;

    SearchResultFragment searchResultFragment;

    public static void toThis(Activity from) {
        from.startActivity(new Intent(from, QueryByKeyWordActivity.class));
        from.overridePendingTransition(R.anim.anim_alpha_in, R.anim.anim_alpha_out);
    }


    @Override
    public void onBackPressed() {
        rlContent.startAnimation(getOutAnim());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_by_key_word);
        ButterKnife.bind(this);
        searchByKeyWordPresenter = new SearchByKeyWordPresenter(new BaseHttpHandler(), this);
        rlContent.startAnimation(getInAnim());
        searchByKeyWordPresenter.loadHotKeyWord();
        searchResultFragment = new SearchResultFragment();

        etKeyword.setOnEditorActionListener(this);

    }


    private TranslateAnimation getInAnim() {

        TranslateAnimation localTranslateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF, -1.0F, Animation.RELATIVE_TO_SELF, 0.0F);
        localTranslateAnimation.setDuration(500L);
        localTranslateAnimation.setFillAfter(true);
        return localTranslateAnimation;
    }

    private TranslateAnimation getOutAnim() {
        TranslateAnimation localTranslateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF, -1.0F);
        localTranslateAnimation.setDuration(500L);
        localTranslateAnimation.setFillAfter(true);
        localTranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                finish();
                overridePendingTransition(0, R.anim.activity_exit_to_top);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return localTranslateAnimation;
    }

    @Override
    public boolean onSuccess(Object... params) {


        if (params[0] instanceof ArrayList) {

            ArrayList<String> tags = (ArrayList<String>) params[0];
            int size = tags.size();

            if (size > 0) {
                flowLayout.removeAllViews();
            }
            for (int i = 0; i < size; i++) {
                flowLayout.addView(createTagView(tags.get(i)));
            }
        } else {

        }

        return false;
    }


    private View createTagView(final String string) {
        CustomFontTextView view = (CustomFontTextView) LayoutInflater.from(this).inflate(R.layout.layout_item_tag_view, flowLayout, false);
        view.setText(Utils.applySpace(string, 1));
        view.setTag(view.getId(), string);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (!searchResultFragment.isAdded()) {
                    ft.add(R.id.result_container, searchResultFragment);
                }
                ft.show(searchResultFragment).commitAllowingStateLoss();
                searchResultFragment.query(String.valueOf(v.getTag(v.getId())));
            }
        });
        return view;
    }

    @Override
    public boolean onFail(Object... params) {
        return false;
    }

    @Override
    public Context context() {
        return this;
    }

    @OnClick(R.id.btn_cancel)
    public void onClick() {
        rlContent.startAnimation(getOutAnim());
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (actionId == KeyEvent.KEYCODE_SEARCH) {


        }
        return false;
    }
}
