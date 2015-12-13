package com.lnyp.stickylist;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;


public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        // 配置日志是否输出(默认true)
        LogUtils.configAllowLog = true;

        // 配置日志前缀
        LogUtils.configTagPrefix = "quickandroid-";
    }
}
