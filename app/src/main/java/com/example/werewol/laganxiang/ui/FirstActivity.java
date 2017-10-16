package com.example.werewol.laganxiang.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.werewol.laganxiang.Constants;
import com.example.werewol.laganxiang.R;
import com.example.werewol.laganxiang.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ButterKnife.bind(this);

        checkPermission();

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
                ToastUtil.showShort(this, "敬请期待...");
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
        }
    }

    private void jumpToMap() {
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
                    Toast.makeText(FirstActivity.this, "自Android 6.0开始需要打开位置权限", Toast.LENGTH_SHORT).show();
                }
                //请求权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        Constants.ACCESS_FINE_LOCATION_COMMANDS_REQUEST_CODE);
            }
        }
    }
}
