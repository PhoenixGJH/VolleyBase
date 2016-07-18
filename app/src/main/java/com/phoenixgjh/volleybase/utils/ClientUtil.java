package com.phoenixgjh.volleybase.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * 获取客户端相关信息
 * Created by Phoenix on 2016/7/14.
 */
public class ClientUtil {
    /**
     * 获取APP版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String versionName = "1.0";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
            if (!TextUtils.isEmpty(versionName)) {
                return versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return versionName;
        }
        return versionName;
    }

    /**
     * 获取版本Code
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        int versionCode = 1;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
