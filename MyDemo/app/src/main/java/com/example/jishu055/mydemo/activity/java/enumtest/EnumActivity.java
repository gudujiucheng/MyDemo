package com.example.jishu055.mydemo.activity.java.enumtest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.activity.BaseActivity;

public class EnumActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enum);
    }

    public void justGo(View view) {
        Log.e("Test", EnumTest.MON.name());
        Log.e("Test", EnumTest.MON.toString());
        Log.e("Test", EnumTest.MON.ordinal()+"");


        //枚举的遍历
        for (EnumTest e : EnumTest.values()) {
            System.out.println(e.toString());
        }

        System.out.println("----------------我是分隔线------------------");

        EnumTest test = EnumTest.TUE;
        switch (test) {
            case MON:
                System.out.println("今天是星期一");
                break;
            case TUE:
                System.out.println("今天是星期二");
                break;
            // ... ...
            default:
                System.out.println(test);
                break;
        }


        //测试自定义方法重写
        Log.e("Test", EnumTestWithValue.MON.name());
        Log.e("Test", EnumTestWithValue.MON.isRest()+"");
        Log.e("Test", EnumTestWithValue.TUE.name()+"");
        Log.e("Test", EnumTestWithValue.TUE.isRest()+"");
    }
}
