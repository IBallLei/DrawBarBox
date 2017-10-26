package com.example.werewol.laganxiang.http.response;

/**
 * Created by zhanglei
 * on 2017/10/23.
 */
public class AuthResponse extends BaseResponse {

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
