package cn.samir.online;

import android.app.Application;
import android.graphics.Bitmap;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.orm.SugarContext;

import butterknife.ButterKnife;
import cn.samir.domains.model.AppInfo;
import cn.samir.online.views.TypeFaceManager;

/**
 * Created by xiawei on 2017/3/8.
 */

public class OnlineApplication extends Application {


    public static OnlineApplication instance;

    public static OnlineApplication getInstance() {
        return instance;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        instance = this;
        ButterKnife.setDebug(true);
        initFresco();
        TypeFaceManager.init(this);
        Crash.getInstance().init(this);
        AppInfo.init(this);
    }

    private void initFresco() {
        ImagePipelineConfig.Builder builder = ImagePipelineConfig.newBuilder(this);
        builder.setBitmapsConfig(Bitmap.Config.RGB_565);
        builder.setResizeAndRotateEnabledForNetwork(true);
        builder.setDownsampleEnabled(true);
        Fresco.initialize(this, builder.build());
    }
}
