package com.luffy.generalutilslib.utils;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 动画-辅助工具
 */
public class AnimationUtils {

    /**
     * 默认动画持续时间
     */
    private final long DEFAULT_ANIMATION_DURATION = 2000;

    private AnimationUtils() {
    }

    public static AnimationUtils getInstance() {
        return AnimationUtilsHelper.mAnimationUtils;
    }

    private static class AnimationUtilsHelper {
        private static final AnimationUtils mAnimationUtils = new AnimationUtils();
    }

    /**
     * 获取一个旋转动画
     *
     * @param fromDegrees       开始角度
     * @param toDegrees         结束角度
     * @param pivotXType        旋转中心点X轴坐标相对类型
     * @param pivotXValue       旋转中心点X轴坐标
     * @param pivotYType        旋转中心点Y轴坐标相对类型
     * @param pivotYValue       旋转中心点Y轴坐标
     * @param durationMillis    持续时间
     * @param animationListener 动画监听器
     * @return 一个旋转动画
     */
    public RotateAnimation getRotateAnimationBase(float fromDegrees,
                                                  float toDegrees,
                                                  int pivotXType,
                                                  float pivotXValue,
                                                  int pivotYType,
                                                  float pivotYValue,
                                                  long durationMillis,
                                                  Animation.AnimationListener animationListener) {
        RotateAnimation rotateAnimation = new RotateAnimation(
                fromDegrees,
                toDegrees,
                pivotXType,
                pivotXValue,
                pivotYType,
                pivotYValue);
        rotateAnimation.setDuration(durationMillis);
        if (animationListener != null) {
            rotateAnimation.setAnimationListener(animationListener);
        }
        return rotateAnimation;
    }

    /**
     * 获取一个根据视图自身中心点旋转的动画
     *
     * @param durationMillis    动画持续时间
     * @param animationListener 动画监听器
     * @return 一个根据中心点旋转的动画
     */
    public RotateAnimation getRotateAnimationByCenter(long durationMillis,
                                                      Animation.AnimationListener animationListener) {
        return getRotateAnimationBase(
                0f,
                359f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                durationMillis,
                animationListener);
    }

    /**
     * 获取一个根据中心点旋转的动画
     *
     * @param duration 动画持续时间
     * @return 一个根据中心点旋转的动画
     */
    public RotateAnimation getRotateAnimationByCenter(long duration) {
        return getRotateAnimationByCenter(duration,
                null);
    }

    /**
     * 获取一个根据视图自身中心点旋转的动画
     *
     * @param animationListener 动画监听器
     * @return 一个根据中心点旋转的动画
     */
    public RotateAnimation getRotateAnimationByCenter(Animation.AnimationListener animationListener) {
        return getRotateAnimationByCenter(DEFAULT_ANIMATION_DURATION,
                animationListener);
    }

    /**
     * 获取一个根据中心点旋转的动画
     *
     * @return 一个根据中心点旋转的动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public RotateAnimation getRotateAnimationByCenter() {
        return getRotateAnimationByCenter(DEFAULT_ANIMATION_DURATION,
                null);
    }

    /**
     * 获取一个透明度渐变动画
     *
     * @param fromAlpha         开始时的透明度
     * @param toAlpha           结束时的透明度都
     * @param durationMillis    持续时间
     * @param animationListener 动画监听器
     * @return 一个透明度渐变动画
     */
    public AlphaAnimation getAlphaAnimationBase(float fromAlpha,
                                                float toAlpha,
                                                long durationMillis,
                                                Animation.AnimationListener animationListener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
        alphaAnimation.setDuration(durationMillis);
        if (animationListener != null) {
            alphaAnimation.setAnimationListener(animationListener);
        }
        return alphaAnimation;
    }

    /**
     * 获取一个透明度渐变动画
     *
     * @param fromAlpha      开始时的透明度
     * @param toAlpha        结束时的透明度都
     * @param durationMillis 持续时间
     * @return 一个透明度渐变动画
     */
    public AlphaAnimation getAlphaAnimation(float fromAlpha,
                                            float toAlpha,
                                            long durationMillis) {
        return getAlphaAnimationBase(fromAlpha,
                toAlpha,
                durationMillis,
                null);
    }

    /**
     * 获取一个透明度渐变动画
     *
     * @param fromAlpha         开始时的透明度
     * @param toAlpha           结束时的透明度都
     * @param animationListener 动画监听器
     * @return 一个透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public AlphaAnimation getAlphaAnimation(float fromAlpha,
                                            float toAlpha,
                                            Animation.AnimationListener animationListener) {
        return getAlphaAnimationBase(fromAlpha,
                toAlpha,
                DEFAULT_ANIMATION_DURATION,
                animationListener);
    }

    /**
     * 获取一个透明度渐变动画
     *
     * @param fromAlpha 开始时的透明度
     * @param toAlpha   结束时的透明度都
     * @return 一个透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public AlphaAnimation getAlphaAnimation(float fromAlpha,
                                            float toAlpha) {
        return getAlphaAnimationBase(fromAlpha,
                toAlpha,
                DEFAULT_ANIMATION_DURATION,
                null);
    }

    /**
     * 获取一个由完全显示变为不可见的透明度渐变动画
     *
     * @param durationMillis    持续时间
     * @param animationListener 动画监听器
     * @return 一个由完全显示变为不可见的透明度渐变动画
     */
    public AlphaAnimation getHiddenAlphaAnimation(long durationMillis,
                                                  Animation.AnimationListener animationListener) {
        return getAlphaAnimationBase(1.0f,
                0.0f,
                durationMillis,
                animationListener);
    }

    /**
     * 获取一个由完全显示变为不可见的透明度渐变动画
     *
     * @param durationMillis 持续时间
     * @return 一个由完全显示变为不可见的透明度渐变动画
     */
    public AlphaAnimation getHiddenAlphaAnimation(long durationMillis) {
        return getHiddenAlphaAnimation(durationMillis, null);
    }

    /**
     * 获取一个由完全显示变为不可见的透明度渐变动画
     *
     * @param animationListener 动画监听器
     * @return 一个由完全显示变为不可见的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public AlphaAnimation getHiddenAlphaAnimation(Animation.AnimationListener animationListener) {
        return getHiddenAlphaAnimation(DEFAULT_ANIMATION_DURATION,
                animationListener);
    }

    /**
     * 获取一个由完全显示变为不可见的透明度渐变动画
     *
     * @return 一个由完全显示变为不可见的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public AlphaAnimation getHiddenAlphaAnimation() {
        return getHiddenAlphaAnimation(DEFAULT_ANIMATION_DURATION,
                null);
    }

    /**
     * 获取一个由不可见变为完全显示的透明度渐变动画
     *
     * @param durationMillis    持续时间
     * @param animationListener 动画监听器
     * @return 一个由不可见变为完全显示的透明度渐变动画
     */
    public AlphaAnimation getShowAlphaAnimation(long durationMillis,
                                                Animation.AnimationListener animationListener) {
        return getAlphaAnimationBase(0.0f,
                1.0f,
                durationMillis,
                animationListener);
    }

    /**
     * 获取一个由不可见变为完全显示的透明度渐变动画
     *
     * @param durationMillis 持续时间
     * @return 一个由不可见变为完全显示的透明度渐变动画
     */
    public AlphaAnimation getShowAlphaAnimation(long durationMillis) {
        return getAlphaAnimationBase(0.0f,
                1.0f,
                durationMillis,
                null);
    }

    /**
     * 获取一个由不可见变为完全显示的透明度渐变动画
     *
     * @param animationListener 动画监听器
     * @return 一个由不可见变为完全显示的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public AlphaAnimation getShowAlphaAnimation(Animation.AnimationListener animationListener) {
        return getAlphaAnimationBase(0.0f,
                1.0f,
                DEFAULT_ANIMATION_DURATION,
                animationListener);
    }

    /**
     * 获取一个由不可见变为完全显示的透明度渐变动画
     *
     * @return 一个由不可见变为完全显示的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public AlphaAnimation getShowAlphaAnimation() {
        return getAlphaAnimationBase(0.0f,
                1.0f,
                DEFAULT_ANIMATION_DURATION,
                null);
    }

    /**
     * 获取一个缩小动画
     *
     * @param durationMillis    时间
     * @param animationListener 监听
     * @return 一个缩小动画
     */
    public ScaleAnimation getLessenScaleAnimation(long durationMillis,
                                                  Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,
                0.0f,
                1.0f,
                0.0f,
                ScaleAnimation.RELATIVE_TO_SELF,
                ScaleAnimation.RELATIVE_TO_SELF);
        scaleAnimation.setDuration(durationMillis);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }

    /**
     * 获取一个缩小动画
     *
     * @param durationMillis 时间
     * @return 一个缩小动画
     */
    public ScaleAnimation getLessenScaleAnimation(long durationMillis) {
        return getLessenScaleAnimation(durationMillis, null);
    }

    /**
     * 获取一个缩小动画
     *
     * @param animationListener 监听
     * @return 返回一个缩小的动画
     */
    public ScaleAnimation getLessenScaleAnimation(Animation.AnimationListener animationListener) {
        return getLessenScaleAnimation(DEFAULT_ANIMATION_DURATION,
                animationListener);
    }

    /**
     * 获取一个放大动画
     *
     * @param durationMillis    时间
     * @param animationListener 监听
     * @return 返回一个放大的效果
     */
    public ScaleAnimation getAmplificationAnimation(long durationMillis,
                                                    Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,
                1.0f,
                0.0f,
                1.0f,
                ScaleAnimation.RELATIVE_TO_SELF,
                ScaleAnimation.RELATIVE_TO_SELF);
        scaleAnimation.setDuration(durationMillis);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }

    /**
     * 获取一个放大动画
     *
     * @param durationMillis 时间
     * @return 返回一个放大的效果
     */
    public ScaleAnimation getAmplificationAnimation(long durationMillis) {
        return getAmplificationAnimation(durationMillis, null);
    }

    /**
     * 获取一个放大动画
     *
     * @param animationListener 监听
     * @return 返回一个放大的效果
     */
    public ScaleAnimation getAmplificationAnimation(Animation.AnimationListener animationListener) {
        return getAmplificationAnimation(DEFAULT_ANIMATION_DURATION,
                animationListener);
    }

    /**
     * 获取一个移动动画
     *
     * @param view  控件
     * @param start 开始
     * @param end   结束
     * @return 动画对象
     */
    public ValueAnimator getValueAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}