package com.example.werewol.laganxiang.manager;

import android.content.Context;
import android.util.Log;

import com.example.werewol.laganxiang.entity.FogResult;
import com.google.gson.Gson;

import io.fogcloud.sdk.fog.api.Fog;
import io.fogcloud.sdk.fog.callback.FogCallBack;

/**
 * Created by IBallLei on 2017/10/10.
 */

public class FogManager {
    public static final String TAG = "FogManager";

    public static final String LOGIN_NAME = "zhanglei0325@163.com";
    public static final String APPID = "51c65b3a-ad61-11e7-8d17-00163e03b4d6";

    public static final String SERVER_URL = "https://api.fogcloud.io/v3";

    private static Fog fog;

    /**
     * 初始化SDK
     *
     * @param context
     */
    public static void initFog(Context context) {
        fog = new Fog(context);

        fog.init(SERVER_URL);

        fog.getVerifyCode(LOGIN_NAME, APPID, new FogCallBack() {
            @Override
            public void onSuccess(String s) {
                Log.e(TAG, "Fog:getVerifyCode_onSuccess：" + s);
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e(TAG, "Fog" + i + ":getVerifyCode_onFailure:" + s);
            }
        });
    }

    /**
     * 获取验证码
     */
    public static void getVerifyCode() {
        fog.getVerifyCode(LOGIN_NAME, APPID, new FogCallBack() {
            @Override
            public void onSuccess(String message) {
                Log.d(TAG, message);
            }

            @Override
            public void onFailure(int code, String message) {
                Log.d(TAG, code + " " + message);
            }
        });
    }


    /**
     * 获取验证码
     */
    public static void checkVerifyCode(String vercode) {
        fog.checkVerifyCode(LOGIN_NAME, vercode, APPID, new FogCallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                FogResult checkFogResult = gson.fromJson(message, FogResult.class);
                Log.d(TAG, message);
            }

            @Override
            public void onFailure(int code, String message) {
                Log.d(TAG, code + " " + message);
            }
        });
    }

    /**
     * 初次设置密码
     *
     * @param password
     * @param token
     */
    public static void setPassword(String password, String token) {
        fog.setPassword(password, new FogCallBack() {
            @Override
            public void onSuccess(String message) {
                Log.d(TAG, message);
            }

            @Override
            public void onFailure(int code, String message) {
                Log.d(TAG, code + " " + message);
            }
        }, token);
    }

    /**
     * 登录
     *
     * @param password
     */
    public static void login(String password) {
        fog.login(LOGIN_NAME, password, APPID, new FogCallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                FogResult loginFogResult = gson.fromJson(message, FogResult.class);
                Log.d(TAG, message);
            }

            @Override
            public void onFailure(int code, String message) {
                Log.d(TAG, code + " " + message);
            }
        });
    }

    /**
     * 刷新token
     * @param userToken
     */
    public static void refreshToken(String userToken) {
        fog.refreshToken(userToken, new FogCallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                FogResult refreshTokenFogResult = gson.fromJson(message, FogResult.class);
                Log.d(TAG, message);
            }
            @Override
            public void onFailure(int code, String message) {
                Log.d(TAG, code + " " + message);
            }
        });
    }

    /**
     * 获取WiFi名
     * @return
     */
    public static String getSSID() {
        Log.d(TAG, fog.getSSID());
        return fog.getSSID();
    }

    /**
     * 开始配网
     * @param password
     */
    public static void startEasyLink(String password) {
        String ssidStr = getSSID();
        int runs = 10000; //发送10秒即关闭
//        fog.startEasyLink(ssidStr, password, false, runs, 20, "", "", new EasyLinkCallBack() {
//            @Override
//            public void onSuccess(String message) {
//                Log.d(TAG, message);
//            }
//
//            @Override
//            public void onFailure(int code, String message) {
//                Log.d(TAG, code + " " + message);
//            }
//        });
    }

    /**
     * 停止配网
     */
    public static void stopEasyLink() {
//        fog.stopEasyLink(new EasyLinkCallBack() {
//            @Override
//            public void onSuccess(String message) {
//                Log.d(TAG, message);
//            }
//            @Override
//            public void onFailure(int code, String message) {
//                Log.d(TAG, code + " " + message);
//            }
//        });
    }


}
