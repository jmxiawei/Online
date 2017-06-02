package cn.samir.online.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 不能滑动的viewpager
 * Created by xiaw on 2017/5/9 0009.
 */

public class CustomViewPager extends ViewPager {

    private boolean autoSwitchPage = false;

    private boolean canScroll = false;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean isCanScroll() {
        return canScroll;
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


        return this.canScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.canScroll && super.onTouchEvent(ev);
    }

    public boolean isAutoSwitchPage() {
        return autoSwitchPage;
    }

    public void setAutoSwitchPage(boolean autoSwitchPage) {
        this.autoSwitchPage = autoSwitchPage;
    }
}


