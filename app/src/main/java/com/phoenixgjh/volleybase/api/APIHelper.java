package com.phoenixgjh.volleybase.api;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.phoenixgjh.volleybase.app.BaseApplication;

/**
 * APIHelper
 * Created by Phoenix on 2016/7/14.
 */
public class APIHelper {
    private static final String TAG = "APIHelper";
    private static APIHelper instance;
    private RequestQueue requestQueue;

    public static APIHelper getInstance() {
        if (instance == null) {
            synchronized (APIHelper.class) {
                if (instance == null) {
                    instance = new APIHelper();
                }
            }
        }
        return instance;
    }

    private APIHelper() {
        requestQueue = Volley.newRequestQueue(BaseApplication.getContext());
    }
}
