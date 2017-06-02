package cn.samir.online.adapter.holders.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import cn.samir.online.util.LogUtil;

import cn.samir.online.R;
import cn.samir.online.adapter.holders.base.CommonViewHolder;

/**
 * 点击隐藏遮盖的view
 * Created by xiaw on 2017/5/7 0007.
 */
public abstract class CommonShadeViewHolder<T> extends CommonViewHolder<T> implements View.OnTouchListener {


    public static final int SHADE_DELAY = 300;
    private View mShadeView;
    private boolean shade = true;
    private boolean isDebug = false;
    private int mCurrentVisibleStatus = View.VISIBLE;
    private AlphaAnimation alphaInAnim;
    private AlphaAnimation alphaOutAnim;
    private Handler mHandler = null;

    public CommonShadeViewHolder(Context context, ViewGroup root, int layoutRes) {
        super(context, root, layoutRes);
        mShadeView = getShadeView();
        setShade(true);
        alphaInAnim = (AlphaAnimation) AnimationUtils.loadAnimation(context, R.anim.anim_alpha_in);
        alphaInAnim.setFillAfter(true);
        alphaOutAnim = (AlphaAnimation) AnimationUtils.loadAnimation(context, R.anim.anim_alpha_out);
        alphaOutAnim.setFillAfter(true);
        alphaOutAnim.setAnimationListener(alphaOutAnimListener);
        alphaInAnim.setAnimationListener(alphaInAnimListener);
        mHandler = new Handler(Looper.getMainLooper());
    }


    /**
     * disable {@link CommonViewHolder} setItemViewClickListener,we use onTouch instead
     */
    @Override
    protected void setItemViewClickListener() {
        //
    }

    public boolean isShade() {
        return shade;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
        if (this.shade) {
            itemView.setOnTouchListener(this);
            itemView.setOnClickListener(null);
        } else {
            itemView.setOnTouchListener(null);
            itemView.setOnClickListener(itemViewClickListener);
        }
    }


    @Override
    public boolean onTouch(final View v, MotionEvent event) {


        if (isDebug) {
            LogUtil.e("action = " + event.getAction() + ".v = " + v.hashCode() + ",t=" + System.currentTimeMillis());
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isShade()) {
                    itemView.postDelayed(shadeHideRunnable, SHADE_DELAY);
                }
                return true;

            case MotionEvent.ACTION_CANCEL:
                if (isShade()) {
                    itemView.removeCallbacks(shadeHideRunnable);
                    itemView.post(shadeShowRunnable);
                }
                break;
            case MotionEvent.ACTION_UP:

                itemView.removeCallbacks(shadeHideRunnable);
                itemView.post(shadeShowRunnable);

                //点击事件
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        itemViewClickListener.onClick(v);
                    }
                }, isShade() ? 500 : 10);

        }

        return false;
    }


    private Animation.AnimationListener alphaOutAnimListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {


        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mShadeView.setVisibility(View.INVISIBLE);
            mCurrentVisibleStatus = View.INVISIBLE;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private Animation.AnimationListener alphaInAnimListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            mShadeView.setVisibility(View.VISIBLE);
            mCurrentVisibleStatus = View.VISIBLE;
        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };


    private Runnable shadeHideRunnable = new Runnable() {
        @Override
        public void run() {
            if (mShadeView != null && mCurrentVisibleStatus == View.VISIBLE) {
                mShadeView.startAnimation(alphaOutAnim);
            }
        }
    };

    private Runnable shadeShowRunnable = new Runnable() {
        @Override
        public void run() {

            if (mShadeView != null && mCurrentVisibleStatus == View.INVISIBLE) {
                mShadeView.startAnimation(alphaInAnim);
            }

        }
    };


    public abstract View getShadeView();
}
