package cn.samir.online.util;

import cn.samir.online.R;

/**
 * Created by xiaw on 2017/4/10 0010.
 */

public class MsgUtils {


    public static int getNotifyMessage(Object p) {
        L.e("MsgUtils", p.toString());

        if(p instanceof Exception){
            L.e(((Exception) p).getMessage());
        }

        return R.string.error_unknow;


    }





    public static int getNotifyMessage(int code) {

        L.e("MsgUtils", code);
        if (code == Constant.ERROR_CODE_NET) {
            return R.string.error_net;
        }

        return R.string.error_unknow;


    }
}
