package com.example.jishu055.mydemo.activity.view;

import android.os.Bundle;

import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.activity.BaseActivity;

/**
 * 感觉速度上面没什么区别，只是布局上面精简了，但是主题设置增多了内容
 * 也算是提供了另外一种设置背景的方案可供参考
 * 采用设置主题方式的时候，还要注意如果不是直接继承自 Activity 可能还会报错
 * 主要逻辑在清单文件中的设置
 */
public class OptimizationActivity_02 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimization_activity_02);
    }
}
