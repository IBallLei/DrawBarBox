package com.example.werewol.laganxiang.application;

import android.app.Application;
import android.bluetooth.BluetoothSocket;

import com.example.werewol.laganxiang.bluetooth.BltManager;
import com.example.werewol.laganxiang.manager.MQTTManager;
import com.example.werewol.laganxiang.thirdparty.BaiduMapManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;

/**
 * Created by IBallLei on 2017/10/10.
 */

public class LaGanXiangApplication extends Application {
    //不管是蓝牙连接方还是服务器方，得到socket对象后都传入
    public static BluetoothSocket bluetoothSocket;

    public String token;
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

        OkGo.getInstance().init(this)
                .addCommonHeaders(new HttpHeaders());
        // 初始化百度地图
        BaiduMapManager.init(getApplicationContext());
        // 初始化MQTT
        MQTTManager.getInstance();
        // 初始化蓝牙
        BltManager.getInstance().initBltManager(sInstance);
    }
}
