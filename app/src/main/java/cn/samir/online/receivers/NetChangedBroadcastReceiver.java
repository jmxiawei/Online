package cn.samir.online.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

/**
 * Created by xiaw on 2017/4/17 0017.
 */

public class NetChangedBroadcastReceiver extends BroadcastReceiver {

    private ConnectivityManager connectivityManager;
    // Describes the status of a network interface.
    // 网络状态信息的实例
    private NetworkInfo info;

    private ArrayList<OnNetChangedListener> listeners = new ArrayList<>();


    public void addOnNetChangedListener(OnNetChangedListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }


    public void removeOnNetChangedListener(OnNetChangedListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }


    /**
     * 当前处于的网络
     * 0 ：null
     * 1 ：2G/3G
     * 2 ：wifi
     */
    public int networkStatus;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

            // 获取网络连接管理器
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            // 获取当前网络状态信息
            info = connectivityManager.getActiveNetworkInfo();

            if (info != null && info.isAvailable()) {

                //当NetworkInfo不为空且是可用的情况下，获取当前网络的Type状态
                //根据NetworkInfo.getTypeName()判断当前网络
                String name = info.getTypeName();

                //更改NetworkStateService的静态变量，之后只要在Activity中进行判断就好了
                if (name.equals("WIFI")) {
                    networkStatus = 2;
                } else {
                    networkStatus = 1;
                }

            }

            if (networkStatus > 0) {

                notifyNetChangedListeners();
            }

        }
    }

    private void notifyNetChangedListeners() {
        if (listeners != null) {
            for (OnNetChangedListener l :
                    listeners) {
                if (l != null) {
                    l.onNetChanged(networkStatus);
                }
            }
        }
    }

    public interface OnNetChangedListener {
        void onNetChanged(int status);
    }
}
