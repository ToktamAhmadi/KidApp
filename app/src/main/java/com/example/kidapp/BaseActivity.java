package com.example.kidapp;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    public abstract  int getCordinatorLayoutId();
    public void showSnackBarMessage(String message){
        Snackbar.make(findViewById(getCordinatorLayoutId()),
                message,Snackbar.LENGTH_SHORT).show();
    }
}
