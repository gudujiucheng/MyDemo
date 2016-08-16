package com.example.jishu055.mydemo.activity.view.customview;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.activity.BaseActivity;
import com.example.jishu055.mydemo.activity.view.canvas.MyCanvas;

public class CustomViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this,"haha",Toast.LENGTH_SHORT).show();
    }
}
