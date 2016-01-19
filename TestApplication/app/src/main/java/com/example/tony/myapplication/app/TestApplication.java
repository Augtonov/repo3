package com.example.tony.myapplication.app;

import android.app.Application;

import com.squareup.leakcanary.RefWatcher;

/**
 * Created by tony on 12/21/2015.
 */
public class TestApplication extends Application {


    private static TestApplication instance;
    private RefWatcher refWatcher;

    public static synchronized TestApplication getInstance() {
        return instance;
    }

    /*public static RefWatcher getRefWatcher(Context context) {
        TestApplication application = (TestApplication) context.getApplicationContext();
        return application.refWatcher;
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        refWatcher = LeakCanary.install(this);
    }
}
