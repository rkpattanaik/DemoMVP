package com.rkpattanaik.demomvp;

import android.app.Application;


/**
 * @author Rajesh Pattanaik
 */

public class MainApp extends Application {
    private static MainApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public static MainApp getInstance() {
        return sInstance;
    }
}
