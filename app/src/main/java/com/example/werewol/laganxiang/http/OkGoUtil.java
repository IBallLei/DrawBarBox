package com.example.werewol.laganxiang.http;

import android.content.Context;

import com.example.werewol.laganxiang.http.request.BaseRequest;
import com.example.werewol.laganxiang.http.response.BaseResponse;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;

import java.util.Map;
import java.util.Set;

/**
 * Created by zhanglei
 * on 2017/10/23.
 */
public class OkGoUtil {

    private final Gson gson;

    private OkGoUtil() {
        gson = new Gson();

    }

    public static OkGoUtil getInstance() {
        return OkGoUtilHolder.instance;
    }

    private static class OkGoUtilHolder {
        private static OkGoUtil instance = new OkGoUtil();
    }

    public <T> void postJson(Context context, BaseRequest request,
                         final CallBack callBack, final Class clazz) {
        String json = gson.toJson(request);
        PostRequest<String> post = OkGo.<String>post(request.getUrl());
        Map<String, String> header = request.getHeader();
        Set<String> set = header.keySet();
        for (String key : set) {
            post.headers(key, header.get(key));
        }
        post.upJson(json).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                BaseResponse baseResponse = (BaseResponse) gson.fromJson(body, clazz);
                callBack.onSuccess(baseResponse);
            }
        });
    }

    public <T> void post(Context context, BaseRequest request,
                         final CallBack callBack, final Class clazz) {
        String json = gson.toJson(request);
        PostRequest<String> post = OkGo.<String>post(request.getUrl());
        Map<String, String> header = request.getHeader();
        Set<String> set = header.keySet();
        for (String key : set) {
            post.headers(key, header.get(key));
        }

        post.upBytes(json.getBytes()).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                BaseResponse baseResponse = (BaseResponse) gson.fromJson(body, clazz);
                callBack.onSuccess(baseResponse);
            }
        });
    }

    public interface CallBack {
        void onSuccess(BaseResponse response);
    }

}
