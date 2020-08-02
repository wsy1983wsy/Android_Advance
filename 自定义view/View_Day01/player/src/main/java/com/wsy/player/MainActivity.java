package com.wsy.player;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wsy.player.ui.UIUtils;
import com.wsy.player.view.BackgourndAnimationRelativeLayout;
import com.wsy.player.view.DiscView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity {
    private List<Integer> musicDatas = new ArrayList<>();
    private BackgourndAnimationRelativeLayout backgourndAnimationRelativeLayout;
    private int index;
    DiscView discView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.getInstance(this);
        setContentView(R.layout.activity_main);
        musicDatas.add(R.raw.ic_music1);
        musicDatas.add(R.raw.ic_music2);
        musicDatas.add(R.raw.ic_music3);
        backgourndAnimationRelativeLayout = findViewById(R.id.rootLayout);
        discView = findViewById(R.id.discview);
        index = 0;
        findViewById(R.id.ivPlayOrPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Glide.with(MainActivity.this)
                        .load(musicDatas.get(index))
                        .transition(DrawableTransitionOptions.withCrossFade(500))
                        .apply(RequestOptions.bitmapTransform(new BlurTransformation(200, 3)))
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                backgourndAnimationRelativeLayout.setForeground(resource);
                            }
                        });
                index++;
                if (index >= musicDatas.size()) {
                    index = 0;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        discView.setMusicDatas(musicDatas);
    }
}
