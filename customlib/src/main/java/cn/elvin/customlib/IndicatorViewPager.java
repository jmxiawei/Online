package cn.elvin.customlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by xiawei on 2017/3/19.
 */
public class IndicatorViewPager extends ViewPager {

    private Paint paint;


    private float mIndicatorMarginBottomDp = 20;

    private float mIndicatorMarginBottomPx;

    public IndicatorViewPager(Context context) {
        this(context, null);
    }

    public IndicatorViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IndicatorViewPager);

        if (a != null) {
            mIndicatorMarginBottomDp = a.getDimension(R.styleable.IndicatorViewPager_indicator_margin_bottom, 20.0f);
            mIndicatorMarginBottomPx = dip2px(context, mIndicatorMarginBottomDp);
            Log.e("IndicatorViewPager", "mIndicatorMarginBottom=" + mIndicatorMarginBottomDp);
            a.recycle();
        }

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);


        //  gestureDetector = new GestureDetector(context, onGestureListener);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCycle(canvas);
    }

    private void drawCycle(Canvas canvas) {
        canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        int count = 0;
        if (this.getAdapter() != null) {
            count = this.getAdapter().getCount();
        }
        int select = getCurrentItem();
        float density = getContext().getResources().getDisplayMetrics().density;
        int itemWidth = (int) (11 * density);
        int itemHeight = itemWidth / 2;
        int x = (getWidth() - count * itemWidth) / 2;
        int y = getHeight() - (int) mIndicatorMarginBottomPx;
        int minItemHeight = (int) ((float) itemHeight * 0.8F);

        for (int i = 0; i < count; i++) {
            if (select == i) {
                paint.setColor(getResources().getColor(R.color.default_arrow_color1));
                canvas.drawCircle(x + itemWidth * i + itemWidth / 2, y, minItemHeight, paint);
            } else {
                paint.setColor(getResources().getColor(R.color.default_arrow_color2));
                canvas.drawCircle(x + itemWidth * i + itemWidth / 2, y, minItemHeight, paint);
            }
        }

        canvas.restore();
    }


}
