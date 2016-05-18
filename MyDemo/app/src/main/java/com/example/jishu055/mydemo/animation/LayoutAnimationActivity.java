package com.example.jishu055.mydemo.animation;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.activity.BaseActivity;
import com.example.jishu055.mydemo.activity.WebActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 布局动画
 * 2016年5月18日14:41:023
 * http://blog.csdn.net/lmj623565791/article/details/38092093
 */

//LayoutTransition transition = new LayoutTransition();
//        transition.setAnimator(LayoutTransition.CHANGE_APPEARING,
//        transition.setAnimator(LayoutTransition.APPEARING,null);
//        transition.setAnimator(LayoutTransition.DISAPPEARING,null);
//        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,null);
//        mGridLayout.setLayoutTransition(transition);


//过渡的类型一共有四种：
//        LayoutTransition.APPEARING 当一个View在ViewGroup中出现时，对此View设置的动画
//        LayoutTransition.CHANGE_APPEARING 当一个View在ViewGroup中出现时，对此View对其他View位置造成影响，对其他View设置的动画
//        LayoutTransition.DISAPPEARING  当一个View在ViewGroup中消失时，对此View设置的动画
//        LayoutTransition.CHANGE_DISAPPEARING 当一个View在ViewGroup中消失时，对此View对其他View位置造成影响，对其他View设置的动画
//        LayoutTransition.CHANGE 不是由于View出现或消失造成对其他View位置造成影响，然后对其他View设置的动画。
//        注意动画到底设置在谁身上，此View还是其他View。
public class LayoutAnimationActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {




    private ViewGroup viewGroup;
    private GridLayout mGridLayout;
    private int mVal;
    private LayoutTransition mTransition;

    private CheckBox mAppear, mChangeAppear, mDisAppear, mChangeDisAppear;

    private LinearLayout mLayOutTest;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);
        viewGroup = (ViewGroup) findViewById(R.id.id_container);

        //出现时候
        mAppear = (CheckBox) findViewById(R.id.id_appear);
        //出现时候对其他控件的影响
        mChangeAppear = (CheckBox) findViewById(R.id.id_change_appear);
        //消失时候
        mDisAppear = (CheckBox) findViewById(R.id.id_disappear);
        //消失时候对其他控件的影响
        mChangeDisAppear = (CheckBox) findViewById(R.id.id_change_disappear);

        mAppear.setOnCheckedChangeListener(this);
        mChangeAppear.setOnCheckedChangeListener(this);
        mDisAppear.setOnCheckedChangeListener(this);
        mChangeDisAppear.setOnCheckedChangeListener(this);

        // 创建一个GridLayout   网格布局
        mGridLayout = new GridLayout(this);
        // 设置每列5个按钮
        mGridLayout.setColumnCount(5);
        // 添加到布局中
        viewGroup.addView(mGridLayout);
        //默认动画全部开启
        mTransition = new LayoutTransition();
        mGridLayout.setLayoutTransition(mTransition);

        mLayOutTest = (LinearLayout)findViewById(R.id.layout_test);
        mLayOutTest.setLayoutTransition(mTransition);

    }

    /**
     * 添加按钮
     *
     * @param view
     */
    public void addBtn(View view)
    {
        final Button button = new Button(this);
        button.setText((++mVal) + "");
        mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));
        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                mGridLayout.removeView(button);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        mTransition = new LayoutTransition();
        mTransition.setAnimator(
                LayoutTransition.APPEARING,
                (mAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.APPEARING) : null));
        mTransition
                .setAnimator(
                        LayoutTransition.CHANGE_APPEARING,
                        (mChangeAppear.isChecked() ? mTransition
                                .getAnimator(LayoutTransition.CHANGE_APPEARING)
                                : null));
        mTransition.setAnimator(
                LayoutTransition.DISAPPEARING,
                (mDisAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.DISAPPEARING) : null));
        mTransition.setAnimator(
                LayoutTransition.CHANGE_DISAPPEARING,
                (mChangeDisAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.CHANGE_DISAPPEARING)
                        : null));
        //TODO 注意这里需要在设置一次，要不还是上次的状态
        mGridLayout.setLayoutTransition(mTransition);
        mLayOutTest.setLayoutTransition(mTransition);
    }


    /**=========================================分割线，之下是自己的测试================================================*/

    public void myTest(View v){
        final Button bt = new Button(this);
        bt.setText("哈哈哈");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt.setVisibility(View.GONE);
            }
        });
        mLayOutTest.addView(bt);
    }

    public void goTOWeb(View v){
//        Intent in = new Intent(this, WebActivity.class);
//        in.putExtra("WEB_URL","http://blog.csdn.net/lmj623565791/article/details/38092093");
//        startActivity(in);
        Uri parse = Uri.parse("http://blog.csdn.net/lmj623565791/article/details/38092093");
        startActivity(new Intent(Intent.ACTION_VIEW,parse));
    }
}
