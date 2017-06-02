package cn.samir.online.adapter.decorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.samir.online.util.LogUtil;

import cn.samir.online.adapter.holders.SquareCardHolder;

/**
 * Created by xiawei on 2017/5/6.
 */

public class SquareCardRightDecorations extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.ViewHolder holder = parent.getChildViewHolder(view);
        if (holder instanceof SquareCardHolder) {
            outRect.set(0, 0, 4, 0);
            //LogUtil.e("SquareCardRightDecorations SquareCardHolder");
        }

    }
}
