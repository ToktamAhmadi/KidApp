package com.example.kidapp.exception;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.HttpException;

public class ExceptionMessageFactory {
    public static String getMessage(Throwable throwable) {
        if (throwable instanceof HttpException) {
            switch (((HttpException) throwable).code()) {
                case 400:
                case 401:
                case 422:
                    Gson gson = new Gson();
                    try {
                        MyHttpException exception = gson.fromJson(((HttpException) throwable).response().errorBody().string(), MyHttpException.class);
                        return exception.getMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                    default:
                        return "اختلال ذز ذزیافت اطلاعات" ;
            }
        }
        return "خطای نامشخص";

    }
    //constructor
    private ExceptionMessageFactory(){

    }
}
