package com.example.jishu055.mydemo.activity.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 简单演示下 画笔和画板的用法
 * Created by zc on 2016/8/10.
 */
public class PaintAndCanvas extends View {


    public PaintAndCanvas(Context context) {
        super(context);
    }

    public PaintAndCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setAntiAlias(true);//抗锯齿
        p.setColor(Color.RED);//设置画笔颜色
        p.setStyle(Paint.Style.FILL);//填充   这里可以试试其他样式
        p.setStrokeWidth(5);//设置画笔宽度
        p.setShadowLayer(10, 10, 10, Color.GREEN);//设置阴影
        //设置画布背景颜色
        canvas.drawRGB(255, 255, 255);

        //画圆
        canvas.drawCircle(190, 200, 150, p);
    }
}
