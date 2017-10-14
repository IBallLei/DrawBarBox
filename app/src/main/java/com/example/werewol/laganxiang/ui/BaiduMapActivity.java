package com.example.werewol.laganxiang.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mapapi.map.MapView;
import com.example.werewol.laganxiang.R;
import com.example.werewol.laganxiang.event.ChangeLongitudeAndLatitudeEvent;
import com.example.werewol.laganxiang.thirdparty.BaiduMapManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaiduMapActivity extends AppCompatActivity {

    @BindView(R.id.bmapView)
    MapView mMapView;

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

}
