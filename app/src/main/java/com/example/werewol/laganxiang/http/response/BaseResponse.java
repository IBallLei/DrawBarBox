package com.example.werewol.laganxiang.http.response;

/**
 * Created by zhanglei
 * on 2017/10/23.
 */
public abstract class BaseResponse {

    /**
     * code : 0
     * info : {"token":"eyJ0eXBlIjoiSldUIiwiYWxnIjoiaG1hY3NoYTEifQ.eyJleHBpcmUiOjE1MDkzNDg1NDQ5NjYsInRva2VuIjoiNzAwNjRlODdiMmMxNDE2Y2EyOGZmZDhkNTBhMzE3MjkifQ.vPcJd9GgKp02lKCUnOD7X2W0kDc"}
     * message : success
     */

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
