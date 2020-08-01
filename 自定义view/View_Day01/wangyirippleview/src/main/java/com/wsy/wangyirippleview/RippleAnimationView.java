package com.wsy.wangyirippleview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.wsy.wangyirippleview.ui.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class RippleAnimationView extends RelativeLayout {
    private Paint paint;
    int rippleColor;
    int radius;
    private int strokeWidth;
    private List<RippleCircleView> circleViews;
    private boolean playingAnimation;
    AnimatorSet animatorSet;

    public Paint getPaint() {
        return paint;
    }

    public int getRippleColor() {
        return rippleColor;
    }

    public int getRadius() {
        return radius;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public RippleAnimationView(Context context) {
        this(context, null);
    }

    public RippleAnimationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        circleViews = new ArrayList<>();
        paint = new Paint();
        paint.setAntiAlias(true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleAnimationView);
        int rippleType = typedArray.getInt(R.styleable.RippleAnimationView_ripple_anim_type, 0);
        radius = typedArray.getInteger(R.styleable.RippleAnimationView_radius, 54);
        strokeWidth = typedArray.getInteger(R.styleable.RippleAnimationView_strokWidth, 2);
        rippleColor = typedArray.getColor(R.styleable.RippleAnimationView_ripple_anim_color, ContextCompat.getColor(context, R.color.rippleColor));
        paint.setStrokeWidth(UIUtils.getInstance().getWidth(strokeWidth));
        if (rippleType == 0) {
            paint.setStyle(Paint.Style.FILL);
        } else {
            paint.setStyle(Paint.Style.STROKE);
        }
        paint.setColor(rippleColor);
        typedArray.recycle();

        int rippleDuration = 3500;
        int singleDelay = rippleDuration / 4;//间隔时间 （上一个波纹  和下一个波纹的）
        ArrayList<Animator> animatorList = new ArrayList<>();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(UIUtils.getInstance().getWidth(radius + strokeWidth), UIUtils.getInstance().getHeight(radius + strokeWidth));
        layoutParams.addRule(CENTER_IN_PARENT, TRUE);
        float maxScale = UIUtils.displayMetricsWidth / (float) (UIUtils.getInstance().getWidth(radius + strokeWidth));
//        float maxScale = 10;
        for (int i = 0; i < 4; i++) {
            //实例化一个水波纹  view
            RippleCircleView rippleCircleView = new RippleCircleView(this);
            rippleCircleView.setLayoutParams(layoutParams);
            addView(rippleCircleView);
            circleViews.add(rippleCircleView);
            //x 方向属性动画
            final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(rippleCircleView, "ScaleX", 1, maxScale);
            scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);
            scaleXAnimator.setStartDelay(i * singleDelay);
            scaleXAnimator.setDuration(rippleDuration);
            animatorList.add(scaleXAnimator);

            //y 方向的属性动画
            final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(rippleCircleView, "ScaleY", 1.0f, maxScale);
            scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);//无限重复
            scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);
            scaleYAnimator.setStartDelay(i * singleDelay);
            scaleYAnimator.setDuration(rippleDuration);
            animatorList.add(scaleYAnimator);

            //Alpha渐变
            final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(rippleCircleView, "Alpha", 1.0f, 0f);
            alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);//无限重复
            alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
            alphaAnimator.setStartDelay(i * singleDelay);
            alphaAnimator.setDuration(rippleDuration);
            animatorList.add(alphaAnimator);
        }
        animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(animatorList);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i("tuch", "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void startRippleAnimationPlay() {
        if (playingAnimation) {
            return;
        }
        playingAnimation = true;
        for (RippleCircleView circleView : circleViews) {
            circleView.setVisibility(View.VISIBLE);
        }
        animatorSet.start();
    }

    public void stopRippleAnimation() {
        if (!playingAnimation) {
            return;
        }
        playingAnimation = false;
        for (RippleCircleView circleView : circleViews) {
            circleView.setVisibility(View.INVISIBLE);
        }
        animatorSet.end();
    }

    public boolean isPlayingAnimation() {
        return playingAnimation;
    }

}
