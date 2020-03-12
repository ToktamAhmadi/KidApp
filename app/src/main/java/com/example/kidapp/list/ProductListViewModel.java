package com.example.kidapp.list;

import com.example.kidapp.model.Product;
import com.example.kidapp.model.api.ApiService;
import com.example.kidapp.providers.ApiServiceProviders;

import java.util.List;

import io.reactivex.Single;

public class ProductListViewModel {
    private ApiService apiService=ApiServiceProviders.providerApiService();
    public Single<List<Product>> products(Integer sortType)
    {
        return apiService.getProducts(sortType);
    }
}
