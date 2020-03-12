package com.example.kidapp.main;

import com.example.kidapp.model.Banner;
import com.example.kidapp.model.Product;
import com.example.kidapp.model.api.ApiService;
import com.example.kidapp.providers.ApiServiceProviders;

import java.util.List;

import io.reactivex.Single;

public class MainViewModel {
    private ApiService apiService= ApiServiceProviders.providerApiService();

    public Single<List<Product>> latestProducts(){
        return apiService.getProducts(Product.SORT_LATEST);
    }

    public Single<List<Product>> popularProducts(){
        return apiService.getProducts(Product.SORT_POPULAR);
    }
    public Single<List<Banner>> banners(){
        return apiService.getBanners();
    }
}
