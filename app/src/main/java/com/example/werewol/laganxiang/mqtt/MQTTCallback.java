package com.example.werewol.laganxiang.mqtt;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import se.wetcat.qatja.messages.MQTTPublish;

import static com.example.werewol.laganxiang.mqtt.MQTTConnectionConstants.STATE_CHANGE;
import static com.example.werewol.laganxiang.mqtt.MQTTConnectionConstants.STATE_CONNECTED;
import static com.example.werewol.laganxiang.mqtt.MQTTConnectionConstants.STATE_CONNECTING;
import static com.example.werewol.laganxiang.mqtt.MQTTConnectionConstants.STATE_CONNECTION_FAILED;
import static com.example.werewol.laganxiang.mqtt.MQTTConnectionConstants.STATE_NONE;
import static se.wetcat.qatja.MQTTConstants.PUBLISH;

/**
 * Created by zhanglei
 * on 2017/10/16.
 */
public class MQTTCallback implements Handler.Callback {

    private Context mContext;

    public MQTTCallback(Context context) {
        mContext = context;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case STATE_CHANGE:
                switch (msg.arg1) {
                    case STATE_NONE:
                        Toast.makeText(mContext, "Not connected", Toast.LENGTH_SHORT).show();
                        return true;
                    case STATE_CONNECTING:
                        Toast.makeText(mContext, "Trying to connect...", Toast.LENGTH_SHORT).show();
                        return true;
                    case STATE_CONNECTED:
                        Toast.makeText(mContext, "Yay! Connected!", Toast.LENGTH_SHORT).show();
                        return true;
                    case STATE_CONNECTION_FAILED:
                        Toast.makeText(mContext, "Connection failed", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return true;
            case PUBLISH:
                MQTTPublish publish = (MQTTPublish) msg.obj;
                String topic = publish.getTopicName();
                byte[] payload = publish.getPayload();
                return true;
            default:
                return false;
        }
    }
}
