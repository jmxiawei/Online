package cn.samir.online.views.tablayout;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import cn.samir.online.util.DensityUtil;
import cn.samir.online.util.LogUtil;

/**
 * Created by xiaw on 2017/5/23 0023.
 */

public class LoopLinearLayoutManager extends RecyclerView.LayoutManager {


    private int mWidth;
    private int mHeight;


    private boolean loop;
    private int mTotalWidth = 0;
    private int screenWidth;


    private Context context;


    private int horizonalScrollOffset = 0;

    //所有item的位置信息
    private SparseArray<Rect> allItemFrames = new SparseArray<>();


    private SparseBooleanArray hasAttachedItems = new SparseBooleanArray();


    private OrientationHelper mOrientationHelper;

    public LoopLinearLayoutManager(Context context, boolean loop) {
        this.loop = loop;
        this.context = context;
        screenWidth = DensityUtil.getDisplayWidth(context);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (state.isPreLayout()) {
            return;
        }
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            return;
        }

        detachAndScrapAttachedViews(recycler);
        //别用layoutmanager的getItemCount
        int size = state.getItemCount();
        LogUtil.e("itemCount1=" + size);
        mTotalWidth = 0;
        int offsetX = 0;
        for (int i = 0; i < size; i++) {
            View view = recycler.getViewForPosition(i);
            measureChildWithMargins(view, 0, 0);
            mWidth = getDecoratedMeasuredWidth(view);
            mHeight = getDecoratedMeasuredHeight(view);
            mTotalWidth += mWidth;
            offsetX += mWidth;
            Rect frame = allItemFrames.get(i);
            if (frame == null) {
                frame = new Rect();
            }
            frame.set(offsetX, 0, offsetX + mWidth, mHeight);
            LogUtil.e(frame);
            allItemFrames.put(i, frame);
            hasAttachedItems.put(i, false);
            addView(view);

            layoutDecorated(view, frame.left, frame.top,
                    frame.right, frame.bottom);

        }
        mTotalWidth = Math.max(mTotalWidth, getHorizontalSpace());
        //recyclerAllItems(recycler, state);

    }

    private void recyclerAllItems(RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (state.isPreLayout()) {
            return;
        }
        Rect showRect = new Rect(horizonalScrollOffset, 0, horizonalScrollOffset + getHorizontalSpace(), getVerticalSpace());
        LogUtil.e(showRect);
        Rect childFrame = new Rect();
        int count = getChildCount();
        LogUtil.e(" child count =" + count);
//        for (int i = 0; i < count; i++) {
//            View view = getChildAt(i);
//            childFrame.left = getDecoratedLeft(view);
//            childFrame.right = getDecoratedRight(view);
//            childFrame.top = getDecoratedTop(view);
//            childFrame.bottom = getDecoratedBottom(view);
//            LogUtil.e(childFrame);
//            if (!showRect.intersect(childFrame)) {
//                //与现实区域没有交集，移除
//                removeAndRecycleView(view, recycler);
//            }
//        }


        int itemCount = state.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            Rect frame = allItemFrames.get(i);
            if (Rect.intersects(showRect, frame)) {
                View view = recycler.getViewForPosition(i);
                addView(view);
                layoutDecorated(view, frame.left - horizonalScrollOffset, frame.top,
                        frame.right - horizonalScrollOffset, frame.bottom);
            } else {

            }
        }


    }


    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {

        int traval = dx;
        detachAndScrapAttachedViews(recycler);
        ensureLayoutState();
        horizonalScrollOffset += dx;
        mOrientationHelper.offsetChildren(-dx);
        recyclerAllItems(recycler, state);
        LogUtil.e(" childcount =" + getChildCount());
        return traval;
    }


    void ensureLayoutState() {
        if (mOrientationHelper == null) {
            mOrientationHelper = OrientationHelper.createOrientationHelper(this, OrientationHelper.HORIZONTAL);
        }
    }


    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

}
