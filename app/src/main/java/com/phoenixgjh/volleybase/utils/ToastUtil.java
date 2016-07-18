package com.phoenixgjh.volleybase.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Phoenix on 2016/7/18.
 */
public class ToastUtil {
    public static void shortToast(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }
}
