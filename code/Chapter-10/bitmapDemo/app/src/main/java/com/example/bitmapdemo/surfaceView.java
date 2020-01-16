package com.example.bitmapdemo;

import java.io.InputStream;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class surfaceView extends SurfaceView{
    // 控制surface的接口，提供了控制surface的大小、格式、像素
    private SurfaceHolder surfaceHolder;
    private Canvas canvas = null;
    // x y用户才触摸屏幕的坐标
    private float x=0,y=0;
    private Bitmap bitmap = null;

    public surfaceView(Context context) {
        super(context);
        // 获取SurfaceHolder接口
        surfaceHolder = this.getHolder();
        // 设置屏幕保持开启状态
        this.setKeepScreenOn(true);
        // 获取资源文件res
        Resources res = getResources();
        // 获取位图资源文件的输入流
        InputStream inputStream=res.openRawResource(R.mipmap.myimage);
        // 创建可绘制的位图对象
        BitmapDrawable bitmapDrawable=new BitmapDrawable(inputStream);
        // 通过可绘制位图对象得到位图引用
        bitmap=bitmapDrawable.getBitmap();
		/*
		 * // 获取资源文件的引用res
        Resources res = getResources();
        // 获取图形资源文件
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable. myimage);
		 */
    }

    //绘制位图
    private void onDraw() {
        try {
            // 锁定Canvas画布
            canvas = surfaceHolder.lockCanvas();
            // 设置canvas画布背景为黑色
            canvas.drawColor(Color.BLUE);
            // 在画布上绘制位图
            //让位图的中心正好在触摸点位置上
            canvas.drawBitmap(bitmap, x-bitmap.getWidth()/2, y-bitmap.getHeight()/2, null);
        } catch (Exception ex) {
        } finally {
            if (canvas != null)
                // 解锁画布，并显示绘制图片
                surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
    //触摸事件
    public boolean onTouchEvent(MotionEvent event){
        x = event.getX();
        y = event.getY();
        onDraw();
        return true;
    }

}
