package com.example.jishu055.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.activity.json.JsonActivity;
import com.example.jishu055.mydemo.activity.view.DrawableActivity;
import com.example.jishu055.mydemo.activity.view.ListItemActivity;
import com.example.jishu055.mydemo.activity.view.OptimizationActivity_01;
import com.example.jishu055.mydemo.activity.view.OptimizationActivity_02;
import com.example.jishu055.mydemo.animation.LayoutAnimationActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_01).setOnClickListener(this);
        findViewById(R.id.bt_02).setOnClickListener(this);
        findViewById(R.id.bt_03_a).setOnClickListener(this);
        findViewById(R.id.bt_03_b).setOnClickListener(this);
        findViewById(R.id.bt_04).setOnClickListener(this);
        findViewById(R.id.bt_05).setOnClickListener(this);
        findViewById(R.id.bt_06).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_01://圆角背景
                startActivity(new Intent(this,DrawableActivity.class));
                break;
            case R.id.bt_02://优雅的根据状态改变背景
                startActivity(new Intent(this,ListItemActivity.class));
                break;
            case R.id.bt_03_a://通过android:theme 布局优化前
                startActivity(new Intent(this,OptimizationActivity_01.class));
                break;
            case R.id.bt_03_b://通过android:theme 布局优化后
                startActivity(new Intent(this,OptimizationActivity_02.class));
                break;
            case R.id.bt_04://json和bean的转换
                startActivity(new Intent(this,JsonActivity.class));
            case R.id.bt_05://布局动画
                startActivity(new Intent(this,LayoutAnimationActivity.class));
                break;
            case R.id.bt_06://布局动画
                sendBroadcast(new Intent("ACTION_TEST"));
                break;

        }
    }

    @Override
    public void initData() {
        super.initData();
        Toast.makeText(this,"我靠 真的调用了！",Toast.LENGTH_SHORT).show();
    }
}
