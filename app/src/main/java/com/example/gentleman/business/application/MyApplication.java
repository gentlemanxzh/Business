package com.example.gentleman.business.application;

import android.app.Application;

/**
 * 1、整个程序的入口
 * 2、初始化工作
 * 3、为整个应用的其他模块提供上下文
 * Created by gentleman on 2017/12/4.
 */

public class MyApplication extends Application {

    public static MyApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static MyApplication getInstance(){
    return mApplication;
    }



}
