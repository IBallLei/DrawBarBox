package com.example.werewol.laganxiang.http.request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanglei
 * on 2017/10/23.
 */
public class BaseRequest {
    private String url;
    private Map<String, String> headers = new HashMap<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map getHeader() {
        return headers;
    }

    public BaseRequest setHeader(String key, String header) {
        this.headers.put(key, header);
        return this;
    }
}
