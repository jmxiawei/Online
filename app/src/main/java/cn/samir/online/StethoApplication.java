package cn.samir.online;

import com.facebook.stetho.Stetho;

/**
 * Created by xiaw on 2017/5/22 0022.
 */

public class StethoApplication  extends OnlineApplication{


    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());
    }
}
