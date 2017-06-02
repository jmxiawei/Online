package cn.elvin.customlib;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;

/**
 * Created by xiaw on 2017/4/11 0011.
 */

public class AutoScrollIndicatorViewPager extends IndicatorViewPager {

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int duration = 3000;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            PagerAdapter adapter = getAdapter();
            if (adapter != null) {
                int count = adapter.getCount();
                if (count > 1) {
                    int currentItem = getCurrentItem();
                    setCurrentItem(++currentItem % count);
                }
            }
            mHandler.postDelayed(this, duration);
        }
    };

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public AutoScrollIndicatorViewPager(Context context) {
        this(context, null);
    }

    public AutoScrollIndicatorViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        mHandler.postDelayed(runnable, duration);
    }


}
