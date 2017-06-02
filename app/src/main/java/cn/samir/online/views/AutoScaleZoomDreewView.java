package cn.samir.online.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.samir.online.util.Constant;

/**
 * Created by xiaw on 2017/3/30 0030.
 */

public class AutoScaleZoomDreewView extends SimpleDraweeView {


    private int TOTAL_DURATION = 1600;
    private int DURATION = 50;
    private int mCurrentIndex = 1;

    private AbstractDraweeController abstractDraweeController;

    private float mMaxScale = 1.05f;
    private float mMinScale = 1.0f;


    private float mCurrentScale = 1.0f;
    ScaleAnimation enlargeAnimation;
    ScaleAnimation scaleAnimation;

    private boolean isContinueAnim = true;

    private Animation.AnimationListener animationListener;

    public AutoScaleZoomDreewView(Context context) {
        this(context, null);
    }

    public AutoScaleZoomDreewView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScaleZoomDreewView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        enlargeAnimation = new ScaleAnimation(mMinScale, mMaxScale, mMinScale, mMaxScale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        enlargeAnimation.setDuration(TOTAL_DURATION);
        enlargeAnimation.setFillAfter(true);
        enlargeAnimation.setRepeatCount(0);
        enlargeAnimation.setAnimationListener(enlargeListener);


        scaleAnimation = new ScaleAnimation(mMaxScale, mMinScale, mMaxScale, mMinScale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(TOTAL_DURATION);
        enlargeAnimation.setFillAfter(true);
        scaleAnimation.setRepeatCount(0);
        scaleAnimation.setAnimationListener(scaleListener);
    }

    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.animationListener = animationListener;
    }

    private Animation.AnimationListener enlargeListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            if (animationListener != null) {
                animationListener.onAnimationStart(animation);
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isContinueAnim()) {
                startAnimation(scaleAnimation);
            }

            if (animationListener != null) {
                animationListener.onAnimationEnd(animation);
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            if (animationListener != null) {
                animationListener.onAnimationRepeat(animation);
            }
        }
    };

    public boolean isContinueAnim() {
        return isContinueAnim;
    }

    public void setContinueAnim(boolean continueAnim) {
        isContinueAnim = continueAnim;
    }

    Animation.AnimationListener scaleListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isContinueAnim()) {
                startAnimation(enlargeAnimation);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };


    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        startAnimation(enlargeAnimation);
    }

    private float getScale(int index) {
        //0-pi 0-1 1-0
        float add = (mMaxScale - mMinScale) / 2.0f;
        return (float) (mMinScale + add * (1 + Math.sin(index * Constant.PI / 100.f)));

    }


}
