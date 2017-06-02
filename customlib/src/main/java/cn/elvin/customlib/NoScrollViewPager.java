package cn.elvin.customlib;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 禁止左右滑动
 * Created by xiaw on 2017/4/17 0017.
 */

public class NoScrollViewPager extends ViewPager {

    private boolean canScroll = false;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (canScroll) {
            return super.dispatchTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (canScroll) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }

    }
}
