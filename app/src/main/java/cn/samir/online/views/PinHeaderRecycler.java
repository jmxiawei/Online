package cn.samir.online.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by xiaw on 2017/5/19 0019.
 */

public class PinHeaderRecycler extends RecyclerView {

    public PinHeaderRecycler(Context context) {
        this(context, null);
    }

    public PinHeaderRecycler(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PinHeaderRecycler(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}
