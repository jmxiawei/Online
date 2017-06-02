//package cn.samir.online.views.tablayout;
//
//import android.animation.Animator;
//import android.animation.ValueAnimator;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.support.v4.view.ViewCompat;
//import android.support.v4.view.animation.FastOutSlowInInterpolator;
//import android.view.Gravity;
//import android.view.View;
//import android.widget.LinearLayout;
//
//import cn.samir.online.util.DensityUtil;
//
///**
// * Created by xiaw on 2017/5/10 0010.
// */
//public class SlidingTabStrip extends LinearLayout {
//    private int mSelectedIndicatorHeight;
//    private final Paint mSelectedIndicatorPaint;
//
//    static final int FIXED_WRAP_GUTTER_MIN = 16; //dps
//    static final int MOTION_NON_ADJACENT_OFFSET = 24;
//
//    int mSelectedPosition = -1;
//    float mSelectionOffset;
//
//    private int mIndicatorLeft = -1;
//    private int mIndicatorRight = -1;
//
//    public static final int MODE_FIXED = 1;
//    private int mMode = MODE_FIXED;
//    private int mTabGravity = Gravity.CENTER;
//    private ValueAnimator mIndicatorAnimator;
//
//    SlidingTabStrip(Context context) {
//        super(context);
//        setWillNotDraw(false);
//        mSelectedIndicatorPaint = new Paint();
//    }
//
//    void setSelectedIndicatorColor(int color) {
//        if (mSelectedIndicatorPaint.getColor() != color) {
//            mSelectedIndicatorPaint.setColor(color);
//            ViewCompat.postInvalidateOnAnimation(this);
//        }
//    }
//
//    void setSelectedIndicatorHeight(int height) {
//        if (mSelectedIndicatorHeight != height) {
//            mSelectedIndicatorHeight = height;
//            ViewCompat.postInvalidateOnAnimation(this);
//        }
//    }
//
//    boolean childrenNeedLayout() {
//        for (int i = 0, z = getChildCount(); i < z; i++) {
//            final View child = getChildAt(i);
//            if (child.getWidth() <= 0) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    void setIndicatorPositionFromTabPosition(int position, float positionOffset) {
//        if (mIndicatorAnimator != null && mIndicatorAnimator.isRunning()) {
//            mIndicatorAnimator.cancel();
//        }
//
//        mSelectedPosition = position;
//        mSelectionOffset = positionOffset;
//        updateIndicatorPosition();
//    }
//
//    float getIndicatorPosition() {
//        return mSelectedPosition + mSelectionOffset;
//    }
//
//    @Override
//    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY) {
//            // HorizontalScrollView will first measure use with UNSPECIFIED, and then with
//            // EXACTLY. Ignore the first call since anything we do will be overwritten anyway
//            return;
//        }
//
//        if (mMode == MODE_FIXED && mTabGravity == Gravity.CENTER) {
//            final int count = getChildCount();
//
//            // First we'll find the widest tab
//            int largestTabWidth = 0;
//            for (int i = 0, z = count; i < z; i++) {
//                View child = getChildAt(i);
//                if (child.getVisibility() == VISIBLE) {
//                    largestTabWidth = Math.max(largestTabWidth, child.getMeasuredWidth());
//                }
//            }
//
//            if (largestTabWidth <= 0) {
//                // If we don't have a largest child yet, skip until the next measure pass
//                return;
//            }
//
//            final int gutter = DensityUtil.dip2px(getContext(), FIXED_WRAP_GUTTER_MIN);
//            boolean remeasure = false;
//
//            if (largestTabWidth * count <= getMeasuredWidth() - gutter * 2) {
//                // If the tabs fit within our width minus gutters, we will set all tabs to have
//                // the same width
//                for (int i = 0; i < count; i++) {
//                    final LinearLayout.LayoutParams lp =
//                            (LayoutParams) getChildAt(i).getLayoutParams();
//                    if (lp.width != largestTabWidth || lp.weight != 0) {
//                        lp.width = largestTabWidth;
//                        lp.weight = 0;
//                        remeasure = true;
//                    }
//                }
//            }
//
//        }
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//
//        if (mIndicatorAnimator != null && mIndicatorAnimator.isRunning()) {
//            // If we're currently running an animation, lets cancel it and start a
//            // new animation with the remaining duration
//            mIndicatorAnimator.cancel();
//            final long duration = mIndicatorAnimator.getDuration();
//            animateIndicatorToPosition(mSelectedPosition,
//                    Math.round((1f - mIndicatorAnimator.getAnimatedFraction()) * duration));
//        } else {
//            // If we've been layed out, update the indicator position
//            updateIndicatorPosition();
//        }
//    }
//
//    private void updateIndicatorPosition() {
//        final View selectedTitle = getChildAt(mSelectedPosition);
//        int left, right;
//
//        if (selectedTitle != null && selectedTitle.getWidth() > 0) {
//            left = selectedTitle.getLeft();
//            right = selectedTitle.getRight();
//
//            if (mSelectionOffset > 0f && mSelectedPosition < getChildCount() - 1) {
//                // Draw the selection partway between the tabs
//                View nextTitle = getChildAt(mSelectedPosition + 1);
//                left = (int) (mSelectionOffset * nextTitle.getLeft() +
//                        (1.0f - mSelectionOffset) * left);
//                right = (int) (mSelectionOffset * nextTitle.getRight() +
//                        (1.0f - mSelectionOffset) * right);
//            }
//        } else {
//            left = right = -1;
//        }
//
//        setIndicatorPosition(left, right);
//    }
//
//    void setIndicatorPosition(int left, int right) {
//        if (left != mIndicatorLeft || right != mIndicatorRight) {
//            // If the indicator's left/right has changed, invalidate
//            mIndicatorLeft = left;
//            mIndicatorRight = right;
//            ViewCompat.postInvalidateOnAnimation(this);
//        }
//    }
//
//    void animateIndicatorToPosition(final int position, int duration) {
//        if (mIndicatorAnimator != null && mIndicatorAnimator.isRunning()) {
//            mIndicatorAnimator.cancel();
//        }
//
//        final boolean isRtl = ViewCompat.getLayoutDirection(this)
//                == ViewCompat.LAYOUT_DIRECTION_RTL;
//
//        final View targetView = getChildAt(position);
//        if (targetView == null) {
//            // If we don't have a view, just update the position now and return
//            updateIndicatorPosition();
//            return;
//        }
//
//        final int targetLeft = targetView.getLeft();
//        final int targetRight = targetView.getRight();
//        final int startLeft;
//        final int startRight;
//
//        if (Math.abs(position - mSelectedPosition) <= 1) {
//            // If the views are adjacent, we'll animate from edge-to-edge
//            startLeft = mIndicatorLeft;
//            startRight = mIndicatorRight;
//        } else {
//            // Else, we'll just grow from the nearest edge
//            final int offset = DensityUtil.dip2px(getContext(), MOTION_NON_ADJACENT_OFFSET);
//            if (position < mSelectedPosition) {
//                // We're going end-to-start
//                if (isRtl) {
//                    startLeft = startRight = targetLeft - offset;
//                } else {
//                    startLeft = startRight = targetRight + offset;
//                }
//            } else {
//                // We're going start-to-end
//                if (isRtl) {
//                    startLeft = startRight = targetRight + offset;
//                } else {
//                    startLeft = startRight = targetLeft - offset;
//                }
//            }
//        }
//
//        if (startLeft != targetLeft || startRight != targetRight) {
//            ValueAnimator animator = mIndicatorAnimator = new ValueAnimator();
//            animator.setInterpolator(new FastOutSlowInInterpolator());
//            animator.setDuration(duration);
//            animator.setFloatValues(0, 1);
//            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animator) {
//                    final float fraction = animator.getAnimatedFraction();
//                    setIndicatorPosition(
//                            DensityUtil.lerp(startLeft, targetLeft, fraction),
//                            DensityUtil.lerp(startRight, targetRight, fraction));
//                }
//            });
//            animator.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mSelectedPosition = position;
//                    mSelectionOffset = 0f;
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//
//                }
//            });
//            animator.start();
//        }
//    }
//
//    @Override
//    public void draw(Canvas canvas) {
//        super.draw(canvas);
//
//        // Thick colored underline below the current selection
//        if (mIndicatorLeft >= 0 && mIndicatorRight > mIndicatorLeft) {
//            canvas.drawRect(mIndicatorLeft, getHeight() - mSelectedIndicatorHeight,
//                    mIndicatorRight, getHeight(), mSelectedIndicatorPaint);
//        }
//    }
//}
