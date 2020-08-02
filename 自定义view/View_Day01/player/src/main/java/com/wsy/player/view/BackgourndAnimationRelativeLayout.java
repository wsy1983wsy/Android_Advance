package com.wsy.player.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.wsy.player.R;

public class BackgourndAnimationRelativeLayout extends RelativeLayout {
    LayerDrawable layerDrawable;
    ObjectAnimator objectAnimator;

    public BackgourndAnimationRelativeLayout(Context context) {
        this(context, null);
    }

    public BackgourndAnimationRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackgourndAnimationRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Drawable backgroundDrawable = getContext().getDrawable(R.drawable.ic_blackground);
        Drawable[] drawables = new Drawable[2];
        drawables[0] = backgroundDrawable;
        drawables[1] = backgroundDrawable;
        layerDrawable = new LayerDrawable(drawables);
        initAnimator();
    }

    private void initAnimator() {
        objectAnimator = ObjectAnimator.ofFloat(this, "number", 0f, 1.0f);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // animation 0 - 1  0:开始， 1：结束
                //不能在这里判断动画的状态
                float animationValue = (float) animation.getAnimatedValue();
                layerDrawable.getDrawable(1).setAlpha((int) (animationValue * 255));
                setBackground(layerDrawable);
            }
        });

        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                layerDrawable.setDrawable(0, layerDrawable.getDrawable(1));
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
    }

    public void setForeground(Drawable drawable) {
        layerDrawable.setDrawable(1, drawable);
        objectAnimator.start();
    }
}
