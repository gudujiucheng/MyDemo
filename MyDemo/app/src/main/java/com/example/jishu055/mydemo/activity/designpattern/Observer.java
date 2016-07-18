package com.example.jishu055.mydemo.activity.designpattern;

/**
 * Created by jishu055 on 2016/7/15.
 */

/**
 * 观察者接口
 *
 * @author Administrator
 */
public interface Observer {
    /**
     * 观察者收到信息，做出自己的相应操作
     * @param subject 被观察者
     * @param data    被观察者传递给观察者的 数据
     */
    void onReceivedMsg(Subject subject, Object data);

}