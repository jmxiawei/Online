package cn.samir.online.views;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import cn.samir.online.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.online.R;

/**
 * 1.白色的一直动画
 * 2.上移动160dp
 * 3.换成黑色
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Created by xiaw on 2017/4/1 0001.
 */
public class LogoInnerOuterAnimView extends FrameLayout {

    @BindView(R.id.img_inner)
    ImageView imgInner;
    @BindView(R.id.img_outer)
    ImageView imgOuter;

    private Mode mMode = Mode.WHITE;

    private int[] blackImageResource = new int[]{
            R.mipmap.ic_eye_black_inner, R.mipmap.ic_eye_black_outer
    };
    private int[] whiteImageResource = new int[]{
            R.mipmap.ic_eye_white_inner, R.mipmap.ic_eye_white_outer
    };


    private RotateAnimation mRotateAnim;


    private AnimationSet mAnimtionSet;

    private AlphaAnimation exitAnim;
    private AlphaAnimation inAnim;


    public static final int RotateAnimDuration = 800;

    public LogoInnerOuterAnimView(@NonNull Context context) {
        this(context, null);
    }

    public LogoInnerOuterAnimView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogoInnerOuterAnimView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_inner_outer, this, true);
        ButterKnife.bind(this);

        inAnim = new AlphaAnimation(0.0f, 1f);
        inAnim.setFillAfter(true);
        inAnim.setDuration(300);
        exitAnim = new AlphaAnimation(1.0f, 0.0f);
        exitAnim.setFillAfter(true);
        exitAnim.setDuration(300);
        mAnimtionSet = new AnimationSet(false);
        mRotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnim.setDuration(RotateAnimDuration);
        mRotateAnim.setFillAfter(true);
        mRotateAnim.setInterpolator(new LinearInterpolator());
        mRotateAnim.setRepeatCount(Animation.INFINITE);
        mAnimtionSet.addAnimation(mRotateAnim);
        init();

    }

    public void setMode(Mode mMode) {

        if (this.mMode != mMode) {
            //动画切换一下
            this.mMode = mMode;
            exitAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    LogUtil.e("exitAnim onAnimationEnd");
                    inAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            setResource();
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            LogUtil.e("inAnim onAnimationEnd");

                            mRotateAnim.setFillAfter(true);
                            imgInner.startAnimation(mRotateAnim);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    startAnimation(inAnim);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            startAnimation(exitAnim);
        }
    }


    private int[] getResouce(Mode mode) {
        if (mode == Mode.BLACK) {
            return blackImageResource;
        } else {
            return whiteImageResource;
        }
    }

    private void init() {
        setResource();
        imgInner.startAnimation(mRotateAnim);
    }

    private void setResource() {
        int[] res = getResouce(mMode);
        imgInner.setImageResource(res[0]);
        imgOuter.setImageResource(res[1]);
    }


    public enum Mode {
        WHITE, BLACK
    }


}
