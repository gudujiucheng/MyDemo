package com.example.jishu055.mydemo.activity.json.cart;

/**
 * 响应基类，与响应相关的都继承此类
 * Created by xz on 2016/03/09.
 */

public class BaseResponse<T> {

    /**
     * 响应的状态 200表示成功，其他表示失败
     */
    protected int status;

    /**
     * 数据
     * JSON格式，成功的情况下会返回该字段
     * 基础数据都是在这个data集合里面的
     */
    protected T data;

    /**
     * 错误信息
     * 失败的情况下会返回该字段
     */
    protected String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
