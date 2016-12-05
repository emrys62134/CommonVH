package com.pei.commonvh;

import android.app.Application;
import android.content.Context;



public class MyApp extends Application{
    private static Context sContext;

//d4cb19c919e9cafe477e963a7a546bd2

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        //第一：默认初始化  建议初始化放到Application


    }
    public static Context getContext(){
        return sContext;
    }
}
