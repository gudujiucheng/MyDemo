package com.example.jishu055.mydemo.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by jishu055 on 2016/5/12.
 * 文章地址：http://blog.csdn.net/lmj623565791/article/details/43752383
 */
public class RoundImageDrawable extends Drawable {
    private Paint mPaint;
    private Bitmap mBitmap;

    private RectF rectF;

    public RoundImageDrawable(Bitmap bitmap) {
        //得到图像
        mBitmap = bitmap;
        //构造渲染器BitmapShader
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        mPaint = new Paint();
//        抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
    }

    /**setBounds就是去设置下绘制的范围*/
    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        //核心代码主要是draw，其他都是辅助
        canvas.drawRoundRect(rectF, 30, 30, mPaint);
    }

    /**为了在View使用wrap_content的时候，提供一下尺寸，默认为-1可不是我们希望的*/
    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }

    /**为了在View使用wrap_content的时候，提供一下尺寸，默认为-1可不是我们希望的*/
    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
