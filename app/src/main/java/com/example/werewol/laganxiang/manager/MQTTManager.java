package com.example.werewol.laganxiang.manager;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;

import com.example.werewol.laganxiang.application.LaGanXiangApplication;
import com.example.werewol.laganxiang.mqtt.MQTTCallback;
import com.example.werewol.laganxiang.mqtt.QatjaService;
import com.example.werewol.laganxiang.utils.SignUtil;

import java.util.HashMap;
import java.util.Map;

import static com.baidu.location.h.k.t;


/**
 * Created by zhanglei
 * on 2017/10/16.
 */
public class MQTTManager {

    private Handler mHandler;
    private boolean isBound;
    private QatjaService client;
    private ServiceConnection connection;

    private MQTTManager() {
        mHandler = new Handler(new MQTTCallback(LaGanXiangApplication.getInstance()));
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                client = ((QatjaService.QatjaBinder) binder).getService();
                isBound = true;
                client.setHandler(mHandler);
                setClient("");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBound = false;
            }
        };
    }

    public static MQTTManager getInstance() {
        return MQTTManagerHolder.instance;
    }

    private static class MQTTManagerHolder {
        private static MQTTManager instance = new MQTTManager();
    }

    public void setClient(String host) {
        setClient(host, 0);
    }

    public void setClient(String host, int port) {
        // Default host is test.mosquitto.org (you should change this!)
        client.setHost("GgZCdhJKx8k.iot-as-mqtt.cn-shanghai.aliyuncs.com");

        // Default mqtt port is 1883
        if (port == 0) {
            client.setPort(1883);
        } else {
            client.setPort(port);
        }

        // Set a unique id for this client-broker combination
        String clientId = WiFiManager.getMacAddress();
        String mqttclientId = clientId + "|securemode=2,signmethod=hmacsha1,timestamp="+t+"|";

        Map<String, String> params = new HashMap<>();
        params.put("productKey", "GgZCdhJKx8k"); //这个是对应用户在控制台注册的 设备productkey
        params.put("deviceName", "2zVxKUdAkLDvKtnnXivD"); //这个是对应用户在控制台注册的 设备name
        params.put("clientId", clientId);
        String t = String.valueOf(System.currentTimeMillis());
        params.put("timestamp", t);
        String mqttPassword = SignUtil.sign(params, "a60Jjhqt4alkFP8HW99f8850Dxk1ZhPH", "hmacsha1");

        client.setIdentifier(mqttclientId);
        
        client.setUsername("2zVxKUdAkLDvKtnnXivD"+"&"+"GgZCdhJKx8k");

        client.setPassword(mqttPassword);

        // Set keep alive time
        client.setKeepAlive(3000);

        // Open the connection to the MQTT server
        client.connect();
    }

    public void subscribe() {
//        // Subscribe to a topic with Quality of Service AT_MOST_ONCE
        client.subscribe("/GgZCdhJKx8k/2zVxKUdAkLDvKtnnXivD/get");
//        // Subscribe to a topic with specified Quality of Service ()
//        client.subscribe(topic, EXACTLY_ONCE);
//        // Subscribe to multiple topics (String[]) with Quality of Service AT_MOST_ONCE
//        client.subscribe(topics[]);
//        // Subscribe to multiple topics (String[]) with specified quality of service (byte[]) for each topic
//        client.subscribe(topics[], qoss[]);
    }

    public void publish() {
//        // Publish a String message
        client.publish("update", "my message");
//        // Publish a byte[] message
//        client.publish("mytopic", message[]);
//        // Publish a byte[] message with RETAIN flag set
//        client.publish("mytopic", message[], true);
    }

    public ServiceConnection getConnection() {
        return connection;
    }

}
