package com.example.kidapp.auth;

import android.content.Context;

import com.example.kidapp.model.Token;
import com.example.kidapp.model.api.ApiService;
import com.example.kidapp.providers.ApiServiceProviders;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.subjects.BehaviorSubject;

public class AuthenticationViewModel {

    /**
     * this method for decide which mode login or register
     * why use BehaviorSubject because of observable have last variable and
     * when subscribe receive last variable
     * by BehaviorSubject check what last variable
     * BehaviorSubject is a observable that take last variable of itself in itself
     */
    private BehaviorSubject<Boolean> isLoginMode = BehaviorSubject.create();
    private BehaviorSubject<Boolean> progressBarVisibilitySubject = BehaviorSubject.create();
    private ApiService apiService = ApiServiceProviders.providerApiService();

    public Completable authenticat(Context context, String username,
                                   String password) {
        Single<Token> tokenSingle;
        progressBarVisibilitySubject.onNext(true);//this line like set visibility=true means progressBar visible
        //if else for check last mode
        //and isLoginMode as behaviourSubject is observable type in first time return null because didn't have any variable
        if (isLoginMode.getValue() == null || isLoginMode.getValue()) {//mode is login
            //Todo
            tokenSingle = apiService.getToken("password", 2, "clientSecret", username, password);
        } else {//mode is register
            tokenSingle = apiService.registerUser(username, password)//send username,password
                    //if register is success get token by flatMap
                    //in there flatMap transfer message success to token
                    .flatMap(successResponse -> apiService.getToken("password", 2, "clientServer", username, password));
        }
        //save data in userInfoManager by UserInfoManager class
        UserInfoManager userInfoManager = new UserInfoManager(context);
        return tokenSingle.doOnSuccess(token -> {userInfoManager.saveToken(token.getAccessToken(), token.getRefreshToken());
        TokenContainer.updateToken(token.getAccessToken());//token save in memory because every time don't request to SharedPreferences
        })
                .doOnEvent((token, throwable) -> progressBarVisibilitySubject.onNext(false))//if login success or failed should progressBat don't visible
                /*//Todo */.toCompletable();// result as Single transfer as Completable

    }

    /**
     * update mode sign in or register when user click on authentication button
     */
    public void onChangeAuthenticationModeButtonClick() {
        isLoginMode.onNext(isLoginMode.getValue() != null && !isLoginMode.getValue());
        /** this code means if in first time open "account" page is isLoginMode.getValue() == null so page is default mode
        * else mode will be !mode means if mode was login will be register
         * if mode register change to login
         */
    }

    public BehaviorSubject<Boolean> getIsLoginMode() {
        return isLoginMode;
    }

    public BehaviorSubject<Boolean> getProgressBarVisibilitySubject() {
        return progressBarVisibilitySubject;
    }
}
