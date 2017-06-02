package cn.samir.online.views.behaviors;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import cn.samir.online.views.ToolBar;

/**
 * Created by xiaw on 2017/5/10 0010.
 */

public class CategoryDetailToolBarBehavior extends CoordinatorLayout.Behavior<ToolBar> {


    public CategoryDetailToolBarBehavior() {
    }

    public CategoryDetailToolBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, ToolBar child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, ToolBar child, View directTargetChild, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, ToolBar child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }
}
