package com.example.kidapp.model.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static Retrofit retrofit;
    public static Retrofit getinstance(){
        if (retrofit== null){
           retrofit=new Retrofit.Builder()
                    .baseUrl(" https://api.npoint.io/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
   private RetrofitSingleton(){}

}