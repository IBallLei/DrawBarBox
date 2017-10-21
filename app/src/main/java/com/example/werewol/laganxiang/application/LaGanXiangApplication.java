package com.example.werewol.laganxiang.application;

import android.app.Application;
import android.bluetooth.BluetoothSocket;

import com.example.werewol.laganxiang.manager.MQTTManager;
import com.example.werewol.laganxiang.thirdparty.BaiduMapManager;

/**
 * Created by IBallLei on 2017/10/10.
 */

public class LaGanXiangApplication extends Application {
        //不管是蓝牙连接方还是服务器方，得到socket对象后都传入
        public static BluetoothSocket bluetoothSocket;
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
