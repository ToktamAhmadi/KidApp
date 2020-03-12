package com.example.kidapp.details;

import com.example.kidapp.model.Comment;
import com.example.kidapp.model.api.ApiService;
import com.example.kidapp.providers.ApiServiceProviders;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.subjects.BehaviorSubject;

public class ProductDetailsViewModel {

    private ApiService apiService= ApiServiceProviders.providerApiService();
    private BehaviorSubject<Boolean> prograssBarVibilitySubject=BehaviorSubject.create();

    public Single<List<Comment>> comment(int productId){
        prograssBarVibilitySubject.onNext(true);
        return apiService.getComment(productId).doOnEvent((comments, throwable) -> prograssBarVibilitySubject.onNext(false));
    }

    public BehaviorSubject<Boolean> PrograssBarVibility() {
        return prograssBarVibilitySubject;
    }
}
