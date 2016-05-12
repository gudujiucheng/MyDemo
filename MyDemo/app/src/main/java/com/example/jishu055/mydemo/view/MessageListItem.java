package com.example.jishu055.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.jishu055.mydemo.R;

/**
 * Created by jishu055 on 2016/5/12.
 */
public class MessageListItem extends RelativeLayout {
    private static final int[] STATE_MESSAGE_READED = {R.attr.state_message_readed};
    private boolean mMessgeReaded = false;

    public MessageListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置控件状态
     * @param readed
     */
    public void setMessageReaded(boolean readed) {
        if (this.mMessgeReaded != readed) {
            mMessgeReaded = readed;
            refreshDrawableState();
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        if (mMessgeReaded) {
            final int[] drawableState = super
                    .onCreateDrawableState(extraSpace + 1);
            mergeDrawableStates(drawableState, STATE_MESSAGE_READED);
            return drawableState;
        }
        return super.onCreateDrawableState(extraSpace);
    }
}
