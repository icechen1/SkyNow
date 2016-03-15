package com.yuchenhou.skynow;

import android.app.Application;
import android.content.Context;

/**
 * Created by yuchen on 3/13/16.
 */
public class SkyNowApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        SkyNowApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return SkyNowApp.context;
    }
}
