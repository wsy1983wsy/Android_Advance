package com.wsy.wangyimusic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.wsy.wangyimusic.ui.UIUtils;
import com.wsy.wangyimusic.ui.ViewCalculateUtil;
import com.wsy.wangyimusic.util.StatusBarUtil;
import com.wsy.wangyimusic.view.MyNestedScrollView;

import java.lang.reflect.Field;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity {
    public final static String IMAGE_URL_MEDIUM = "http://p3.music.126.net/iRbTMHGfy-grDtx7o2T_dA==/109951164009413034.jpg?param=400y400";
    Toolbar toolbar;
    ImageView toolbarBgImageView;
    ImageView header_bg;
    RecyclerView music_recycler;
    LinearLayout lv_header_contail;
    ImageView header_music_log;
    ImageView header_image_item;
    MyNestedScrollView myNestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.getInstance(getApplication());
        setContentView(R.layout.activity_main);
        init();
        postImage();
        notifyData();
        setImageHeaderBg();
    }

    private void init() {
        toolbar = findViewById(R.id.toolBar);
        toolbarBgImageView = findViewById(R.id.toolbar_bg);
        header_bg = findViewById(R.id.header_bg);
        music_recycler = findViewById(R.id.music_recycler);
        myNestedScrollView = findViewById(R.id.nsv_scrollview);
        header_music_log = findViewById(R.id.header_music_log);
        LinearLayout lv_header_detail = findViewById(R.id.lv_header_detail);
        RelativeLayout rv_header_container = findViewById(R.id.rv_header_container);
        lv_header_contail = findViewById(R.id.lv_header_contail);
        header_image_item = findViewById(R.id.header_image_item);
        setSupportActionBar(toolbar);
        toolbar.setTitle("歌单");
        toolbar.setSubtitle("歌单歌单歌单");
        modifyToolbar();
        ViewCalculateUtil.setViewLayoutParam(toolbar, 1080, 164, 0, 0, 0, 0);
        ViewCalculateUtil.setViewLinearLayoutParam(rv_header_container, 1080, 770, 164, 0, 0, 0);
        ViewCalculateUtil.setViewLayoutParam(toolbarBgImageView, 1080, 164 + UIUtils.getInstance().getSystemBarHeight(this), 0, 0, 0, 0);
        ViewCalculateUtil.setViewLayoutParam(header_bg, 1080, 850, 0, 0, 0, 0);
        ViewCalculateUtil.setViewLayoutParam(lv_header_detail, 1080, 380, 72, 0, 52, 0);
        ViewCalculateUtil.setViewLinearLayoutParam(header_image_item, 380, 380);
        ViewCalculateUtil.setViewLayoutParam(header_music_log, 60, 60, 59, 0, 52, 0);
        StatusBarUtil.setStateBar(this, toolbar);
    }

    private void modifyToolbar() {
        try {
            Field imageButtonFieldId = toolbar.getClass().getDeclaredField("mNavButtonView");
            imageButtonFieldId.setAccessible(true);
            ImageButton imageButton = (ImageButton) imageButtonFieldId.get(toolbar);
            Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) imageButton.getLayoutParams();
            layoutParams.topMargin = UIUtils.getInstance().getHeight(20);
            layoutParams.leftMargin = UIUtils.getInstance().getWidth(10);
        } catch (Throwable e) {
        }
    }

    private void postImage() {
        Glide.with(this)
                .load(IMAGE_URL_MEDIUM)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .override(400, 400)
                .into(header_image_item);

        // "14":模糊度；"3":图片缩放3倍后再进行模糊
        Glide.with(this)
                .load(IMAGE_URL_MEDIUM)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(200, 3)))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        lv_header_contail.setBackground(resource);
                    }
                });

        Glide.with(this)
                .load(IMAGE_URL_MEDIUM)
                .error(R.drawable.stackblur_default)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(250, 6)))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        toolbar.setBackgroundColor(Color.TRANSPARENT);
                        toolbarBgImageView.setImageAlpha(0);
                        toolbarBgImageView.setVisibility(View.VISIBLE);
                        toolbarBgImageView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
//                                scrollChangeHeader(0);
                                myNestedScrollView.scrollBy(0, 1);
                            }
                        }, 100);
                        return false;
                    }
                })
                .into(toolbarBgImageView);
    }


    private void notifyData() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        music_recycler.setLayoutManager(mLayoutManager);
        // 需加，不然滑动不流畅
        music_recycler.setNestedScrollingEnabled(false);
        music_recycler.setHasFixedSize(false);
        final MusicAdapter adapter = new MusicAdapter(this);
        adapter.notifyDataSetChanged();
        music_recycler.setAdapter(adapter);
    }

    private void setImageHeaderBg() {
        slidingDistance = UIUtils.getInstance().getHeight(490);
        myNestedScrollView.setOnMyScrollChangeListener(new MyNestedScrollView.ScrollInterface() {
            @Override
            public void onScrollChange(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollChangeHeader(scrollY);
            }
        });
    }

    int slidingDistance;

    private void scrollChangeHeader(int scrollY) {
        Log.d("MainActivity", "scrollY: " + scrollY);
        float alpha = Math.abs(scrollY) * 1.0f / (slidingDistance);
        Drawable drawable = toolbarBgImageView.getDrawable();
        if (drawable != null) {
            //490
            if (scrollY <= slidingDistance) {
                alpha = (int) (alpha * 255);
                Log.d("MainActivity", "alpha: " + alpha);
                drawable.mutate().setAlpha((int) alpha);
                toolbarBgImageView.setImageDrawable(drawable);
            } else {
                //490
                drawable.mutate().setAlpha(255);
                toolbarBgImageView.setImageDrawable(drawable);
            }
        }
    }

}
