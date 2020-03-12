package com.example.kidapp.model.api;

import com.example.kidapp.BaseActivity;
import com.example.kidapp.exception.ExceptionMessageFactory;

import io.reactivex.CompletableObserver;

public abstract class MyCompletableObserver implements CompletableObserver{

    private BaseActivity activity;
    public MyCompletableObserver(BaseActivity activity){
        this.activity=activity;
    }

    @Override
    public void onError(Throwable e) {
        activity.showSnackBarMessage(ExceptionMessageFactory.getMessage(e));

    }
}
