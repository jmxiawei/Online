package cn.samir.online.adapter.holders.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.List;

import butterknife.BindView;
import cn.samir.domains.model.BaseModel;
import cn.samir.online.R;
import cn.samir.online.adapter.BaseLoopRecyclerAdapter;
import cn.samir.online.adapter.RPagerSnapHelper;
import cn.samir.online.util.DensityUtil;
import cn.samir.online.util.LogUtil;
import cn.samir.online.views.IndicatorView;
import cn.samir.online.views.PinHeaderRecycler;

/**
 * Created by xiaw on 2017/5/15 0015.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public abstract class CommonRecyclerPagerHolder<T> extends CommonDividerHolder<T> implements RPagerSnapHelper.OnPageListener, ViewTreeObserver.OnGlobalLayoutListener {


    protected static final int INVALID = -1;
    @BindView(R.id.recycler_view)
    protected PinHeaderRecycler recyclerView;
    @BindView(R.id.indicator_view)
    protected IndicatorView indicatorView;

    //protected RPagerSnapHelper mRPagerSnapHelper;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected BaseLoopRecyclerAdapter mBaseLoopRecyclerAdapter;

    protected boolean isLayout = false;

    private int mTotalDataSize = INVALID;


    public CommonRecyclerPagerHolder(Context context, ViewGroup root, int layoutRes) {
        super(context, root, layoutRes);


        ////mRPagerSnapHelper = getRPagerSnapHelper();
        //mLayoutManager = new LoopLinearLayoutManager(context,true);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public void onLayoutCompleted(RecyclerView.State state) {
                super.onLayoutCompleted(state);
                if ((mItemSettings == null || mItemSettings.isSnapHelper())) {
                    getRPagerSnapHelper().attachToRecyclerView(recyclerView);
                }
                LogUtil.e(CommonRecyclerPagerHolder.this.hashCode() + "onLayoutCompleted=" + SystemClock.elapsedRealtimeNanos());
            }
        };

        //ReflectUtils.setFieldBooleanValue(mLayoutManager,"DEBUG",true);

        // mRPagerSnapHelper.setOnPageListener(this);
        // recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(this);

        recyclerView.setLayoutManager(mLayoutManager);
        mBaseLoopRecyclerAdapter = getBaseLoopRecyclerAdapter();
        recyclerView.setAdapter(mBaseLoopRecyclerAdapter);

    }

    @Override
    public void bindData(T t, int position) {

    }

    private RPagerSnapHelper getRPagerSnapHelper() {
        RPagerSnapHelper p = new RPagerSnapHelper(false);
        p.setOnPageListener(this);
        recyclerView.setOnFlingListener(null);
        return p;
    }

    public abstract BaseLoopRecyclerAdapter getBaseLoopRecyclerAdapter();


    protected void setPagerData(List<BaseModel> datas) {
        LogUtil.e(CommonRecyclerPagerHolder.this.hashCode() + "bindData=" + SystemClock.elapsedRealtimeNanos());
        isLayout = false;
        mBaseLoopRecyclerAdapter.setDataList(datas);
        setTotalDataSize(datas.size());
        indicatorView.setTotalCount(datas.size());
        int to = 10000 * mBaseLoopRecyclerAdapter.getDataSize();
        recyclerView.scrollToPosition(to);
        indicatorView.setCurrentPosition(to);

    }

    public int getTotalDataSize() {

        if (mTotalDataSize == INVALID) {
            throw new NullPointerException("mTotalDataSize should set before use");
        }
        return mTotalDataSize;
    }

    public void setTotalDataSize(int mTotalDataSize) {

        if (mTotalDataSize < 0) {
            throw new NullPointerException("mTotalDataSize should be > 0");
        }
        this.mTotalDataSize = mTotalDataSize;
    }

    @Override
    public void onPageSelector(final int position) {
        itemView.postDelayed(new Runnable() {
            @Override
            public void run() {
                indicatorView.setCurrentPosition(position % getTotalDataSize());
            }
        }, 100);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onGlobalLayout() {


        if (!isLayout) {
            isLayout = true;
            int gap = DensityUtil.dip2px(getContext(), getContext().getResources().getDimension(R.dimen.video_collection_item_margin));
            recyclerView.smoothScrollBy(-gap, 0);
        }
    }
}
