package cn.elvin.customlib;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * Created by xiaw on 2017/3/20 0020.
 */

public class PullUpFinishLayout extends FrameLayout {


    private int downx;
    private int downY;


    private boolean isCancel = false;

    private int lastx;
    private int lastY;


    private int mTouchSlop;

    private boolean isFinish = false;
    private OnFinishListener onFinishListener;

    public OnFinishListener getOnFinishListener() {
        return onFinishListener;
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    public PullUpFinishLayout(@NonNull Context context) {
        this(context, null);
    }

    public PullUpFinishLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullUpFinishLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        int action = ev.getAction() & MotionEvent.ACTION_MASK;


        switch (action) {

            case MotionEvent.ACTION_DOWN:
                isFinish = false;
                downx = (int) ev.getX();
                downY = (int) ev.getY();
                L.e("PullUpFinishLayout", "ACTION_DOWN" + downx + "," + downY);
                break;


            case MotionEvent.ACTION_MOVE:

                int currentX = (int) ev.getX();
                int currentY = (int) ev.getY();
                int deltaX = Math.abs(currentX - lastx);
                int deltaY = Math.abs(currentY - lastY);
                lastx = currentX;
                lastY = currentY;
                if (deltaY > deltaX) {
                    if (Math.abs(lastY - downY) > Math.abs(lastx - downx) && Math.abs(lastY - downY) > mTouchSlop * 5) {
                        if (onFinishListener != null && !isFinish) {
                            isFinish = true;
                            onFinishListener.onFinish();
                        }
                    }
                }

                break;
        }

        boolean rs = super.dispatchTouchEvent(ev);
        L.e("PullUpFinishLayout", "rs=" + rs);
        return rs;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
        /*int action = event.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isCancel = false;
                downx = (int) event.getX();
                downY = (int) event.getY();
                L.e("PullUpFinishLayout", "ACTION_DOWN" + downx + "," + downY);
                return true;


            case MotionEvent.ACTION_MOVE:

                int currentX = (int) event.getX();
                int currentY = (int) event.getY();
                int deltaX = Math.abs(currentX - lastx);
                int deltaY = Math.abs(currentY - lastY);
                lastx = currentX;
                lastY = currentY;
                L.e("PullUpFinishLayout", "ACTION_MOVE" + currentX + "," + currentY);
                if (deltaY > deltaX) {
                    return true;
                }

                lastx = currentX;
                lastY = currentY;

                break;


            case MotionEvent.ACTION_CANCEL:
                isCancel = true;
                L.e("PullUpFinishLayout", "isCancel");

                break;

            case MotionEvent.ACTION_UP:

                if (isCancel) {

                    return false;
                }
                lastx = (int) event.getX();
                lastY = (int) event.getY();
                L.e("PullUpFinishLayout", "Math.abs(lastY - downY)=" + Math.abs(lastY - downY) + ",mTouchSlop=" + mTouchSlop);
                if (Math.abs(lastY - downY) > Math.abs(lastx - downx) && Math.abs(lastY - downY) > mTouchSlop * 5) {
                    if (onFinishListener != null) {
                        onFinishListener.onFinish();
                    }
                }

                break;

        }

        return super.onTouchEvent(event);*/
    }


    public interface OnFinishListener {

        void onFinish();
    }

}
