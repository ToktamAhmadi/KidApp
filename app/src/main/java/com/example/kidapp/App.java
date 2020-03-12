package com.example.kidapp;

import android.app.Application;

import com.example.kidapp.auth.TokenContainer;
import com.example.kidapp.auth.UserInfoManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TokenContainer.updateToken(new UserInfoManager(this).token());
    }
}
