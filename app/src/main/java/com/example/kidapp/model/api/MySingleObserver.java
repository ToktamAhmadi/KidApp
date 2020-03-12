package com.example.kidapp.model.api;

import com.example.kidapp.BaseActivity;
import com.example.kidapp.exception.ExceptionMessageFactory;

import retrofit2.HttpException;

public abstract class MySingleObserver<T> implements io.reactivex.SingleObserver<T> {

    private BaseActivity activity;
    public MySingleObserver(BaseActivity activity){
        this.activity=activity;
    }

    @Override
    public void onError(Throwable e) {
        activity.showSnackBarMessage(ExceptionMessageFactory.getMessage(e));

    }
}
