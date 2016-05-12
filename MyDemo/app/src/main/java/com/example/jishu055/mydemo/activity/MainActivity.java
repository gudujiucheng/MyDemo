package com.example.jishu055.mydemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jishu055.mydemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_01).setOnClickListener(this);
        findViewById(R.id.bt_02).setOnClickListener(this);
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
        }
    }
}
