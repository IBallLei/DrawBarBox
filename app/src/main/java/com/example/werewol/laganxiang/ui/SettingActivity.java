package com.example.werewol.laganxiang.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.werewol.laganxiang.R;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.img_back_btn)
    ImageView imgBackBtn;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.dingwei)
    TextView dingwei;
    @BindView(R.id.switch_dingwei)
    SwitchButton switchDingwei;
    @BindView(R.id.blueteeth)
    TextView blueteeth;
    @BindView(R.id.switch_blueteeth)
    SwitchButton switchBlueteeth;
    @BindView(R.id.fengmingqi)
    TextView fengmingqi;
    @BindView(R.id.switch_fengmingqi)
    SwitchButton switchFengmingqi;
    @BindView(R.id.led)
    TextView led;
    @BindView(R.id.switch_led)
    SwitchButton switchLed;
    @BindView(R.id.huxun)
    TextView huxun;
    @BindView(R.id.switch_huxun)
    SwitchButton switchHuxun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        title.setText("设置");

        initSwitchButton();
    }

    private void initSwitchButton() {
        setSwitchButton(switchDingwei);
        setSwitchButton(switchBlueteeth);
        setSwitchButton(switchFengmingqi);
        setSwitchButton(switchLed);
        setSwitchButton(switchHuxun);
    }

    SwitchButton.OnCheckedChangeListener onCheckedChangeListener =
            new SwitchButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                    switch (view.getId()) {
                        case R.id.switch_dingwei:

                            break;
                        case R.id.switch_blueteeth:

                            break;
                        case R.id.switch_fengmingqi:

                            break;
                        case R.id.switch_led:

                            break;
                        case R.id.switch_huxun:

                            break;
                    }
                }
            };

    private void setSwitchButton(SwitchButton switchButton) {
        switchButton.setChecked(true);
        switchButton.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @OnClick(R.id.img_back_btn)
    public void onViewClicked() {
        finish();
    }
}
