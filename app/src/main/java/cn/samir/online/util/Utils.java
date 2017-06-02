package cn.samir.online.util;

import android.text.TextUtils;

/**
 * Created by xiawei on 2017/3/16.
 */

public class Utils {

    public static String buildCategoryAndDuration(String cate, int duration) {
        StringBuilder sb = new StringBuilder();
        sb.append("#")
                .append(cate)
                .append("   /   ")
                .append(Utils.getMinites(duration))
                .append("'")
                .append(duration % 60)
                .append("\"");
        return sb.toString();
    }


    public static String getMinites(int duration) {
        if (duration < 60) {
            return "00";
        } else if (duration < 600) {
            return "0" + duration / 60;
        } else {
            return String.valueOf(duration / 60);
        }
    }


    public static boolean isEmpty(String text) {

        if (text == null || text.length() == 0 || "null".equalsIgnoreCase(text)) {
            return true;
        }
        return false;
    }


    public static String applySpace(String text, int numSpace) {

        if (TextUtils.isEmpty(text)) {
            return "";
        }
        if (numSpace <= 0) {
            return text;
        }
        if (numSpace > 5) {
            numSpace = 5;
        }
        StringBuilder sb = new StringBuilder();

        int l = text.length();

        for (int i = 0; i < l; i++) {
            sb.append(text.charAt(i));

            if (i < (l - 1)) {//最后一个后面不加空格
                for (int j = 0; j < numSpace; j++) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }


    public static  String formatDayOffset(long time) {


        long current = System.currentTimeMillis();

        long offset = current - time;

        if (offset < Constant.MILS_DAYS) {
            //今天
            return "今天";
        } else if (offset > 7 * Constant.MILS_DAYS) {
            //一周前
            return "1周前";
        } else {
            return offset / Constant.MILS_DAYS + "天前";
        }


    }
}
