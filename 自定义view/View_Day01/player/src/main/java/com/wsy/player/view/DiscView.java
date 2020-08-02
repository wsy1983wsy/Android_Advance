package com.wsy.player.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.viewpager.widget.ViewPager;

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
    }

}
