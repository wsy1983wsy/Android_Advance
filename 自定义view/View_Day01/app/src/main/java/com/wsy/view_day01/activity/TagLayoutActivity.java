package com.wsy.view_day01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wsy.view_day01.R;
import com.wsy.view_day01.view.taglayout.BaseAdapter;
import com.wsy.view_day01.view.taglayout.TagLayout;

import java.util.ArrayList;

public class TagLayoutActivity extends Activity {
    private ArrayList<String> items;
    private TagLayout tagLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_layout);
        tagLayout = findViewById(R.id.tagLayout);
        items = new ArrayList<>();
        items.add("1");
        items.add("1234");
        items.add("12342");
        items.add("111234");
        items.add("13412341");
        items.add("1e134134");
        items.add("1qw");
        items.add("1134");
        items.add("asdfaf1");
        items.add("12431234");
        items.add("1134");
        items.add("qwer1");
        items.add("wqeqwer1");

        tagLayout.setAdapter(tagAdapter);
    }

    private BaseAdapter tagAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public View getView(int position, ViewGroup parent) {
            TextView view = (TextView) LayoutInflater.from(TagLayoutActivity.this).inflate(R.layout.tag_layout_item, parent, false);
            view.setText(items.get(position));
            return view;
        }
    };
}
