package cn.samir.online.http.downloads;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/** 查詢下載進度 系统的downloadManager
 * Created by xiaw on 2017/5/16 0016.
 */

public class ScheduledExecutorServiceImp {


    private static ScheduledExecutorServiceImp instance;

    private ScheduledExecutorService scheduledExecutorServiceImp;




    private ScheduledExecutorServiceImp() {
        scheduledExecutorServiceImp = Executors.newScheduledThreadPool(1);

    }


    public static ScheduledExecutorServiceImp getInstance() {

        if (instance == null) {
            synchronized (ScheduledExecutorServiceImp.class) {
                if (instance == null) {
                    instance = new ScheduledExecutorServiceImp();
                }
            }
        }
        return instance;
    }

    /**
     * @param runnable
     * @param timeSecDelay
     */
    public ScheduledFuture scheduleFix(Runnable runnable, int timeSecDelay,TimeUnit timeUnit) {
        return scheduledExecutorServiceImp.scheduleAtFixedRate(runnable, 0, timeSecDelay, timeUnit);
    }

    /**
     * @param runnable
     * @param timeSecDelay
     */
    public ScheduledFuture scheduleFixMils(Runnable runnable, int timeSecDelay) {
        return scheduledExecutorServiceImp.scheduleAtFixedRate(runnable, 0, timeSecDelay, TimeUnit.MILLISECONDS);
    }


}
