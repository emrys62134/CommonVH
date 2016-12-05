package com.pei.commonvh;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by dllo on 16/11/7.
 * 显示圆形图片的Drawable
 */
public class CircleDrawable extends Drawable {
    private Bitmap bitmap;//原始图片
    private Paint paint;  //画笔
    private int r; //半径

    public CircleDrawable(Bitmap bitmap) {  //构造方法传进来
        this.bitmap = bitmap;
        paint = new Paint();
        paint.setAntiAlias(true); //抗锯齿
        BitmapShader shader = new BitmapShader(bitmap,
                Shader.TileMode.CLAMP, //图片重复时候使用的模式
                Shader.TileMode.CLAMP);
        paint.setShader(shader);//设置画笔的花纹
        //计算出半径
        r = Math.min(
                bitmap.getHeight() / 2,
                bitmap.getWidth() / 2);

    }

    @Override
    public void draw(Canvas canvas) {
        //画圆
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, r, paint);
//        画三角
//        Path path = new Path();
//        path.moveTo(0,0);
//        path.lineTo(bitmap.getWidth(),0);
//        path.lineTo(bitmap.getWidth()/2,bitmap.getHeight());
//        path.close();
//        canvas.drawPath(path,paint);
        //画五角星
//        Path path = new Path();
//        path.moveTo(bitmap.getWidth()/2,0);
//        path.lineTo();
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    //下面两个方法是必须要写的
    //负责高数Drawable他的宽高是多少

    @Override
    public int getIntrinsicHeight() {
//        return 2*r;
        return bitmap.getHeight();
    }

    @Override
    public int getIntrinsicWidth() {
//        return 2*r;
        return bitmap.getWidth();
    }
}
