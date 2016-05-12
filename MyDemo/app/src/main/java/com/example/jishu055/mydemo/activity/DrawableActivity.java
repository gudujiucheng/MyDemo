package com.example.jishu055.mydemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.view.drawable.CircleImageDrawable;
import com.example.jishu055.mydemo.view.drawable.RoundImageDrawable;


public class DrawableActivity extends AppCompatActivity {
    Bitmap bitmap;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        iv = (ImageView) findViewById(R.id.iv_drawable);
        bitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.test);
    }

    int i = 0;
    boolean change = true;

    public void change(View v) {
        switch (i % 3) {
            case 0:
                iv.setImageDrawable(new RoundImageDrawable(bitmap));
                break;
            case 1:
                if (change) {
                    v.setBackgroundDrawable(new RoundImageDrawable(bitmap));
                    ((Button) v).setText("可以设置任何控件的背景！");
                } else {
                    v.setBackgroundDrawable(null);
                    ((Button) v).setText("点击切换");
                }
                change = !change;

                break;
            case 2:
                iv.setImageDrawable(new CircleImageDrawable(bitmap));
                break;
        }
        i++;

    }
}
