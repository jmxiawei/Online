package cn.samir.online.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dinuscxj.pullzoom.PullZoomBaseView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.online.R;

/**
 * Created by xiawei on 2017/3/15.
 */

public class HeaderZoomView extends LinearLayout implements PullZoomBaseView.OnPullZoomListener {


    @BindView(R.id.img_header)
    SimpleDraweeView imgHeader;
    @BindView(R.id.tv_notify)
    LoadingAnimView loadingAnimView;
    @BindView(R.id.zoom_container)
    FrameLayout zoomContainer;
    @BindView(R.id.img_icon)
    SimpleDraweeView imgIcon;
    @BindView(R.id.zoom_view)
    RelativeLayout zoomView;

    public static final int PULL_THRESHOLD = 100;
    private LoadingAnimView.Mode mLoadingMode = LoadingAnimView.Mode.TEXT;

    public HeaderZoomView(Context context) {
        this(context, null);
    }

    public HeaderZoomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderZoomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        View v = LayoutInflater.from(context).inflate(R.layout.layout_header_zoom, this, false);
        addView(v);
        ButterKnife.bind(this, this);
        imgHeader.setImageURI("res://cn.samir.online/" + R.mipmap.home_page_header_cover);
        imgIcon.setImageURI("res://cn.samir.online/" + R.mipmap.home_page_header_icon);
    }

    public ViewGroup getZoomContainer() {
        return (ViewGroup) findViewById(R.id.zoom_container);
    }

    public View getZoomView() {
        return findViewById(R.id.zoom_view);
    }


    public void setLoadingMode(LoadingAnimView.Mode mLoadingMode, String text) {
        this.mLoadingMode = mLoadingMode;
        if (this.mLoadingMode == LoadingAnimView.Mode.TEXT) {
            loadingAnimView.setTextMode(text);
        } else {
            loadingAnimView.setMode(mLoadingMode);
        }
    }

    public void setMode(int mode) {
        if (mode == 0) {
            findViewById(R.id.zoom_view).setVisibility(VISIBLE);
        } else {
            findViewById(R.id.zoom_view).setVisibility(INVISIBLE);
        }
    }


    @Override
    public void onPullUp(float newScrollValue) {

    }

    @Override
    public void onPullZooming(float newScrollValue) {
        loadingAnimView.setVisibility(VISIBLE);
        //LogUtil.e("onPullZooming=" + newScrollValue + ",height=" + getHeight());
        loadingAnimView.setCurrentPercent((int) (Math.abs(newScrollValue) / 2));
    }

    @Override
    public void onPullStart() {
        loadingAnimView.setVisibility(VISIBLE);
        loadingAnimView.setMode(LoadingAnimView.Mode.PULL);
    }

    @Override
    public void onPullZoomEnd(float newScrollValue) {
        loadingAnimView.setVisibility(INVISIBLE);
//        if (Math.abs(newScrollValue) > PULL_THRESHOLD) {
//            loadingAnimView.setMode(LoadingAnimView.Mode.REFRESH);
//        } else {
//            loadingAnimView.setMode(LoadingAnimView.Mode.TEXT);
//        }

    }
}
