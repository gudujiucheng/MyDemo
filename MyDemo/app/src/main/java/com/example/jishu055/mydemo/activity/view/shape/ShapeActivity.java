package com.example.jishu055.mydemo.activity.view.shape;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.activity.BaseActivity;

public class ShapeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
    }

    public void haha(View v){
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.alpha=0.7f;
        getWindow().setAttributes(params);

    }
}
