package com.wsy.wangyimusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageButton;

import com.wsy.wangyimusic.ui.UIUtils;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.getInstance(getApplication());
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("歌单");
        toolbar.setSubtitle("歌单歌单歌单");
        modifyToolbar();
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

}
