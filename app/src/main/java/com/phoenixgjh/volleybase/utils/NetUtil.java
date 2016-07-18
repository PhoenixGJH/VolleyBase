package com.phoenixgjh.volleybase.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 获取网路信息
 * Created by Phoenix on 2016/7/14.
 */
public class NetUtil {
    public static final int TYPE_DISCONNECTED = -1;
    public static final int TYPE_WIFI = 0;
    public static final int TYPE_NO_WIFI = 1;

    /**
     * 判断网路是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {
        //获取网路连接服务
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isAvailable();
    }

    /**
     * 获取网路类型
     *
     * @param context
     * @return
     */
    public static int netType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            return TYPE_DISCONNECTED;
        } else {
            return info.getType();
        }
    }
}
