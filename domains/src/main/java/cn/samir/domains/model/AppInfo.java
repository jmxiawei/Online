package cn.samir.domains.model;

import android.content.Context;

import java.util.HashMap;

import cn.samir.domains.model.util.DateUtil;
import cn.samir.domains.model.util.PhoneUtil;

/**
 * Created by xiaw on 2017/4/7 0007.
 */

public class AppInfo {

    /**
     * udid=7c7e8036375b40abb81c7f9a5f73293ccb6969b7&
     * vc=175
     * &vn=3.4.3
     * &deviceModel=Android%20SDK%20built%20for%20x86
     * &first_channel=eyepetizer_baidu_market
     * &last_channel=eyepetizer_baidu_market
     * &system_version_code=25
     */

    private static AppInfo info = new AppInfo();

    private long date;

    private long nextPublishTime;

    private HashMap<String, String> params = new HashMap<>();

    private AppInfo() {
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }

    public String getDateString() {

        return new StringBuilder().append("- ").append(DateUtil.getWeekEn(date))
                .append(",")
                .append(DateUtil.getMonthEn(date))
                .append(DateUtil.getDayOfMonth(date)).append(" -").toString();


    }

    public HashMap<String, String> getParams() {
        params.put("vc", getVc());
        params.put("vn", getVn());
        params.put("udid", getUdid());
        params.put("deviceModel", getDeviceModel());
        params.put("first_channel", getFirst_channel());
        params.put("last_channel", getLast_channel());
        params.put("system_version_code", getSystem_version_code());
        return params;
    }

    public static void init(Context context) {
        AppInfo ai = instance();
        ai.setVc(String.valueOf(PhoneUtil.getVersionCode(context)));
        ai.setVn(PhoneUtil.getVersionName(context));
        ai.setUdid("7c7e8036375b40abb81c7f9a5f73293ccb6969b7");
        ai.setDeviceModel(PhoneUtil.getDevice());
        ai.setFirst_channel("eyepetizer_baidu_market");
        ai.setLast_channel("eyepetizer_baidu_market");
        ai.setSystem_version_code(PhoneUtil.getOSVersionInt());

    }

    public static AppInfo instance() {
        return info;
    }


    public String udid;
    public String vc;
    public String vn;
    public String deviceModel;
    public String first_channel;
    public String last_channel;
    public String system_version_code;

    public static void setInfo(AppInfo info) {
        AppInfo.info = info;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getVc() {
        return vc;
    }

    public void setVc(String vc) {
        this.vc = vc;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getFirst_channel() {
        return first_channel;
    }

    public void setFirst_channel(String first_channel) {
        this.first_channel = first_channel;
    }

    public String getLast_channel() {
        return last_channel;
    }

    public void setLast_channel(String last_channel) {
        this.last_channel = last_channel;
    }

    public String getSystem_version_code() {
        return system_version_code;
    }

    public void setSystem_version_code(String system_version_code) {
        this.system_version_code = system_version_code;
    }
}
