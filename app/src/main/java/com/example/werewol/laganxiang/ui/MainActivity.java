package com.example.werewol.laganxiang.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.werewol.laganxiang.Constants;
import com.example.werewol.laganxiang.R;
import com.example.werewol.laganxiang.event.ChangeLongitudeAndLatitudeEvent;
import com.example.werewol.laganxiang.thirdparty.BaiduMapManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_demo)
    ImageView imgDemo;
    @BindView(R.id.txt_longitude)
    TextView txtLongitude;
    @BindView(R.id.txt_latitude)
    TextView txtLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        checkPermission();

        BaiduMapManager.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ChangeLongitudeAndLatitudeEvent event) {
        txtLatitude.setText(String.valueOf(event.getLatitude()));
        txtLongitude.setText(String.valueOf(event.getLongitude()));
    }

    @OnClick(R.id.img_demo)
    public void onViewClicked() {
        Intent intent = new Intent(this, BaiduMapActivity.class);
        startActivity(intent);
    }

    public void checkPermission() {
        //Android 6.0判断用户是否授予定位权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//如果 API level 是大于等于 23(Android 6.0) 时
            //判断是否具有权限
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //判断是否需要向用户解释为什么需要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    Toast.makeText(MainActivity.this, "自Android 6.0开始需要打开位置权限", Toast.LENGTH_SHORT).show();
                }
                //请求权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        Constants.ACCESS_FINE_LOCATION_COMMANDS_REQUEST_CODE);
            }
        }
    }
}
