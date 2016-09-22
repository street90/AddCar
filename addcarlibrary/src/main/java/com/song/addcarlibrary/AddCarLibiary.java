package com.song.addcarlibrary;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by song on 2016/9/15.
 */
public class AddCarLibiary {

    /**
     *
     * @param startView 起始位置的图片
     * @param endView 结束位置图片
     * @param mContext
     * @param parentView  //用于添加的动画的View 父布局
     * @param time //动画时间
     */
    public static void AddCar(ImageView startView, View endView, Context mContext, final RelativeLayout parentView, final int time, final addCarEndListener listener) {



        /**
         *创建一个View用于动画显示
         */
        final ImageView view = new ImageView(mContext);
        view.setImageDrawable(startView.getDrawable());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(startView.getWidth(),startView.getHeight());


        /**
         * 父布局的位置
         */
        int[] parentLocation = new int[2];
        parentView.getLocationInWindow(parentLocation);

        /**
         * 开始view的位置
         */
        int[] startLocation = new int[2];
        startView.getLocationInWindow(startLocation);

        /**
         * 购物车的位置
         */
        int[] endLocation = new int[2];
        endView.getLocationInWindow(endLocation);

        params.leftMargin = startLocation[0] - parentLocation[0] - parentView.getPaddingLeft();
        params.topMargin = startLocation[1] - parentLocation[1] - parentView.getPaddingTop();

        parentView.addView(view,params);

        /**
         * x方向的移动距离
         */
        int xL = endLocation[0] - startLocation[0] + endView.getWidth() / 2 - startView.getWidth() / 2;

        /**
         * x方向的速度
         */
        final float xV = xL/time;

        /**
         * y方向的移动距离
         */
        int yL = endLocation[1] - startLocation[1] + endView.getHeight() / 2 - startView.getHeight() / 2;

        /**
         * y方向的加速度
         */
        final float yA = yL*2/time/time;


        ValueAnimator va = new ValueAnimator();
        va.setDuration(time * 1000);
        va.setObjectValues(new PointF(0, 0));

        va.setEvaluator(new TypeEvaluator<PointF>(){
            @Override
            public PointF evaluate(float x, PointF pointF, PointF t1) {

                PointF point = new PointF();
                point.x = x* xV*time;

                point.y = yA * (x * time) * (x* time) / 2;

                return point;
            }
        });

        va.start();

        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF point = (PointF) valueAnimator.getAnimatedValue();
                view.setTranslationX(point.x);
                view.setTranslationY(point.y);
            }
        });

        //在动画结束时去掉动画添加的ImageView
        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                parentView.removeView(view);
                listener.end();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }
}
