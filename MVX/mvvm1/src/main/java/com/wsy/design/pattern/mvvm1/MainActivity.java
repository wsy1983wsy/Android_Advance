package com.wsy.design.pattern.mvvm1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.wsy.design.pattern.mvvm1.databinding.ActivityMainBinding;
import com.wsy.design.pattern.mvvm1.vm.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        new LoginViewModel(binding);
    }
}
