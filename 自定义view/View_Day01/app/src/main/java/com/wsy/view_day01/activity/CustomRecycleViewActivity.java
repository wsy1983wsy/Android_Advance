package com.wsy.view_day01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wsy.view_day01.R;
import com.wsy.view_day01.view.recycleview.CustomRecycleView;

public class CustomRecycleViewActivity extends Activity {
    CustomRecycleView recycleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        recycleView = findViewById(R.id.recycleView);
        recycleView.setAdapter(new CustomRecycleView.Adapter() {
            @Override
            public View onCreateViewHolder(int position, View convertView, ViewGroup parent) {
                convertView = CustomRecycleViewActivity.this.getLayoutInflater().inflate(R.layout.item_table, parent, false);
                TextView textView = (TextView) convertView.findViewById(R.id.text1);
                textView.setText("网易课堂 " + position);
                Log.i("CustomRecycleViewActivity", "onCreateViewHodler: " + convertView.hashCode());
                return convertView;
            }

            @Override
            public View onBinderViewHolder(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) convertView.findViewById(R.id.text1);
                textView.setText("网易课堂 " + position);
                Log.i("CustomRecycleViewActivity", "onBinderViewHodler: " + convertView.hashCode());
                return convertView;
            }

            @Override
            public int getItemViewType(int row) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public int getCount() {
                return 30;
            }

            @Override
            public int getHeight(int index) {
                return 100;
            }
        });
    }
}
