package com.example.werewol.laganxiang.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.werewol.laganxiang.R;
import com.example.werewol.laganxiang.event.InfoEvent;
import com.example.werewol.laganxiang.thirdparty.BaiduMapManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_back_btn)
    ImageView imgBackBtn;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.txt_id)
    TextView txtId;
    @BindView(R.id.txt_wendu)
    TextView txtWendu;
    @BindView(R.id.txt_huxun)
    TextView txtHuxun;
    @BindView(R.id.txt_bufang)
    TextView txtBufang;
    @BindView(R.id.img_demo)
    ImageView imgDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        BaiduMapManager.start();

        initViews();
    }

    private void initViews() {
        title.setText("信息");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(InfoEvent event) {
        int imageNum = event.getImageNum();
        switch (imageNum) {
            case 1:
//                imgDemo.setImageResource(R.);
                break;
        }
        double temperature = event.getTemperature();
        txtWendu.setText(temperature + "摄氏度");
    }

    @OnClick(R.id.img_back_btn)
    public void onViewClicked() {
        finish();
    }

}
