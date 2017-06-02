package cn.samir.online.views.video;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import cn.samir.online.util.DensityUtil;
import cn.samir.online.util.LogUtil;

/**
 * 亮度
 * Created by xiaw on 2017/5/24 0024.
 */
public class BrightnessView extends FrameLayout {




    private static final boolean DEBUG = true;
    private float downX;
    private float downY;


    private float lastX;
    private float lastY;


    private int mTouchSlop;

    private int mScreenWidth;
    private int mScreenHeight;

    public BrightnessView(@NonNull Context context) {
        this(context, null);
    }


    public BrightnessView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public BrightnessView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScreenWidth = DensityUtil.getDisplayWidth(context);
        mScreenHeight = DensityUtil.getDisplayHeight(context);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = downX = ev.getX();
                lastY = downY = ev.getY();
                if (DEBUG) {
                    LogUtil.e("MotionEvent.ACTION_DOWN");
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                float currentX = ev.getX();
                float currentY = ev.getY();
                float deltaX = currentX - lastX;
                float deltaY = currentY - lastY;
                if (DEBUG) {
                    LogUtil.e("MotionEvent.ACTION_MOVE=" + deltaY);
                }
                updateBrightness(-deltaY);
                break;
        }

        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 默认不拦截
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }


    private void updateBrightness(float delta) {
        int current = DensityUtil.getScreenBrightness((Activity) getContext());
        int deltaB = (int) (delta * mScreenHeight / 500);
        current = current + deltaB;
        current = checkValue(current);
        DensityUtil.setScreenBrightness((Activity) getContext(), current);
        if (DEBUG) {
            LogUtil.e("updateBrightness=" + current);
        }
    }


    private int checkValue(int v) {
        if (v < 10) {
            v = 10;
        } else if (v > 240) {
            v = 240;
        }
        return v;
    }

}


