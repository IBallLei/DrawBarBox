package com.example.werewol.laganxiang.http.request;

import com.example.werewol.laganxiang.manager.WiFiManager;
import com.example.werewol.laganxiang.utils.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanglei
 * on 2017/10/23.
 */
public class AuthRequest extends BaseRequest{

    private String version;
    private String clientId;
    private String signmethod;
    private String sign;
    private String productKey;
    private String deviceName;
    private String timestamp;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSignmethod() {
        return signmethod;
    }

    public void setSignmethod(String signmethod) {
        this.signmethod = signmethod;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class Builder {
        private AuthRequest request = new AuthRequest();

        private String url;
        private String header;

        private String version = "default";
        private String clientId = WiFiManager.getMacAddress();
        private String signmethod = "hmacsha1";
        private String sign;
        private String productKey;
        private String deviceName;
        private String timestamp;

        public Builder() {
            url = "https://iot-as-http.cn-shanghai.aliyuncs.com/auth";
            productKey = "GgZCdhJKx8k";
            deviceName = "2zVxKUdAkLDvKtnnXivD";
            timestamp = String.valueOf(System.currentTimeMillis());
        }

        public Builder build() {
            request.setUrl(url);
            request.setProductKey(productKey);
            request.setDeviceName(deviceName);
            request.setClientId(clientId);
            request.setTimestamp(timestamp);
            request.setSignmethod(signmethod);
            request.setVersion(version);

            Map<String, String> params = new HashMap<>();
            params.put("productKey", productKey); //这个是对应用户在控制台注册的 设备productkey
            params.put("deviceName", deviceName); //这个是对应用户在控制台注册的 设备name
            params.put("clientId", clientId);
            params.put("timestamp", timestamp);
            sign = SignUtil.sign(params, "a60Jjhqt4alkFP8HW99f8850Dxk1ZhPH", "hmacsha1");
            request.setSign(sign);

            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setRequest(AuthRequest request) {
            this.request = request;
            return this;
        }

        public Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder setClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder setSignmethod(String signmethod) {
            this.signmethod = signmethod;
            return this;
        }

        public Builder setSign(String sign) {
            this.sign = sign;
            return this;
        }

        public Builder setProductKey(String productKey) {
            this.productKey = productKey;
            return this;
        }

        public Builder setDeviceName(String deviceName) {
            this.deviceName = deviceName;
            return this;
        }

        public Builder setTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public BaseRequest create() {
            return request;
        }
    }

}
