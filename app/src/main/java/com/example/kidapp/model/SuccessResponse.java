
package com.example.kidapp.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SuccessResponse {

    @SerializedName("message")
    private String mMessage;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
