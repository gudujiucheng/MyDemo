package com.example.jishu055.mydemo.activity.view.keyboard;

import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.activity.BaseActivity;
import com.example.jishu055.mydemo.activity.MainActivity;

public class KeyBoardActivity extends BaseActivity {

    //密码输入pop
    private PopupWindow popupWindow;
    //密码pop布局
    private View mView;
    //软键盘父布局
    private RelativeLayout act;
    //密码输入框
    private LinearLayout layout_input;
    //软键盘工具类
    private KeyboardUtil keyBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);
        initPopup();
    }


    private void initPopup() {
        popupWindow = new PopupWindow(mView = View.inflate(this, R.layout.pop_pay_keyboard, null), ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);

        //这里很重要，不设置这个ListView（如果布局里面有listview需要操作的话）得不到相应
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());//这个不设置会导致点击外面不消失的状况

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {//这个监听可以处理pop消失的时候的一些操作，比如之前做的箭头指示。
            @Override
            public void onDismiss() {
            }
        });
        //针对mView 进行处理，实现点击外部不消失，但是要相应返回键的动作。
        mView.setFocusableInTouchMode(true);
        mView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });

        initKeyBoard();
    }

    private void initKeyBoard() {
        act = (RelativeLayout) mView.findViewById(R.id.keyboard_layout);
        layout_input = (LinearLayout) mView.findViewById(R.id.layout_input);

        //通过工具类 生成软键盘
        keyBoard = new KeyboardUtil(act, this, layout_input, new KeyboardUtil.InputFinishListener() {

            @Override
            public void inputHasOver(String text) {//输入完毕的回调
                //弹窗测试
                AlertDialog.Builder builder = new AlertDialog.Builder(KeyBoardActivity.this);
                builder.setMessage(text).setTitle("您输入的密码是：").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            }
        });

        layout_input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                keyBoard.showKeyboard();
                return false;
            }
        });
    }

    public void showPop(View view) {
        popupWindow.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
    }

    public void hidePop(View view) {
        popupWindow.dismiss();
    }

}
