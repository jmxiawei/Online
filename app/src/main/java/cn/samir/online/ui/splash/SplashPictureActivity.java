package cn.samir.online.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.configs.Configs;
import cn.samir.online.R;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.PresenterView;
import cn.samir.online.mvp.presenters.SplashConfigPresenter;
import cn.samir.online.ui.MainActivity;
import cn.samir.online.ui.base.BaseActivity;
import cn.samir.online.util.LogUtil;
import cn.samir.online.views.AutoScaleZoomDreewView;
import cn.samir.online.views.CustomFontTextView;
import cn.samir.online.views.LogoInnerOuterAnimView;

/**
 * 图片引导页
 */
public class SplashPictureActivity extends BaseActivity implements PresenterView {

    @BindView(R.id.aszd)
    AutoScaleZoomDreewView aszd;
    @BindView(R.id.logoAnim)
    LogoInnerOuterAnimView logoAnim;
    private static final int TRANSLATE_UP = -200;
    @BindView(R.id.tv_eyepetizer)
    CustomFontTextView tvEyepetizer;
    @BindView(R.id.tv_for)
    CustomFontTextView tvFor;
    @BindView(R.id.tv_today)
    CustomFontTextView tvToday;
    @BindView(R.id.ll_bottom_text)
    LinearLayout llBottomText;
    @BindView(R.id.container)
    RelativeLayout container;
    private TranslateAnimation translateAnimation;

    private SplashConfigPresenter splashConfigPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_picture_ad);
        ButterKnife.bind(this);

        container.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LOW_PROFILE);

        splashConfigPresenter = new SplashConfigPresenter(new BaseHttpHandler(), this);
        splashConfigPresenter.loadConfig();

        startAnim();
    }

    private void startAnim() {
        translateAnimation = new TranslateAnimation(0, 0, 0, TRANSLATE_UP);
        translateAnimation.setDuration(800);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logoAnim.setMode(LogoInnerOuterAnimView.Mode.WHITE);
                llBottomText.setVisibility(View.VISIBLE);
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation1 = new AlphaAnimation(0f, 1.0f);
                TranslateAnimation translateAnimation1 = new TranslateAnimation(0, 0, 0, -50);
                alphaAnimation1.setDuration(800);
                translateAnimation1.setDuration(800);
                animationSet.addAnimation(alphaAnimation1);
                animationSet.addAnimation(translateAnimation1);
                animationSet.setFillAfter(true);
                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                llBottomText.startAnimation(animationSet);
                /**
                 * for today 上移
                 */
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, TRANSLATE_UP);
                translateAnimation.setDuration(800);
                translateAnimation.setFillAfter(true);
                final AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1.0f);
                alphaAnimation.setDuration(800);
                translateAnimation.setFillAfter(true);
                AnimationSet set = new AnimationSet(true);
                set.addAnimation(translateAnimation);
                set.addAnimation(alphaAnimation);
                set.setFillAfter(true);

                tvFor.setVisibility(View.VISIBLE);
                tvToday.setVisibility(View.VISIBLE);
                tvFor.startAnimation(set);
                tvToday.startAnimation(set);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        aszd.setContinueAnim(true);
        aszd.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Eyepetizer 上移
                logoAnim.startAnimation(translateAnimation);
                tvEyepetizer.setVisibility(View.VISIBLE);
                tvEyepetizer.startAnimation(translateAnimation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public boolean onSuccess(Object... params) {

        Configs configs = (Configs) params[0];
        aszd.setImageURI(Uri.parse(configs.getStartPage().getImageUrl()));
        return false;
    }

    @Override
    public boolean onFail(Object... params) {
        LogUtil.e(params[0]);
        //Toast.makeText(this, MsgUtils.getNotifyMessage(params[0]), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();

        return false;
    }

    @Override
    public Context context() {
        return this;
    }
}
