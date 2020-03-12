
package com.example.kidapp.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Author {

    @SerializedName("username")
    private String mUsername;

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
