package cn.samir.online.patchs;

import com.meituan.robust.Patch;
import com.meituan.robust.RobustCallBack;

import java.util.List;

/**
 * Created by xiaw on 2017/5/25 0025.
 */

public class PatchCallBack implements RobustCallBack {

    @Override
    public void onPatchListFetched(boolean result, boolean isNet, List<Patch> patches) {

    }

    @Override
    public void onPatchFetched(boolean result, boolean isNet, Patch patch) {

    }

    @Override
    public void onPatchApplied(boolean result, Patch patch) {

    }

    @Override
    public void logNotify(String log, String where) {

    }

    @Override
    public void exceptionNotify(Throwable throwable, String where) {

    }
}
