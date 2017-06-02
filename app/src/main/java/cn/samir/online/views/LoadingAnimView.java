package cn.samir.online.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;

import cn.elvin.customlib.L;

/**
 * Created by xiaw on 2015/9/18 0018.
 */
public class LoadingAnimView extends CustomFontTextView {

    public int mPointColor = android.R.color.white;
    private Paint mPaint;


    private int mRepeatInterval = 70;
    private int repeatTimes = 6;
    private int mWidth;
    private int mHeight;

    /**
     * 球的最小直径
     */
    private int mPointMinWidth = 20;
    /**
     * 球的直径
     */
    private int mPointWidth = 20;
    /**
     * 周围空白区域
     */
    private int mPadding = 10;

    public int mPointCount = 4;
    public int mPointStartPosition;
    public int mTotalMoveLength;
    public float[] mPointPositions;
    public float[] mPointAlphas;
    public float[] mPointWidths;

    private Mode mMode = Mode.TEXT;
    private String mText;
    private int currentPercent = 0;


    public LoadingAnimView(Context context) {
        this(context, null);
    }

    public LoadingAnimView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(mPointColor));
        mPaint.setStyle(Paint.Style.FILL);
    }

    public Mode getMode() {
        return mMode;
    }

    public void setMode(Mode mMode) {
        this.mMode = mMode;
        L.e("setMode=" + mMode.name());
        invalidate();
    }

    public void setTextMode(String text) {
        this.mMode = Mode.TEXT;
        this.mText = text;

        L.e("setMode=" + mMode.name());

        setText(mText);
        //invalidate();
    }


    public void setCurrentPercent(int currentPercent) {
        this.currentPercent = currentPercent;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();

        if (mWidth > 0 && mHeight > 0) {
            /**
             * 如果宽度不够，减少球的个数
             */
            if ((mWidth > mPadding * 2) && (mWidth - mPadding * 2) / mPointCount < mPointMinWidth) {
                mPointCount = (mWidth - mPadding * 2) / mPointCount;
                mPointWidth = (mWidth - mPadding * 2) / mPointCount;
            } else {
                // 球的直径为高度
                mPointWidth = (mHeight - mPadding / 2);
            }
            mPointWidth = 16;
            mPointPositions = new float[mPointCount];
            mPointAlphas = new float[mPointCount];
            mPointWidths = new float[mPointCount];
            //其实位置
            mPointStartPosition = (mWidth - mPadding * 2 - 2 * mPointWidth) / 4;
            mTotalMoveLength = mPointStartPosition;
            initPosition();
            mPaint.setStrokeWidth(mPointWidth);
        }
    }

    private boolean isDebug = false;

    @Override
    protected void onDraw(Canvas canvas) {


        int cy = mHeight / 2;
        if (mMode == Mode.TEXT) {
            super.onDraw(canvas);
        } else if (mMode == Mode.REFRESH) {

            // canvas.drawColor(getResources().getColor(R.color.color_white));
            for (int i = 0; i < mPointCount; i++) {
                if (isDebug) {
                    mPaint.setColor(getResources().getColor(mPointColor));
                }
                float alpha = mPointAlphas[i];
                if (mPointAlphas[i] > 255) {
                    alpha = 0xff;
                } else if (mPointAlphas[i] < 0) {
                    alpha = 0;
                }
                mPaint.setAlpha((int) alpha);
                canvas.drawCircle(mPointPositions[i], cy, mPointWidths[i] / 2, mPaint);
                if (isDebug) {
                    mPaint.setColor(Color.BLACK);
                    mPaint.setTextSize(mPointWidth / 2);
                    canvas.drawText(String.valueOf(i), mPointPositions[i], cy, mPaint);
                }
            }
            //变换位置
            updatePointPosition();
        } else if (mMode == Mode.PULL) {
            int x = (getWidth() - mPointCount * mPointWidth - (mPointCount - 1) * mPadding) / 2;
            int size = (currentPercent / 25) + 1;
            if (size > mPointCount) {
                size = mPointCount;
            }
            for (int i = 0; i < size; i++) {
                mPaint.setColor(getResources().getColor(mPointColor));
                canvas.drawCircle(x + mPointWidth * i + mPadding * i, cy, mPointWidth / 2, mPaint);
            }
        }


    }

    /**
     * 初始化位置
     */
    private void initPosition() {
        //初始位置
        mPointPositions[0] = mPointStartPosition;
        mPointAlphas[0] = 25;
        mPointWidths[0] = 0.5f * mPointWidth;
        mPointPositions[1] = mPointStartPosition + mTotalMoveLength + mPadding / 2;
        mPointAlphas[1] = 255;
        mPointWidths[1] = 1.0f * mPointWidth;
        mPointPositions[2] = mPointPositions[1] + mPointWidth + mPadding;
        mPointAlphas[2] = 255;
        mPointWidths[2] = 1.0f * mPointWidth;
        mPointPositions[3] = mPointPositions[2] + mPointWidth + mPadding;
        mPointAlphas[3] = 240;
        mPointWidths[3] = 0.95f * mPointWidth;
    }

    /**
     * 第一个球在一个循环内移动到第二个球左边
     * 循环控制
     */
    private void updatePointPosition() {
        //int repeatTimes = (mRepeatTime / mRepeatInterval);
        mPointPositions[0] += (mTotalMoveLength) / repeatTimes;
        mPointAlphas[0] += (255 - 25) / repeatTimes;
        mPointWidths[0] += 0.5 * mPointWidth / repeatTimes;

        mPointPositions[3] += (mTotalMoveLength) / repeatTimes;
        mPointAlphas[3] -= 240 / repeatTimes;
        mPointWidths[3] -= 0.5 * mPointWidth / repeatTimes;

        mPointPositions[1] += mPointWidth / repeatTimes;
        mPointAlphas[1] -= 1;
        mPointPositions[2] += mPointWidth / repeatTimes;
//        if (isDebug) {
//            LogUtil.d(mPointAlphas[0] + "," + mPointAlphas[1] + "," + mPointAlphas[2] + "," + mPointAlphas[3]);
//            LogUtil.d("width=" + mPointWidth + "," + mPointPositions[0] + "," + mPointPositions[1] + "," + mPointPositions[2] + "," + mPointPositions[3]);
//        }
        if (mPointPositions[0] >= mPointPositions[1] - mPointWidth) {
            initPosition();

        }
        //变化一次间隔
        postInvalidateDelayed(mRepeatInterval);

    }


    public enum Mode {
        PULL, REFRESH, TEXT
    }
}
