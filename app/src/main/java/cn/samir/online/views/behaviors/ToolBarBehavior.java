package cn.samir.online.views.behaviors;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;



import cn.elvin.customlib.L;
import cn.samir.online.util.LogUtil;

/**
 * Created by xiaw on 2017/5/8 0008.
 */

public class ToolBarBehavior extends CoordinatorLayout.Behavior<View> {


    private static final int INVALID = -10000;
    private static final int INVALID_NULL_DATA = -20000;
    private int childHieght = 0;


    private boolean isDebug = true;


    private View child = null;//toobar
    private View target = null;//recycler

    private boolean enabled = false;

    private void log(String msg) {
        if (isDebug) {
            L.e("ToolBarBehavior", msg);
        }

    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private String logArray(int[] d) {
        StringBuffer stringBuffer = new StringBuffer(" [ ");
        int l = d.length;
        for (int i = 0; i < l; i++) {
            stringBuffer.append(d[i]).append(",");
        }
        stringBuffer.append(" ] ");
        return stringBuffer.toString();

    }

    public ToolBarBehavior() {
    }

    public ToolBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        L.e("ToolBarBehavior context attrs");
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        log("onStartNestedScroll");
        //checkPosition(child, target);
        this.child = child;
        this.target = target;

        coordinatorLayout.removeCallbacks(checkRunnable);

        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
        if (isDebug) {
            log("onNestedScrollAccepted");
        }
        checkPosition(child, target);
    }

    /**
     * 滑动结束后再次判断
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     */
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        if (isDebug) {
            log("onStopNestedScroll");
        }
        checkPosition(child, target);
        coordinatorLayout.postDelayed(checkRunnable, 500);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        if (isDebug) {
            log("onNestedFling velocityY=" + velocityY);
        }
        checkPosition(child, target);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (isDebug) {
            log("onNestedPreScroll,consumed" + logArray(consumed));
        }
        checkPosition(child, target);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {

        if (isDebug) {
            log("onNestedPreFling velocityY=" + velocityY);
        }
        checkPosition(child, target);
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);

    }

    private Runnable checkRunnable = new Runnable() {
        @Override
        public void run() {
            checkPosition(child, target);
        }
    };

    private void checkPosition(View child, View target) {

        if (child == null || target == null || !enabled) {
            return;
        }
        if (target instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) target;
            int top = checkFirstItemCompletelyVisible(recyclerView);
            float alpha = Math.abs(1 - (Math.abs(top) * 1.0f / childHieght));
            if (top != INVALID) {
                if (alpha < 0.1f) {
                    alpha = 0;
                } else if (alpha > 1) {
                    alpha = 1;
                }
                child.setAlpha(alpha);
            } else {
                child.setAlpha(0);
            }
            if (isDebug) {
                LogUtil.e("mDx=%d,alpha=%f", top, alpha);
            }
        }
    }

    /**
     * @param coordinatorLayout
     * @param child             使用这个Behavior 的控件
     * @param target            产生滑动效果的控件
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (isDebug) {
            log("onNestedScroll dyConsumed=" + dyConsumed + ",dyUnconsumed=" + dyUnconsumed);
        }
        checkPosition(child, target);
    }

    private int checkFirstItemCompletelyVisible(RecyclerView recyclerView) {
        RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
        if (lm == null) {
            return INVALID_NULL_DATA;
        }
        if (lm.getChildCount() == 0) {
            return INVALID_NULL_DATA;
        }
        View child = lm.getChildAt(0);
        int firstVisiblePosition = ((RecyclerView.LayoutParams) child.getLayoutParams()).getViewAdapterPosition();
        int top = child.getTop();
        if (firstVisiblePosition == 0) {
            if (childHieght == 0) {
                childHieght = child.getHeight();
            }
            return top;
        }
        if (isDebug) {
            log("firstVisiblePosition=" + firstVisiblePosition + ",top=" + top);
        }
        return INVALID;
    }
}
