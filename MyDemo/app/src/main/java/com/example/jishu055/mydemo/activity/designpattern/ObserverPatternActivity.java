package com.example.jishu055.mydemo.activity.designpattern;

import android.os.Bundle;
import android.view.View;

import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.activity.BaseActivity;

public class ObserverPatternActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer_pattern);

    }

    public void TestObserver(View v) {
        ConcreteSubject concreteSubject = new ConcreteSubject();//真正的实例对象
        CartoonObserver cartoonObserver = new CartoonObserver();//卡通的监听器
        NBAObserver nbaObserver = new NBAObserver();//看nba的监听器
        //把监听器放到要监听的对象内部中去
        concreteSubject.addObserver(cartoonObserver);
        concreteSubject.addObserver(nbaObserver);

        //被监听的对象遍历发通知
        concreteSubject.notifyAllObserver("老师来了");

    }

}

