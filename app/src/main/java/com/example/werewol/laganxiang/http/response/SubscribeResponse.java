package com.example.werewol.laganxiang.http.response;

/**
 * Created by zhanglei
 * on 2017/10/23.
 */
public class SubscribeResponse extends BaseResponse {
    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {

    }
}
