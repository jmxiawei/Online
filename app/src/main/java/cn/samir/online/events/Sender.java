package cn.samir.online.events;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;

import cn.samir.online.util.Constant;

/**
 * Created by xiaw on 2017/5/26 0026.
 */

public class Sender {
    /**
      * 发送一个广播  ACTION_EVENT
     */
    public static void send(Context context, Parcelable data) {

        if (data != null) {
            Intent event = new Intent(Constant.ACTION_EVENT);
            event.putExtra(Constant.ACTION_EVENT_DATA, data);
            LocalBroadcastManager.getInstance(context).sendBroadcast(event);
        }

    }
}
