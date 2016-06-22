package com.example.jishu055.mydemo.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jishu055.mydemo.R;

public class BaseActivity extends Activity {

    /**
     * 测试会不会调用
     */
    public void initData() {

    }



    public  void callBack(){
        initData();
    }






    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(BaseActivity.this, "收到广播", Toast.LENGTH_SHORT).show();
            initData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("BaseActivity", "DEMO当前所在的activity：" + getClass().getSimpleName());
        registerBro();
    }

    private MyReceiver mReceiver;

    public void registerBro() {
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction("ACTION_TEST");
        mReceiver = new MyReceiver();
        registerReceiver(mReceiver, mFilter);

    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

}
