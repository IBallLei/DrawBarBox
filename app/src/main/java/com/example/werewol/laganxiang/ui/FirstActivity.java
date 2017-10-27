package com.example.werewol.laganxiang.ui;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.werewol.laganxiang.Constants;
import com.example.werewol.laganxiang.R;
import com.example.werewol.laganxiang.bluetooth.BltContant;
import com.example.werewol.laganxiang.bluetooth.BltManager;
import com.example.werewol.laganxiang.bluetooth.BltService;
import com.example.werewol.laganxiang.bluetooth.ReceiveSocketService;
import com.example.werewol.laganxiang.event.ChangeLongitudeAndLatitudeEvent;
import com.example.werewol.laganxiang.event.InfoEvent;
import com.example.werewol.laganxiang.http.OkGoUtil;
import com.example.werewol.laganxiang.http.response.BaseResponse;
import com.example.werewol.laganxiang.utils.AlarmUtils;
import com.example.werewol.laganxiang.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Werewol
 */
public class FirstActivity extends AppCompatActivity implements OkGoUtil.CallBack {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button2)
    Button button2;

//    private ServiceConnection connection;
//    private String token;

    /**
     * 蓝牙接收广播
     */
    BltManager.OnRegisterBltReceiver onRegisterBltReceiver = new BltManager.OnRegisterBltReceiver() {
        @Override
        public void onBluetoothDevice(BluetoothDevice device) {
            final BluetoothDevice bluetoothDevice = device;
            Log.e("blueTooth", ".onBluetoothDevice:" + bluetoothDevice);
            ToastUtil.showShort(FirstActivity.this, "onBluetoothDevice");
            //链接的操作应该在子线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    BltManager.getInstance().createBond(bluetoothDevice, handler);
                }
            }).start();
        }

        @Override
        public void onBltIng(BluetoothDevice device) {
            ToastUtil.showShort(FirstActivity.this, "onBltIng");
        }

        @Override
        public void onBltEnd(final BluetoothDevice device) {
            ToastUtil.showShort(FirstActivity.this, "onBltEnd");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    BltManager.getInstance().createBond(device, handler);
                }
            }).start();
        }

        @Override
        public void onBltNone(BluetoothDevice device) {
            ToastUtil.showShort(FirstActivity.this, "onBltNone");
        }

        @Override
        public void onBltOpen() {
            // 添加蓝牙服务器
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    BltService.getInstance().run(handler);
//                }
//            }).start();
        }

        @Override
        public void onBltClose() {
            BltService.getInstance().cancel();
        }
    };

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1://搜索蓝牙
                    break;
                case 2://蓝牙可以被搜索
                    break;
                case 3:
                    final BluetoothDevice bltServiceDev = (BluetoothDevice) message.obj;
                    ToastUtil.showShort(FirstActivity.this, "bltServiceDev:" + bltServiceDev);

                    break;
                case 4:
                    BluetoothDevice btDev = (BluetoothDevice) message.obj;
                    ToastUtil.showShort(FirstActivity.this, "链接成功——btDev:" + btDev);
                    // 配对完成接受信息
                    ReceiveSocketService.receiveMessage(handler);
                    break;
                default:
                    String msg = (String) message.obj;
                    ToastUtil.showShort(FirstActivity.this, "msg:" + msg);
                    parseMsgInfo(msg);
                    break;
            }
            return false;
        }
    });

    /**
     * 解释蓝牙设备返回值
     *
     * @param msg "6/333.333333/22.2222222/27.45/201710191412"
     */
    private void parseMsgInfo(String msg) {
        if (msg != null) {
            String[] split = msg.split("/");
            int imageNum = Integer.parseInt(split[0]);
            double longitude = Double.parseDouble(split[1]);
            double latitude = Double.parseDouble(split[2]);
            double temperature = Double.parseDouble(split[3]);
            String time = split[4];
            ToastUtil.showShort(this, "角度图：" + imageNum
                    + "，经度：" + longitude + "，维度：" + latitude
                    + "，温度：" + temperature + "，时间：" + time);


            EventBus.getDefault()
                    .post(new ChangeLongitudeAndLatitudeEvent(BaiduMapActivity.POINT_TYPE_LAGANXIANG,
                            latitude, longitude));
            EventBus.getDefault().post(new InfoEvent(imageNum, temperature));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ButterKnife.bind(this);

        checkPermission();

//        Intent service = new Intent(this, QatjaService.class);

        connectTopic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 停止蓝牙搜索
        BltManager.getInstance().stopSearthBltDevice();
        // 反注册蓝牙配对
        BltManager.getInstance().unregisterReceiver(this);
//        unbindService(connection);
        AlarmUtils.stopAlarm();
    }

    private void connectTopic() {
//        token = String.valueOf(SPUtils.get(this, SPUtils.KEY_TOKEN, ""));
//        if (TextUtils.isEmpty(token)) {
//            getToken();
//        } else {
//            subscribe();
//        }

//        connection = MQTTManager.getInstance().getConnection();
//        bindService(service, MQTTManager.getInstance().getConnection(), Context.BIND_AUTO_CREATE);

//        MQTTManager.getInstance().actionStart(this);

        BltManager.getInstance().checkBleDevice(this);
        // 监听蓝牙广播
        BltManager.getInstance().registerBltReceiver(this, onRegisterBltReceiver);

        initBlt();
    }

    private void initBlt() {
        //让本机设备能够被其他人搜索到
        BltManager.getInstance().clickBlt(this, BltContant.BLUE_TOOTH_MY_SEARTH);

//        BltManager.getInstance().getBltList();

        // 添加蓝牙服务器
//        if (BltManager.getInstance().getmBluetoothAdapter().isEnabled()) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    BltService.getInstance().run(handler);
//                }
//            }).start();
//        }
    }

    @OnClick({R.id.button1, R.id.button7, R.id.button8, R.id.button6, R.id.button5, R.id.button3, R.id.button4, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                ToastUtil.showShort(this, "敬请期待...");
                break;
            case R.id.button7:
                ToastUtil.showShort(this, "敬请期待...");
                break;
            case R.id.button8:
//                subscribe();
                BltManager.getInstance().clickBlt(this, BltContant.BLUE_TOOTH_SEARTH);
                break;
            case R.id.button6:
                ToastUtil.showShort(this, "敬请期待...");
                break;
            case R.id.button5:
                jumpToMap();
                break;
            case R.id.button3:
                ToastUtil.showShort(this, "敬请期待...");
                break;
            case R.id.button4:
                ToastUtil.showShort(this, "敬请期待...");
                break;
            case R.id.button2:
                ToastUtil.showShort(this, "敬请期待...");
                break;
            default:
                break;
        }
    }

//    private void getToken() {
//        BaseRequest baseRequest = new AuthRequest.Builder().build().create();
//        OkGoUtil.getInstance().postJson(this, baseRequest, this, AuthResponse.class);
//    }

//    private void subscribe() {
//        BaseRequest baseRequest = new SubscribeRequest.Builder()
//                .setToken(token).build().create();
//        OkGoUtil.getInstance().post(this, baseRequest, this, SubscribeResponse.class);
//    }

    private void jumpToMap() {
        Intent intent = new Intent(this, BaiduMapActivity.class);
        startActivity(intent);
    }

    public void checkPermission() {
        //Android 6.0判断用户是否授予定位权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果 API level 是大于等于 23(Android 6.0) 时
            //判断是否具有权限
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //判断是否需要向用户解释为什么需要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    Toast.makeText(FirstActivity.this, "自Android 6.0开始需要打开位置权限", Toast.LENGTH_SHORT).show();
                }
                //请求权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        Constants.ACCESS_FINE_LOCATION_COMMANDS_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onSuccess(BaseResponse response) {
//        if (response instanceof AuthResponse) {
//            if (response.getCode() == 0) {
//                AuthResponse authResponse = (AuthResponse) response;
//                if (authResponse.getInfo() != null) {
//                    SPUtils.put(this, SPUtils.KEY_TOKEN, authResponse.getInfo().getToken());
//                    token = authResponse.getInfo().getToken();
//                }
//            }
//        } else if (response instanceof SubscribeResponse) {
//            if (response.getCode() == 20001 || response.getCode() == 20002 || response.getCode() == 20003) {
//                getToken();
//            } else if (response.getCode() == 0) {
//                SubscribeResponse subscribeResponse = (SubscribeResponse) response;
//            }
//        }
    }


}
