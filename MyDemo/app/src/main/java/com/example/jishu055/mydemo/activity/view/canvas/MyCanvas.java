package com.example.jishu055.mydemo.activity.view.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by jishu055 on 2016/8/10.
 */
public class MyCanvas extends View {

    private Paint mPaint = null;  //用于绘制路径的画笔
    private Path mPath = null; //用户触摸的路径
    private Bitmap desBitmap = null;
    private Canvas mCanvas = null;
    private float preX; //上一次的触摸点x坐标
    private float preY; //上一次触摸点y坐标
    private static final int MIN_SLOP = 5;  //最小的滑动距离

    public MyCanvas(Context context) {
        super(context);
    }

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPath = new Path();


        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);//设置画笔抗锯齿和抗抖动
        mPaint.setStyle(Paint.Style.STROKE); //设置画笔为实心
        mPaint.setStrokeCap(Paint.Cap.ROUND); //设置画笔笔触为圆形
        mPaint.setStrokeJoin(Paint.Join.ROUND); //设置画笔接触点为圆形
        mPaint.setStrokeWidth(30);  //画笔的宽度
        mPaint.setColor(Color.GREEN); //画笔的颜色

        //获取当前屏幕的宽和高
        int screenWidth = getScreenSize(getContext())[0];
        int screenHeight = getScreenSize(getContext())[1];

        //创建一个BITMAP
        desBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        //创建一个和当前bitmap大小相同的画布
        mCanvas = new Canvas(desBitmap);
        //绘制当前画布的填充色
        mCanvas.drawColor(Color.parseColor("#cccccc"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将desBitmap绘制到当前view的canvas中
        canvas.drawBitmap(desBitmap, 0, 0, null);
        //绘制path，即用户滑动的路径
        mCanvas.drawPath(mPath, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        Log.d("Test", "the onTouchEvent runs....");
        //获取当前手指相对当前view接触的x，y坐标
        /**
         * 获取当前手指相对当前手机屏幕坐标，接触的x，y坐标
         * event.getRawX()
         * event.getRawY()
         */
        float currentX = event.getX();
        float currentY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("Test", "the action_down runs....");
                //当用户按下时候，将mPath重置，并且将起始点移动到当前坐标
                mPath.reset();
                mPath.moveTo(currentX, currentY);
                //记录上次触摸的坐标，注意ACTION_DOWN方法只会执行一次
                preX = currentX;
                preY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("Test", "the action_move runs....");
                //ACTION_MOVE方法会多次执行，计算用户水平和垂直方向滑动的距离
                int dx = (int) Math.abs(currentX- preX);
                int dy = (int) Math.abs(currentY - preY);
                //如果滑动的距离大于规定的值
                if (dx > MIN_SLOP || dy > MIN_SLOP) {
                    mPath.quadTo(preX, preY, currentX, currentY);
                    //重新为上一次的触摸点赋值
                    preX = currentX;
                    preY = currentY;
                }
                break;
        }

        invalidate();//重新绘制当前view
        return true;
    }

    public static int[] getScreenSize(Context context) {
        int screenSize[] = new int[2];

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Display metrics = windowManager.getDefaultDisplay();
        screenSize[0] = metrics.getWidth();
        screenSize[1] = metrics.getHeight();
        return screenSize;
    }


}
