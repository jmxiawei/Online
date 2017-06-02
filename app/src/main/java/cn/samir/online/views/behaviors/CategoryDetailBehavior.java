package cn.samir.online.views.behaviors;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import cn.samir.online.util.LogUtil;

import cn.samir.online.R;

/**
 * Created by xiaw on 2017/5/10 0010.
 */
public class CategoryDetailBehavior extends CoordinatorLayout.Behavior<LinearLayout> {
    // child ll_container
    // targetview recycler_view
    // dependency fl_container


    private static final int MOVE_UP = 1;
    private static final int MOVE_DOWN = 0;
    private AppBarLayout appBarLayout;
    private ValueAnimator mOffsetAnimator;


    private int mMaxOffsetFromTop = -1;
    private int mToolBarHeight;
    private boolean wasNestedFling = false;


    private int mdependencyViewTop = -1;

    public CategoryDetailBehavior() {
    }


    void log(String msg) {
        LogUtil.e(msg);
    }

    public CategoryDetailBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        mToolBarHeight = context.getResources().getDimensionPixelOffset(R.dimen.top_title_height);

    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, LinearLayout child, View directTargetChild, View target, int nestedScrollAxes) {


        if (mMaxOffsetFromTop < 0) {
            mMaxOffsetFromTop = child.getTop();
        }
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, LinearLayout child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {

        if (mdependencyViewTop < 0) {
            mdependencyViewTop = dependency.getTop();
        }
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, LinearLayout child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        log("onNestedPreScroll dy=" + dy);

        int top = getChildOffsetFromTop(child);

        if (dy > 0) {
            /**
             *
             */
            int offset = top - mdependencyViewTop;
            if (offset > 0) {
                //还有向上滑动的空间
                animateOffsetWithDuration(coordinatorLayout, child, -dy, dy * 10);
                consumed[1] = dy;
            }
        } else {

            //向下滑
            int offset = top - mMaxOffsetFromTop;
            if (offset < 0) {
                //还可以向下滑动
                animateOffsetWithDuration(coordinatorLayout, child, -dy, dy * 10);
                consumed[1] = dy;
            }
        }
    }


    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, LinearLayout child, View target, float velocityX, float velocityY, boolean consumed) {
        log("onNestedFling velocityY=" + velocityY + ",consumed=" + consumed);


        boolean fling = false;
        if (consumed) {
            if (velocityY > 0) {
                //上滑动
                /**
                 * 看child 距离toolbar的距离
                 */
                int top = getChildOffsetFromTop(child);
                if (top != mdependencyViewTop) {
                    // 还没顶到toolbar
                    animateOffsetTo(coordinatorLayout, child, mdependencyViewTop - top, velocityY);
                    fling = true;
                }

            } else {
                // 下滑动

                int top = getChildOffsetFromTop(child);

                if (top < mMaxOffsetFromTop) {
                    animateOffsetTo(coordinatorLayout, child, mMaxOffsetFromTop - top, velocityY);
                    fling = true;
                }
            }

        }
        wasNestedFling = fling;
        return fling;
        //super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, LinearLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        log("onNestedPreScroll dyConsumed=" + dyConsumed + ",dyUnconsumed=" + dyUnconsumed);


    }


    int getChildOffsetFromTop(LinearLayout child) {
        return child.getTop();
    }

    /**
     * @param coordinatorLayout
     * @param child
     * @param offset            需要移动的距离， 正负都有可能
     * @param velocity
     */
    private void animateOffsetTo(final CoordinatorLayout coordinatorLayout,
                                 final LinearLayout child, final int offset, float velocity) {
        final int distance = offset;

        final int duration;
        velocity = Math.abs(velocity);
        if (velocity > 0) {
            duration = 3 * Math.round(1000 * (distance / velocity));
        } else {
            final float distanceRatio = (float) distance / child.getHeight();
            duration = (int) ((distanceRatio + 1) * 150);
        }
        log("animateOffsetTo offset=" + offset + ",duration=" + duration);
        animateOffsetWithDuration(coordinatorLayout, child, offset, duration);
    }


    /**
     * @param coordinatorLayout
     * @param child
     * @param offset
     * @param duration
     */
    private void animateOffsetWithDuration(final CoordinatorLayout coordinatorLayout,
                                           final LinearLayout child, final int offset, final int duration) {
        //当前的
        final int currentOffset = child.getTop() - mdependencyViewTop;
        if (child.getTop() < mdependencyViewTop || child.getTop() > mMaxOffsetFromTop) {
            //超过了顶部或者超过了底部
            if (mOffsetAnimator != null && mOffsetAnimator.isRunning()) {
                mOffsetAnimator.cancel();
            }
            return;
        }

        if (mOffsetAnimator == null) {
            mOffsetAnimator = new ValueAnimator();
            mOffsetAnimator.setInterpolator(new DecelerateInterpolator());
            mOffsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    //log("animator=" + (int) animator.getAnimatedValue());
                    setChildMarginTop(child, (int) animator.getAnimatedValue(), offset > 0 ? MOVE_DOWN : MOVE_UP);
                }
            });
        } else {
            mOffsetAnimator.cancel();
        }

        mOffsetAnimator.setDuration(Math.min(Math.abs(duration), 2000));
        mOffsetAnimator.setIntValues(currentOffset, currentOffset + offset);
        mOffsetAnimator.start();
    }

    /**
     * @param child
     * @param offset
     * @param or     1 向上  0 向下
     */
    private void setChildMarginTop(LinearLayout child, int offset, int or) {

        ViewGroup.MarginLayoutParams ll = (ViewGroup.MarginLayoutParams) child.getLayoutParams();


        if (or == 1) {
            if (offset < mdependencyViewTop) {
                offset = mdependencyViewTop;
            }
        } else {
            if (offset > mMaxOffsetFromTop) {
                offset = mMaxOffsetFromTop;
            }
        }
        ll.topMargin = offset;
        log("setChildMarginTop=" + offset);
        child.setLayoutParams(ll);
    }

}
