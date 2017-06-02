package cn.samir.online.ui.fragment;

import android.support.v4.app.Fragment;

import cn.elvin.customlib.L;


/**
 * Created by xiaw on 2017/4/17 0017.
 */

public  class BaseLoggerFragment extends Fragment {


    protected String getLogTag() {
        return getClass().getSimpleName();
    }




    protected void log(String str) {
        L.e(getLogTag(), str);
    }

}
