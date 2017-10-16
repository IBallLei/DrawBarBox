package com.example.werewol.laganxiang.manager;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.example.werewol.laganxiang.application.LaGanXiangApplication;

/**
 * Created by zhanglei
 * on 2017/10/16.
 */
public class WiFiManager {
    public static String getMacAddress() {
        String macAddress =null;
        WifiManager wifiManager =
                (WifiManager) LaGanXiangApplication.getInstance()
                        .getApplicationContext()
                        .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null== wifiManager ?null: wifiManager.getConnectionInfo());

        if(!wifiManager.isWifiEnabled())
        {
            //必须先打开，才能获取到MAC地址
            wifiManager.setWifiEnabled(true);
            wifiManager.setWifiEnabled(false);
        }
        if(null!= info) {
            macAddress = info.getMacAddress();
        }
        return macAddress;
    }
}
