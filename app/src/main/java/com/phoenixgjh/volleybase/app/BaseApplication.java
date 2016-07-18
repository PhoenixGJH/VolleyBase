package com.phoenixgjh.volleybase.app;

import android.app.Application;
import android.content.Context;

/**
 * Application
 * Created by Phoenix on 2016/7/14.
 */
public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        instance = this;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
