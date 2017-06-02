package cn.elvin.customlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * 箭头动画
 * Created by xiaw on 2016/5/31 0031.
 */
public class ArrowAnimView extends View {


    private Mode mMode = Mode.UP;

    private int mArrowColor = android.R.color.holo_red_dark;

    private Paint mPaint;

    private int mCurrentColorStep = 0;


    private int[] colorList = new int[]{
            R.color.default_arrow_color1,
            R.color.default_arrow_color2,
            R.color.default_arrow_color3,
            R.color.default_arrow_color3
    };

    private int mWidth;
    private int mHalfWidth;
    private int mTotalColor = colorList.length;
    private int mTotalArrow = mTotalColor - 1;

    private int[] mToggleColorList = null;
    private Point mPoint1 = new Point();
    private Point mPoint2 = new Point();
    private Point mPoint3 = new Point();

    private int mPaintWidth = 4;

    private int DURATION = 500;
    private Canvas mCanvas;


    private Handler mH = new Handler();

    private void toggle() {

        if (mToggleColorList == null) {
            mToggleColorList = new int[mTotalColor];
            for (int i = 0; i < mTotalColor; i++) {
                mToggleColorList[i] = colorList[i];
            }
        }

        int tempValue = mToggleColorList[0];
        for (int i = 0; i < mTotalArrow; i++) {
            mToggleColorList[i] = mToggleColorList[i + 1];
        }
        mToggleColorList[mTotalArrow] = tempValue;


//        int tempStep = mCurrentColorStep;//2
//        for (int i = mTotalArrow; i > 0; i--) {
//            tempStep = tempStep % colorList.length;
//            mToggleColorList[tempStep++] = colorList[i - 1];
//            //2-0
//            //0-1
//            //1-2
//        }
//        mCurrentColorStep++;
    }


    public ArrowAnimView(Context context) {
        this(context, null);
    }

    public ArrowAnimView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ArrowAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(mArrowColor));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mPaintWidth);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureWidth * mTotalArrow / 2;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, heightMode);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public Mode getMode() {
        return mMode;
    }

    public void setMode(Mode mMode) {
        this.mMode = mMode;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mWidth = getWidth();
        mHalfWidth = mWidth / 2;
        initPoint();

        if (mWidth > 0) {
            mH.removeCallbacks(invalidateRunnabale);
            mH.postDelayed(invalidateRunnabale, DURATION);
        }
    }

    Runnable invalidateRunnabale = new Runnable() {
        @Override
        public void run() {
            invalidate();
            mH.postDelayed(invalidateRunnabale, DURATION);
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;
        toggle();
        drawAnimStep1(mCanvas);
       // L.e("onDraw", "currentTimeMillis=" + System.currentTimeMillis());
    }


    private void initPoint() {

        mPoint1.set(0, mHalfWidth);
        mPoint2.set(mHalfWidth, 0);
        mPoint3.set(mWidth, mHalfWidth);

    }


    /**
     * 上面深色，下面浅色
     */
    private void drawAnimStep1(Canvas canvas) {

        for (int i = 0; i < mTotalArrow; i++) {
            mPaint.setColor(getResources().getColor(mToggleColorList[i]));
            Path path = new Path();
            path.moveTo(mPoint1.x, mPoint1.y + mHalfWidth * i);
            path.lineTo(mPoint2.x, mPoint2.y + mHalfWidth * i);
            path.lineTo(mPoint3.x, mPoint3.y + mHalfWidth * i);
            canvas.drawPath(path, mPaint);

            // canvas.drawLine(mPoint1.x, mPoint1.y + mHalfWidth * i, mPoint2.x, mPoint2.y + mHalfWidth * i, mPaint);
            // canvas.drawLine(mPoint2.x, mPoint2.y + mHalfWidth * i, mPoint3.x, mPoint3.y + mHalfWidth * i, mPaint);
        }
        // postInvalidateDelayed(DURATION);

//        mPaint.setColor(getResources().getColor(mToggleColorList[0]));
//        canvas.drawLine(mPoint1.x, mPoint1.y, mPoint2.x, mPoint2.y, mPaint);
//        canvas.drawLine(mPoint2.x, mPoint2.y, mPoint3.x, mPoint3.y, mPaint);
//
//        mPaint.setColor(getResources().getColor(mToggleColorList[1]));
//        canvas.drawLine(mPoint1.x, mPoint1.y + mHeight / mTotalArrow, mPoint2.x, mPoint2.y + mHeight / mTotalArrow, mPaint);
//        canvas.drawLine(mPoint2.x, mPoint2.y + mHeight / mTotalArrow, mPoint3.x, mPoint3.y + mHeight / mTotalArrow, mPaint);
//
//        mPaint.setColor(getResources().getColor(mToggleColorList[2]));
//        canvas.drawLine(mPoint1.x, mPoint1.y + mHeight / 2, mPoint2.x, mPoint2.y + mHeight / 2, mPaint);
//        canvas.drawLine(mPoint2.x, mPoint2.y + mHeight / 2, mPoint3.x, mPoint3.y + mHeight / 2, mPaint);


    }


    public enum Mode {
        UP, DOWN
    }
}
