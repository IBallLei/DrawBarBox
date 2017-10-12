package com.example.werewol.laganxiang.application;

import android.app.Application;
import android.util.Log;

import com.example.werewol.laganxiang.manager.FogManager;

import io.fogcloud.sdk.fog.api.Fog;
import io.fogcloud.sdk.fog.callback.FogCallBack;

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
    }
}
