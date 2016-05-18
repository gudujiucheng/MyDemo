package com.example.jishu055.mydemo.activity.json.cart;

/**
 * 响应基类，与响应相关的都继承此类
 * Created by xz on 2016/03/09.
 */

public class BaseResponseTest {

    /**
     * 响应的状态 200表示成功，其他表示失败
     */
    protected int status;


    /**
     * 错误信息
     * 失败的情况下会返回该字段
     */
    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
