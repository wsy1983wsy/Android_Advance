package com.wsy.design.pattern.databinding.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.wsy.design.pattern.databinding.BR;

/*
public class UserInfo extends BaseObservable {
    private String username;

    private String password;

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}*/
public class UserInfo {
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
}