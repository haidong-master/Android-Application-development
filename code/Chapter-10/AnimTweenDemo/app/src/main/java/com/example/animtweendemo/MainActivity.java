package com.example.animtweendemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity implements AnimationListener{

    public static final String TAG = "MainActivity";

    // 动画图片
    private ImageView          tweenImage;

    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    public void onCreate(Bundle cycle) {
        super.onCreate(cycle);
        super.setContentView(R.layout.activity_main);
        // 取得动画图片
        this.tweenImage = (ImageView) super.findViewById(R.id.TweenImage);
    }

    /**
     * 按钮：尺寸变化动画
     */
    public void onBtnScaleAnimClick(View view) {
        // 动画开始
        this.doStartAnimation(R.anim.scale);
    }

    /**
     * 按钮：渐变动画
     */
    public void onBtnAlphaAnimClick(View view) {
        // 动画开始
        this.doStartAnimation(R.anim.alpha);
    }

    /**
     * 按钮：位置变化动画
     */
    public void onBtnTranslateAnimClick(View view) {
        // 动画开始
        this.doStartAnimation(R.anim.translate);
    }

    /**
     * 按钮：旋转动画
     */
    public void onBtnRotateAnimClick(View view) {
        // 动画开始
        this.doStartAnimation(R.anim.rotate);
    }

    /**
     * 开始动画
     */
    private void doStartAnimation(int animId) {
        // 加载动画
        Animation animation = AnimationUtils.loadAnimation(this, animId);
        // 动画开始
        this.tweenImage.startAnimation(animation);
    }

    @Override
    public void onAnimationEnd(Animation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

}
