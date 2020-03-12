package com.example.kidapp.auth;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

public class UserInfoManager {
    private SharedPreferences sharedPreferences;
    public UserInfoManager(Context context){
        sharedPreferences=context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
    }
    public void saveToken(String token,String refreshToken){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",token);
        editor.putString("refresh_token",refreshToken);
        editor.apply();

    }

    @Nullable
    public String token(){
        return sharedPreferences.getString("token",null);
    }

    public void saveUsername(String  username){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.apply();
    }

    @Nullable
    public String refreshToken(){
        return sharedPreferences.getString("refresh_token",null);
    }
}
