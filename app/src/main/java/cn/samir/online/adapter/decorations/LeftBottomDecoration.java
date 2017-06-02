package cn.samir.online.adapter.decorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.samir.online.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

import cn.samir.online.adapter.AllCategoriesAdapter;
import cn.samir.online.adapter.holders.RectangleCardHolder;
import cn.samir.online.adapter.holders.SquareCardHolder;

/** lineDecoration for SquaredCardHolder
 * Created by xiaw on 2017/5/5 0005.
 */
public class LeftBottomDecoration extends RecyclerView.ItemDecoration {


    private int mDivider = AllCategoriesAdapter.DIVIDER_WIDTH;

//
//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//
//        final int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            final View child = parent.get(i);
//            //获得child的布局信息
//            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//            final int left = child.getRight() + params.rightMargin;
//            final RecyclerView.ViewHolder holder = parent.getChildViewHolder(child);
//            final int position = parent.getChildLayoutPosition(child);
//            if (holder instanceof SquareCardHolder) {
//                if (left == 0) {
//                    caches.put(position, RIGHT);
//                } else {
//                    caches.put(position, LEFT);
//                }
//            }
//        }
//    }


    boolean isCard = false;
    HashMap<Integer, Integer> beforeMeRectCardSize = new HashMap<>();

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final RecyclerView.ViewHolder holder = parent.getChildViewHolder(view);
        int position = parent.getChildAdapterPosition(view);
        if (holder instanceof SquareCardHolder) {
            if ((position - getRectSize(position)) % 2 == 0) {
                outRect.set(0, 0, mDivider / 2, mDivider);
            } else {
                outRect.set(mDivider / 2, 0, 0, mDivider);
            }
            isCard = true;
        } else {
            beforeMeRectCardSize.put(position, 1);
            isCard = false;
            outRect.set(0, 0, 0, mDivider);
        }
        //LogUtil.e((isCard ? "SquareCardHolder" : "RectangleCardHolder") + " mRectCardSize=" + beforeMeRectCardSize);
    }

    /**
     * how many  {@link RectangleCardHolder}  before this {@link SquareCardHolder}
     * {@link RectangleCardHolder} has 2 span ,{@link SquareCardHolder} has 1 span
     *
     * @param position
     * @return
     */
    private int getRectSize(int position) {

        int size = 0;
        for (Map.Entry<Integer, Integer> ppp : beforeMeRectCardSize.entrySet()) {
            int k = ppp.getKey();
            int v = ppp.getValue();
            if (k < position && v == 1) {
                size++;
            }
        }
        return size;
    }
}
