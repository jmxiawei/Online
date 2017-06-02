package cn.samir.online.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.samir.online.R;

/**
 * Created by xiaw on 2017/4/13 0013.
 */

public class IndicatorView extends View {

    private int totalCount = 5;
    private int currentPosition = 1;
    private Paint paint;

    private int circleRadius;//直径
    private int itemGap = 20;


    private int height = 0;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition % totalCount;
        postInvalidate();
    }

    private
    @ColorRes
    int selectColor = R.color.transparent_aa;
    private
    @ColorRes
    int normalColor = R.color.transparent_33;

    public int getSelectColor() {
        return selectColor;
    }

    public void setSelectColor(@ColorRes int selectColor) {
        this.selectColor = selectColor;
    }

    public int getNormalColor() {
        return normalColor;
    }

    public void setNormalColor(@ColorRes int normalColor) {
        this.normalColor = normalColor;
    }

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        height = getHeight();
        circleRadius = height / 3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCycle(canvas);
    }

    private void drawCycle(Canvas canvas) {
        int x = (getWidth() - totalCount * circleRadius - (totalCount - 1) * itemGap) / 2;
        int y = height / 2;
        for (int i = 0; i < totalCount; i++) {
            if (currentPosition == i) {
                paint.setColor(getResources().getColor(selectColor));
                canvas.drawCircle(x + circleRadius * i + itemGap * i, y, circleRadius, paint);
            } else {
                paint.setColor(getResources().getColor(normalColor));
                canvas.drawCircle(x + circleRadius * i + itemGap * i, y, circleRadius, paint);
            }
        }
    }

}
