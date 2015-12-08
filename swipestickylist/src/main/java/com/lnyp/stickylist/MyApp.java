package com.lnyp.stickylist;

import android.app.Application;

import org.xutils.*;
import org.xutils.BuildConfig;

/**
 * Created by 李宁 on 2015/12/8.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
