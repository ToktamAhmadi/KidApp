
package com.example.kidapp.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Token {

    @SerializedName("access_token")
    private String mAccessToken;
    @SerializedName("expire_in")
    private Long mExpireIn;
    @SerializedName("refresh_token")
    private String mRefreshToken;
    @SerializedName("token_type")
    private String mTokenType;

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public Long getExpireIn() {
        return mExpireIn;
    }

    public void setExpireIn(Long expireIn) {
        mExpireIn = expireIn;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }

    public String getTokenType() {
        return mTokenType;
    }

    public void setTokenType(String tokenType) {
        mTokenType = tokenType;
    }

}
