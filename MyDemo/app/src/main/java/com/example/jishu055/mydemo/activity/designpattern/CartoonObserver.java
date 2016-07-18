package com.example.jishu055.mydemo.activity.designpattern;

import android.util.Log;

/**
 * Created by zc on 2016/7/15.
 */
public class CartoonObserver implements Observer {
    @Override
    public void onReceivedMsg(Subject subject, Object data) {
        Log.e("DebugLog", "我是" + this.getClass().
                getSimpleName() + data + "别看漫画了");
    }

}