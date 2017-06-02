package cn.samir.online.util;

/**
 * Created by xiaw on 2017/3/17 0017.
 */

public class Constant {

    public static final int MILS_SECONDS = 1000;

    public static final int MILS_MINUTES = MILS_SECONDS * 60;

    public static final int MILS_HOURS = MILS_MINUTES * 60;

    public static final long MILS_DAYS = MILS_HOURS * 24;


    public static final int BANNER_SWITCH_DELAY = 5000;

    public static final String FOLDER_ROOT = "/kaiyan";

    public static final String SP_PREFIX = "cn_samir_online_";

    /**
     * 是否是首次加载
     */
    public static final String SP_IS_FIRST_LOAD = SP_PREFIX + "SP_IS_FIRST_LOAD";


    public static final double PI = 3.1415926;


    public static final int ERROR_CODE_NET = 1;

    public static final String ACTION_EVENT = SP_PREFIX + "ACTION_EVENT";


    public static final String ACTION_EVENT_DATA = SP_PREFIX + "ACTION_EVENT_DATA";

    /**
     * 保存配置
     */
    public static final String SP_CONFIGS = SP_PREFIX + "SP_CONFIGS";


    // sync -adapter

    public static final String ACCOUNT_TYPE = "cn.samir.online.eyepetizer";
    public static final String AUTHORITY = "cn.samir.online.eyepetizer";









    public static final String IJK_LIB_NAME = "libijkplayer.so";


}
