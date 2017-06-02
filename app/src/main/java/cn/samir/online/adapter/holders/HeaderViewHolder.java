package cn.samir.online.adapter.holders;

import android.view.View;

import com.dinuscxj.pullzoom.PullZoomBaseView;
import com.dinuscxj.pullzoom.PullZoomRecyclerView;

import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.L;
import cn.samir.online.views.HeaderZoomView;

/**
 * Created by xiawei on 2017/3/16.
 */

public class HeaderViewHolder extends CommonViewHolder<Object> implements PullZoomRecyclerView.OnPullZoomListener {

    private PullZoomRecyclerView pullZoomRecyclerView;
    private HeaderZoomView hzv;


    public HeaderViewHolder(View layoutView, PullZoomRecyclerView pullZoomRecyclerView) {
        super(layoutView);
        this.pullZoomRecyclerView = pullZoomRecyclerView;
        this.pullZoomRecyclerView.addOnPullZoomListener(this);
        hzv = (HeaderZoomView) itemView;
        pullZoomRecyclerView.addOnPullZoomListener(hzv);
    }

    @Override
    public void bindData(Object o, int position) {
//        pullZoomRecyclerView.setHeaderContainer(hzv.getZoomContainer());
//        pullZoomRecyclerView.setZoomView(hzv.getZoomView());
        noBackground();
        pullZoomRecyclerView.setHeaderContainer(hzv.getZoomContainer());
        pullZoomRecyclerView.setZoomView(hzv.getZoomView());

    }

    @Override
    public void onPullUp(float newScrollValue) {
        L.e("onPullUp=" + newScrollValue);
    }

    @Override
    public void onPullZooming(float newScrollValue) {

    }

    @Override
    public void onPullStart() {
        L.e("onPullStart");
        itemView.removeCallbacks(changeRunnable);
        hzv.setMode(0);
    }

    @Override
    public void onPullZoomEnd(float newScrollValue) {

        L.e("onPullZoomEnd");
        itemView.postDelayed(changeRunnable, PullZoomBaseView.ZOOM_BACK_DURATION);
    }


    private Runnable changeRunnable = new Runnable() {
        @Override
        public void run() {
            L.e("onPullZoomEnd run");
            hzv.setMode(1);
        }
    };

}
