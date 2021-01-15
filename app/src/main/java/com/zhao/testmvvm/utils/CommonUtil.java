package com.zhao.testmvvm.utils;

import android.content.Context;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/13
 * Time: 15:17
 */
public class CommonUtil {

    public static int dp2px(Context context, float dpValue) {
        if (null == context) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static int getScreenWidth(Context context) {
        if (null == context) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
