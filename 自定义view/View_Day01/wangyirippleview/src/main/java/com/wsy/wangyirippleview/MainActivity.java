package com.wsy.wangyirippleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wsy.wangyirippleview.ui.UIUtils;
import com.wsy.wangyirippleview.ui.ViewCalculateUtil;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    RippleAnimationView rippleAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.getInstance(this);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.ImageView);
        ViewCalculateUtil.setViewLayoutParam(imageView, 168, 168, 0, 0, 0, 0);
        rippleAnimationView = (RippleAnimationView) findViewById(R.id.layout_RippleAnimation);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rippleAnimationView.isPlayingAnimation()) {
                    rippleAnimationView.stopRippleAnimation();
                } else {
                    rippleAnimationView.startRippleAnimationPlay();
                }
            }
        });
    }
}
