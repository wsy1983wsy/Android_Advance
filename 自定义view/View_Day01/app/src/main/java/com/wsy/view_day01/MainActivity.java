package com.wsy.view_day01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wsy.view_day01.activity.ColorTrackActivity;
import com.wsy.view_day01.activity.CustomTextViewActivity;
import com.wsy.view_day01.activity.HeightActivity;
import com.wsy.view_day01.activity.LetterSideBarActivity;
import com.wsy.view_day01.activity.QQStepViewActivity;
import com.wsy.view_day01.activity.RatingBarActivity;
import com.wsy.view_day01.activity.TagLayoutActivity;
import com.wsy.view_day01.activity.TouchViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showCustomTextView(View view) {
        Intent intent = new Intent(this, CustomTextViewActivity.class);
        startActivity(intent);
    }

    public void showQQStepView(View view) {
        Intent intent = new Intent(this, QQStepViewActivity.class);
        startActivity(intent);
    }

    public void showColorTrackTextView(View view) {
        Intent intent = new Intent(this, ColorTrackActivity.class);
        startActivity(intent);
    }

    public void showRatingBar(View view) {
        Intent intent = new Intent(this, RatingBarActivity.class);
        startActivity(intent);
    }

    public void showLetterSideBar(View view) {
        Intent intent = new Intent(this, LetterSideBarActivity.class);
        startActivity(intent);
    }

    public void showHeightActivity(View view) {
        Intent intent = new Intent(this, HeightActivity.class);
        startActivity(intent);
    }

    public void showTagLayout(View view) {
        Intent intent = new Intent(this, TagLayoutActivity.class);
        startActivity(intent);
    }

    public void shwTouchView(View view) {
        Intent intent = new Intent(this, TouchViewActivity.class);
        startActivity(intent);
    }

}
