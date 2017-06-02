package cn.samir.domains.model.util;

import java.util.Calendar;

/**
 * 星期
 * 星期一： Mon.=Monday
 * 星期二： Tues.=Tuesday
 * 星期三：Wed.=Wednesday
 * 星期四： Thur.=Thursday
 * 星期五： Fri.=Friday
 * 星期六： Sat.=Saturday
 * 星期天： Sun.=Sunday
 * 月份
 * 一月份＝JAN. Jan.=January
 * 二月份＝FEB. Feb.=February
 * 三月份＝MAR. Mar.=March
 * 四月份＝APR. Apr.=April
 * 五月份＝MAY May=May
 * 六月份＝JUN. Jun.=June
 * 七月份＝JUL. Jul.=July
 * 八月份＝AUG. Aug.=August
 * 九月份＝SEP. Sept.=September
 * 十月份＝OCT. Oct.=October
 * 十一月份＝NOV. Nov.=November
 * 十二月份＝DEC. Dec.=Decembe
 * Created by xiaw on 2017/4/15 0015.
 */

public class DateUtil {


    private static String[] weeks = new String[]{
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
    };


    private static String[] months = new String[]{
            "Jan.",
            "Feb.",
            "Mar.",
            "Apr.",
            "May.",
            "Jun.",
            "Jul.",
            "Aug.",
            "Sept.",
            "Oct.",
            "Nov.",
            "Dec.",

    };


    public static String getWeekEn(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return weeks[week - 1];
    }


    public static String getMonthEn(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int month = calendar.get(Calendar.MONTH);
        if (month > 0) {
            return months[month];
        }
        return "";
    }


    public static int getDay(long ms) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        return calendar.get(Calendar.DAY_OF_MONTH);


    }


    public static String buildMonthAndDay(long mils) {
        return getMonthEn(mils)+getDay(mils);

    }


    public static String getDayOfMonth(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

}
