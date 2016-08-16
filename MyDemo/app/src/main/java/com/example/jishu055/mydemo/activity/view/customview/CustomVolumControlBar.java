package com.example.jishu055.mydemo.activity.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.jishu055.mydemo.R;

/**
 * Created by jishu055 on 2016/8/15.
 */
public class CustomVolumControlBar extends View {

    /**
     * 第一圈的颜色
     */
    private int mFirstColor;

    /**
     * 第二圈的颜色
     */
    private int mSecondColor;
    /**
     * 圈的宽度
     */
    private int mCircleWidth;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 当前进度
     */
    private int mCurrentCount = 0;

    /**
     * 中间的图片
     */
    private Bitmap mImage;
    /**
     * 每个块块间的间隙
     */
    private int mSplitSize;
    /**
     * 个数
     */
    private int mCount;

    private Rect mRect;

    public CustomVolumControlBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVolumControlBar(Context context) {
        this(context, null);
    }

    /**
     * 必要的初始化，获得一些自定义的值
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomVolumControlBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //获取属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomVolumControlBar, defStyle, 0);
        int n = a.getIndexCount();

        //遍历属性
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomVolumControlBar_firstColor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CustomVolumControlBar_secondColor:
                    mSecondColor = a.getColor(attr, Color.CYAN);
                    break;
                case R.styleable.CustomVolumControlBar_bg:
                    mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomVolumControlBar_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomVolumControlBar_dotCount:
                    mCount = a.getInt(attr, 20);// 默认20
                    break;
                case R.styleable.CustomVolumControlBar_splitSize:
                    mSplitSize = a.getInt(attr, 20);
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();
        mRect = new Rect();


        // 绘图线程
        new Thread() {
            public void run() {
                while (true) {

                    if (mCurrentCount < mCount) {
                        for (int i = 0; i < mCount - mCurrentCount; i++) {
                            mCurrentCount++;
                            refresh();
                        }
                    } else {
                        for (int i = mCurrentCount; i > 0; i--) {
                            mCurrentCount--;
                            refresh();
                        }
                    }


                }
            }

            ;
        }.start();
    }


    /**
     * 刷新界面，并且延迟50毫秒
     */
    private void refresh() {
        postInvalidate();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setStrokeCap(Paint.Cap.ROUND); // 定义线段断电形状为圆头（如果不设置这个，画圆界面的断点就不是圆角的了）
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = centre - mCircleWidth / 2;// 半径
        /**
         * 画块块去
         */
        drawOval(canvas, centre, radius);

        /**
         * 计算内切正方形的位置
         */
        int relRadius = radius - mCircleWidth / 2;// 获得内圆的半径
        /**
         * 内切正方形的距离顶部 = mCircleWidth + relRadius - √2 / 2
         */
        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        /**
         * 内切正方形的距离左边 = mCircleWidth + relRadius - √2 / 2
         */
        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);

        /**
         * 如果图片比较小，那么根据图片的尺寸放置到正中心
         */
        if (mImage.getWidth() < Math.sqrt(2) * relRadius) {
            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getWidth() * 1.0f / 2);
            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);
            mRect.right = (int) (mRect.left + mImage.getWidth());
            mRect.bottom = (int) (mRect.top + mImage.getHeight());

        }
        // 绘图
        canvas.drawBitmap(mImage, null, mRect, mPaint);
    }

    /**
     * 根据参数画出每个小块
     *
     * @param canvas
     * @param centre
     * @param radius
     */
    private void drawOval(Canvas canvas, int centre, int radius) {
        /**
         * 根据需要画的个数以及间隙计算每个块块所占的比例*360
         */
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;

        //圆的中心位置centre和半径相加减算出圆形外层的的矩形界限  左上右下
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限

        mPaint.setColor(mFirstColor); // 设置圆环的颜色
        for (int i = 0; i < mCount; i++) {
//            oval :指定圆弧的外轮廓矩形区域。
//            startAngle: 圆弧起始角度，单位为度。
//            sweepAngle: 圆弧扫过的角度，顺时针方向，单位为度,从右中间开始为零度。
//            useCenter: 如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形。
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint); // 根据进度画圆弧
        }

        mPaint.setColor(mSecondColor); // 设置圆环的颜色
        for (int i = 0; i < mCurrentCount; i++) {
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint); // 根据进度画圆弧
        }
    }


    /**
     * 当前数量+1
     */
    public void up() {
        mCurrentCount++;
        postInvalidate();
    }

    /**
     * 当前数量-1
     */
    public void down() {
        mCurrentCount--;
        postInvalidate();
    }

    private int xDown, xUp;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = (int) event.getY();
                break;

            case MotionEvent.ACTION_UP:
                xUp = (int) event.getY();
                if (xUp > xDown)// 下滑
                {
                    down();
                } else {
                    up();
                }
                break;
        }

        return true;
    }

}
