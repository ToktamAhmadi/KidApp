package com.example.kidapp.model.api;

import com.example.kidapp.model.Banner;
import com.example.kidapp.model.Comment;
import com.example.kidapp.model.Product;
import com.example.kidapp.model.SuccessResponse;
import com.example.kidapp.model.Token;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("93931d1e1aef0703f009")
    Single<List<Product>> getProducts(@Query("sort") Integer sort);

    @GET("4311d83abe01c1ed0c54")
    Single<List<Banner>> getBanners();

    //Todo
    @FormUrlEncoded
    @POST("")
    Single<Token> getToken(@Field("grant_type") String grantType,
                           @Field("client_id")Integer clientId,
                           @Field("client_secret")String clientSecret,
                           @Field("username")String username,
                           @Field("password")String password);
    //Todo
    @FormUrlEncoded
    @POST("")
    Single<SuccessResponse> registerUser(@Field("username")String username,
                                         @Field("password")String password);


    //Todo
    @GET("5e57c6d561ef782ce2c06055")
    Single<List<Comment>> getComment(@Query("product_id") int productId);


}
