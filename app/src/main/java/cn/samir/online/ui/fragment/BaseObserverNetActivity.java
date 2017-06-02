package cn.samir.online.ui.fragment;

import android.content.IntentFilter;
import android.net.ConnectivityManager;

import cn.samir.online.receivers.NetChangedBroadcastReceiver;
import cn.samir.online.ui.base.BaseActivity;

/**
 * Created by xiaw on 2017/4/18 0018.
 */

public class BaseObserverNetActivity extends BaseActivity implements NetChangedBroadcastReceiver.OnNetChangedListener {



    private NetChangedBroadcastReceiver netChangedBroadcastReceiver = new NetChangedBroadcastReceiver();


    protected void addOnNetChangedListener(NetChangedBroadcastReceiver.OnNetChangedListener listener) {
        netChangedBroadcastReceiver.addOnNetChangedListener(listener);
    }


    protected void removeOnNetChangedListener(NetChangedBroadcastReceiver.OnNetChangedListener listener) {
        netChangedBroadcastReceiver.removeOnNetChangedListener(listener);
    }


    @Override
    protected void onResume() {
        super.onResume();

        netChangedBroadcastReceiver.addOnNetChangedListener(this);
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netChangedBroadcastReceiver, intentFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(netChangedBroadcastReceiver);
    }


    @Override
    public void onNetChanged(int status) {

    }
}
