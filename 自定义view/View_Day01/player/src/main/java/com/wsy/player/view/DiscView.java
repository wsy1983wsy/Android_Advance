package com.wsy.player.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.viewpager.widget.ViewPager;

import com.wsy.player.MusicListener;
import com.wsy.player.R;
import com.wsy.player.ui.UIUtils;
import com.wsy.player.ui.ViewCalculateUtil;
import com.wsy.player.util.BitmapUtil;

import java.util.ArrayList;
import java.util.List;

public class DiscView extends RelativeLayout {
    List<View> discLayouts = new ArrayList<>();
    List<Integer> musicDatas = new ArrayList<>();
    List<ObjectAnimator> miscAnimationrs = new ArrayList<>();
    ImageView musicNeedle;
    ImageView musicCircle;
    ViewPagerAdapter viewPagerAdapter;
    ObjectAnimator needleObjectAnimator;
    ViewPager viewPager;
    private MusicListener musicListener;

    public void setMusicListener(MusicListener musicListener) {
        this.musicListener = musicListener;
    }

    public DiscView(Context context) {
        this(context, null);
    }

    public DiscView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiscView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    //设置歌曲列表
    public void setMusicDatas(List<Integer> musicDatas) {
        if (musicDatas == null || musicDatas.size() == 0) {
            return;
        }
        discLayouts.clear();
        this.musicDatas.clear();
        miscAnimationrs.clear();
        this.musicDatas.addAll(musicDatas);
        for (Integer music : this.musicDatas) {
            View centerContainer = LayoutInflater.from(getContext()).inflate(R.layout.layout_disc, viewPager, false);
            ImageView centerImageView = (ImageView) centerContainer.findViewById(R.id.ivDisc);
            Drawable drawable = BitmapUtil.getMusicItemDrawable(getContext(), music);
            centerImageView.setImageDrawable(drawable);
            ViewCalculateUtil.setViewLinearLayoutParam(centerImageView, 800, 800, (850 - 800) / 2 + 190, 0, 0, 0);
            discLayouts.add(centerContainer);
            //准备，指针动画执行之后，才执行旋转动画
            ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(centerImageView, View.ROTATION, 0, 360);
            rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
            rotateAnimator.setDuration(20 * 1000);
            rotateAnimator.setInterpolator(new LinearInterpolator());
            miscAnimationrs.add(rotateAnimator);
        }
        viewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
        viewPagerAdapter = new ViewPagerAdapter(discLayouts);
        viewPager.setAdapter(viewPagerAdapter);
    }

    private int currentItem;

    private void init() {
        viewPager = findViewById(R.id.vpDiscContain);
        // 底盘
        musicCircle = findViewById(R.id.ivDiscBlackgound);
        // 唱针
        musicNeedle = findViewById(R.id.ivNeedle);
        int musicCircleWidth = UIUtils.getInstance().getWidth(813);
        Bitmap bitmapDisc = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_disc_blackground), musicCircleWidth, musicCircleWidth, false);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmapDisc);
        musicCircle.setImageDrawable(roundedBitmapDrawable);
        ViewCalculateUtil.setViewLayoutParam(musicCircle, 850, 850, 190, 0, 0, 0);

        // 设置指针
        Bitmap originBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_needle);
        Bitmap bitmap = Bitmap.createScaledBitmap(originBitmap, UIUtils.getInstance().getWidth(276), UIUtils.getInstance().getWidth(276), false);
        ViewCalculateUtil.setViewLayoutParam(musicNeedle, 276, 413, 43, 0, 500, 0);
        musicNeedle.setPivotX(UIUtils.getInstance().getWidth(43));
        musicNeedle.setPivotY(UIUtils.getInstance().getHeight(43));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             *
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                currentItem = position;
            }

            /**
             * viewpager 状态发生变化
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        //什么都没做
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        //滑动结束
                        playNeedleAnimator();
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        //开始滑动
                        pauseNeedleAnimator();
                        break;
                }
            }
        });

        needleObjectAnimator = ObjectAnimator.ofFloat(musicNeedle, View.ROTATION, -30, 0);
        needleObjectAnimator.setDuration(500);
        needleObjectAnimator.setInterpolator(new AccelerateInterpolator());
        needleObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                int index = viewPager.getCurrentItem();
                ObjectAnimator objectAnimator = miscAnimationrs.get(index);
                if (objectAnimator.isPaused()) {
                    objectAnimator.resume();
                } else {
                    objectAnimator.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    private void playNeedleAnimator() {
        needleObjectAnimator.start();
        if (musicListener != null) {
            musicListener.onMusicChanged(currentItem);
        }
    }

    //唱盘动画停止
    private void pauseNeedleAnimator() {
        miscAnimationrs.get(viewPager.getCurrentItem()).pause();
        needleObjectAnimator.reverse();
    }
}
