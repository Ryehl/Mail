package com.xaoyv.small.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mmkv.MMKV;

import io.realm.Realm;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:自定义App</p>
 *
 * @author Xaoyv
 * date 2020/10/14 23:26
 */
public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //初始化Fresco
        Fresco.initialize(this);
        //初始化数据库
        Realm.init(this);
        //初始化MMKV存储
        MMKV.initialize(this);
    }
}
