package cn.samir.online.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.samir.online.ui.fragment.BaseLoggerFragment;
import cn.samir.online.views.ToolBar;

/**
 * Created by xiawei on 2017/3/15.
 */

public abstract class BaseFragment extends BaseLoggerFragment {


    public abstract int getLayout();


    protected LinearLayout mToolbar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public LinearLayout getToolbar() {
        return mToolbar;
    }

    public void setToolbar(LinearLayout mToolbar) {
        this.mToolbar = mToolbar;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

//
//    private BroadcastReceiver EventReceive = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            if (intent.getAction().equals(Constant.ACTION_EVENT)) {
//
//                Event event = intent.getParcelableExtra(Constant.ACTION_EVENT_DATA);
//                if (event != null) {
//                    onEvent(event.getData());
//                }
//            }
//
//        }
//    };


    @Override
    public void onResume() {
        super.onResume();

//        IntentFilter actionFileter = new IntentFilter(Constant.ACTION_EVENT);
//        LocalBroadcastManager.getInstance(getContext()).registerReceiver(EventReceive, actionFileter);

    }

    @Override
    public void onPause() {
        super.onPause();

//        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(EventReceive);
    }


    public void onEvent(Object data) {

    }


}
