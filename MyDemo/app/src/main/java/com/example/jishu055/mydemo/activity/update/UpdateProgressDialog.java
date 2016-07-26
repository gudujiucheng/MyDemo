package com.example.jishu055.mydemo.activity.update;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jishu055.mydemo.R;


/**
 * 版本更新进度弹窗
 */
public class UpdateProgressDialog extends Dialog implements View.OnClickListener {

    private TextView mTextView;

    private ProgressBar mProgressBar;

    private OnCancelListener mListener;

    public UpdateProgressDialog(Context context) {
        this(context, R.style.Dialog_UpgradeVerion);
    }

    public UpdateProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        initialize();
    }

    /**
     * 初始化
     */
    private void initialize() {
        setContentView(R.layout.dialog_update_progress);
        findViewById(R.id.btn_upgrade_progress_cancel).setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.tv_upgrade_progress);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_upgrade);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        WindowManager wm = window.getWindowManager();
        Display d = wm.getDefaultDisplay();
        params.width = (int) (d.getWidth() * 0.9);
        window.setAttributes(params);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_upgrade_progress_cancel://取消
                mListener.onCancel(this);
                break;
        }
    }

    /**
     * 设置取消按钮监听
     *
     * @param listener
     */
    public void setNegativeButton(OnCancelListener listener) {
        mListener = listener;
    }

    /**
     * 取消按钮监听
     */
    public interface OnCancelListener {
        public void onCancel(DialogInterface dialog);
    }

    /**
     * 设置进度条进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        mProgressBar.setProgress(progress);
    }

    /**
     * 设置进度提示文本
     *
     * @param text
     */
    public void setProgressText(String text) {
        mTextView.setText(text);
    }
}
