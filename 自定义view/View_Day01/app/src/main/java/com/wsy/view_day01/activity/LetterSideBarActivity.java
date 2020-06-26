package com.wsy.view_day01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.wsy.view_day01.R;
import com.wsy.view_day01.view.LetterSideBar;

public class LetterSideBarActivity extends Activity implements LetterSideBar.LetterTouchListener {
    private TextView selectedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_side_bar);
        LetterSideBar letterSideBar = findViewById(R.id.letterSideBar);
        letterSideBar.setOnLetterTouchListener(this);
        selectedTextView = findViewById(R.id.selectedTextView);

    }

    @Override
    public void touch(CharSequence letter, boolean isTouch) {
        if (!isTouch) {
            return;
        }
        selectedTextView.setText(letter);
    }
}
