package cn.samir.online.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.samir.online.util.LogUtil;

import cn.samir.online.util.DensityUtil;
import cn.samir.online.util.L;


public class RPagerSnapHelper extends PagerSnapHelper {

    private OnPageListener mOnPageListener;
    private int mCurrentPosition = 0;
    private static final int DELAY = 2000;
    private RecyclerView recyclerView;
    private boolean autoChange = false;

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public RPagerSnapHelper setCurrentPosition(int currentPosition) {
        mCurrentPosition = currentPosition;
        return this;
    }

    public RPagerSnapHelper(boolean autoChange) {
        this.autoChange = autoChange;
    }

    /**
     * 页面选择回调监听
     */
    public RPagerSnapHelper setOnPageListener(OnPageListener onPageListener) {
        mOnPageListener = onPageListener;
        return this;
    }

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }


    private Runnable directSwitchItemRunnable = new Runnable() {
        @Override
        public void run() {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null) {
                mCurrentPosition = 10000;
                recyclerView.scrollToPosition(mCurrentPosition);
                if (mOnPageListener != null) {
                    mOnPageListener.onPageSelector(mCurrentPosition);
                }
            }
            L.e("directSwitchItemRunnable " + mCurrentPosition);
        }
    };


    private Runnable switchItemRunnable = new Runnable() {
        @Override
        public void run() {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null) {
                recyclerView.smoothScrollToPosition(++mCurrentPosition % adapter.getItemCount());
                if (mOnPageListener != null) {
                    mOnPageListener.onPageSelector(mCurrentPosition);
                }
            }
            if (autoChange) {
                recyclerView.postDelayed(this, DELAY);
            }

            L.e("directSwitchItemRunnable " + mCurrentPosition + "," + recyclerView.hashCode());
        }
    };

    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        View view = super.findSnapView(layoutManager);

        if (view != null) {
            LogUtil.e("RPagerSnapHelper findSnapView layoutManager=" + layoutManager.hashCode() + ",view ="
                    + view.hashCode() + " l =" + view.getLeft() + ",t = " + view.getTop());
        } else {
            LogUtil.e("RPagerSnapHelper findSnapView layoutManager=" + layoutManager.hashCode() + ",view==null");
        }
        return view;
    }

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = super.calculateDistanceToFinalSnap(layoutManager, targetView);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) targetView.getLayoutParams();
        int position = params.getViewAdapterPosition();
        int screenWidth = DensityUtil.getDisplayWidth(targetView.getContext());
        int itemWidth = targetView.getWidth();
        LogUtil.e("RPagerSnapHelper calculateDistanceToFinalSnap screenWidth=" + screenWidth + ",itemWidth=" +
                itemWidth + ",left=" + targetView.getLeft()
                + ".out[0]=" + out[0] + "layoutManager=" + layoutManager.hashCode());
        notifyPageListner(position);
        return out;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        LogUtil.e("RPagerSnapHelper findTargetSnapPosition layoutManager=" + layoutManager.hashCode());
        return super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
    }

    private void notifyPageListner(int position) {
        if (mOnPageListener != null && mCurrentPosition != position) {
            mOnPageListener.onPageSelector(mCurrentPosition = position);
        }
        setCurrentPosition(position);
    }

    public interface OnPageListener {
        void onPageSelector(int position);
    }
}