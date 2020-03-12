package com.example.kidapp.providers;

import com.example.kidapp.model.api.ApiService;
import com.example.kidapp.model.api.RetrofitSingleton;

public class ApiServiceProviders {
    private static ApiService apiService;
    public static ApiService providerApiService() {
        if (apiService == null) {
            apiService = RetrofitSingleton.getinstance().create(ApiService.class);
        }
        return apiService;
    }

}
