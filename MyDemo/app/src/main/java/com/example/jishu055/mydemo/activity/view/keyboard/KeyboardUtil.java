package com.example.jishu055.mydemo.activity.view.keyboard;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jishu055.mydemo.R;

public class KeyboardUtil {
    private Context context;

    private KeyboardView keyboardView;

    private Keyboard k2;// 数字键盘

    public boolean isnun = true;// 是否数据键盘

    // private TextView ed;
    private InputFinishListener inputOver;

    TextView[] textViews = new TextView[6];

    LinearLayout layout_parent;

    private Animation animation;

    /**
     * @param act               软键盘布局
     * @param context
     * @param layout_parent     密码输入框
     * @param inputOverListener 输入完毕的回调监听
     */
    public KeyboardUtil(RelativeLayout act, Context context, LinearLayout layout_parent, InputFinishListener inputOverListener) {
        this.context = context;
        this.layout_parent = layout_parent;
        initTextViews();

        //创建数字键盘
        k2 = new Keyboard(context, R.xml.symbols_num);
        keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);
        keyboardView.setEnabled(true);
        keyboardView.setKeyboard(k2);
        //是否可以预览(预览貌似是一句pop来弹出的，如果外层已经嵌套pop会抛出异常，可不用预览，也可以外层使用其他方式处理)
        keyboardView.setPreviewEnabled(false);

        keyboardView.setOnKeyboardActionListener(listener);
        this.inputOver = inputOverListener;

        List<Key> keys = keyboardView.getKeyboard().getKeys();
        //遍历设置两个按钮的初始状态 删除 和隐藏
        for (Key key : keys) {
            if (key.codes[0] == Keyboard.KEYCODE_MODE_CHANGE || key.codes[0] == Keyboard.KEYCODE_DELETE) {
                key.onPressed();
            }
        }
    }

    /**
     * 初始化密码输入框
     */
    private void initTextViews() {
        //长度6
        for (int i = 0; i < textViews.length; i++) {
            textViews[i] = new TextView(context);
            LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
            //线性布局通过weight平均宽度
            params.weight = 1;
            textViews[i].setLayoutParams(params);
            layout_parent.addView(textViews[i]);
            //密码输入
            textViews[i].setTransformationMethod(PasswordTransformationMethod.getInstance());
            textViews[i].setGravity(Gravity.CENTER);
            textViews[i].setTextSize(40);
            //添加0.8dp的竖线分割
            if (i < textViews.length - 1) {//控制最后面不加
                View view = new View(context);
                LayoutParams viewParams = new LayoutParams((int) context.getResources().getDimension(
                        R.dimen.width_input_tv_cutline), LayoutParams.FILL_PARENT);
                view.setLayoutParams(viewParams);
                view.setBackgroundColor(Color.parseColor("#D8D8D8"));
                layout_parent.addView(view);
            }

        }
    }

    private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            if (primaryCode == Keyboard.KEYCODE_DELETE) {//删除
                deleteTextView();
            } else if (primaryCode != Keyboard.KEYCODE_MODE_CHANGE) {//字符输入
                inputTextView(Character.toString((char) primaryCode));
            } else {//隐藏
                hideKeyboard();
            }

        }

    };

    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(context, R.anim.push_up_in);
            keyboardView.startAnimation(animation);
        }
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.INVISIBLE);
            animation = AnimationUtils.loadAnimation(context, R.anim.keyboard_push_down);
            keyboardView.startAnimation(animation);
        }
    }

    private boolean isword(String str) {
        String wordstr = "abcdefghijklmnopqrstuvwxyz";
        if (wordstr.indexOf(str.toLowerCase()) > -1) {
            return true;
        }
        return false;
    }

    /**
     * 字符输入
     *
     * @param code
     */
    private void inputTextView(String code) {
        for (int i = 0; i < textViews.length; i++) {
            TextView tv = textViews[i];
            if (tv.getText().toString().equals("")) {//每次都遍历，输入到最靠前的并且内容为空的框里，然后返回。
                tv.setText(code);
                if (i == textViews.length - 1) {//判断是否已经输入完毕
                    inputOver.inputHasOver(getInputText());
                }
                return;
            }
        }
    }

    /**
     * 一个一个的删除已经输入的字符
     */
    private void deleteTextView() {
        for (int i = textViews.length - 1; i >= 0; i--) {
            TextView tv = textViews[i];
            if (!tv.getText().toString().equals("")) {//遍历，只要遇到有一个不为空的，就跳出循环（也就是一个个删的意思）
                tv.setText("");
                return;
            }
        }
    }

    /**
     * 清空所有已经输入的字符
     * 在输入错误的密码等情况使用
     */
    public void deleteAllTextView() {
        for (int i = textViews.length - 1; i >= 0; i--) {
            TextView tv = textViews[i];
            if (!tv.getText().toString().equals("")) {
                tv.setText("");
            }
        }
    }

    /**
     * 获取已经输入的字符
     *
     * @return
     */
    private String getInputText() {
        StringBuffer sb = new StringBuffer();
        for (TextView tv : textViews) {
            sb.append(tv.getText().toString());
        }
        return sb.toString();
    }

    /**
     * @description:TODO 输入监听
     * @FileName KeyboardUtil.java
     * @user wzl email:wzl200711402@163.com
     * @date 2014-3-19
     */
    public interface InputFinishListener {
        public void inputHasOver(String text);
    }

}