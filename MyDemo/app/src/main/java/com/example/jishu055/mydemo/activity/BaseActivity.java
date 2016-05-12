package com.example.jishu055.mydemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.jishu055.mydemo.R;

public class BaseActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("BaseActivity", "DEMO当前所在的activity：" + getClass().getSimpleName());
    }
}
