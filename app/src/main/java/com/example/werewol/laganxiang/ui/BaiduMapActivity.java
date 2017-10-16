package com.example.werewol.laganxiang.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.map.MapView;
import com.example.werewol.laganxiang.R;
import com.example.werewol.laganxiang.event.ChangeLongitudeAndLatitudeEvent;
import com.example.werewol.laganxiang.thirdparty.BaiduMapManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaiduMapActivity extends AppCompatActivity {

    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.setting)
    Button setting;
    @BindView(R.id.info)
    Button info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        BaiduMapManager.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ChangeLongitudeAndLatitudeEvent event) {
        BaiduMapManager.setMaker(mMapView, event.getLatitude(), event.getLongitude());
    }

    @OnClick({R.id.setting, R.id.info})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.setting:
                intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.info:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
