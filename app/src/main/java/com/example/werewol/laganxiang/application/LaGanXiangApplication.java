package com.example.werewol.laganxiang.application;

import android.app.Application;

import com.example.werewol.laganxiang.manager.MQTTManager;
import com.example.werewol.laganxiang.thirdparty.BaiduMapManager;

/**
 * Created by IBallLei on 2017/10/10.
 */

public class LaGanXiangApplication extends Application {
    /**
     * The s instance.
     */
    private static LaGanXiangApplication sInstance;

    public static LaGanXiangApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }


    private void init() {
        sInstance = this;
        // 初始化庆科云
//        FogManager.initFog(this);

        // 初始化百度地图
        BaiduMapManager.init(getApplicationContext());
        // 初始化MQTT
        MQTTManager.getInstance();
    }
}
