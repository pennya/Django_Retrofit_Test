package com.example.kjh.retrofit_test;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by KJH on 2017-06-06.
 */

public interface NetworkService {
    @POST("/api/versions/")
    Call<Version> post_version(@Body Version version);

    @PATCH("/api/versions/{pk}/")
    Call<Version> patch_version(@Path("pk") int pk, @Body Version version);

    @DELETE("/api/versions/{pk}/")
    Call<Version> delete_version(@Path("pk") int pk);

    @GET("/api/versions/")
    Call<List<Version>> get_version();

    @GET("/api/versions/{pk}/")
    Call<Version> get_pk_version(@Path("pk") int pk);





    @POST("/api/restaurants/")
    Call<Version> post_restaruant(@Body Restaurant restaruant);

    @PATCH("/api/restaurants/{pk}/")
    Call<Version> patch_restaruant(@Path("pk") int pk, @Body Restaurant restaruant);

    @DELETE("/api/restaurants/{pk}/")
    Call<Version> delete_restaruant(@Path("pk") int pk);

    @GET("/api/restaurants/")
    Call<List<Version>> get_restaruant();

    @GET("/api/restaurants/{pk}/")
    Call<Version> get_pk_restaruant(@Path("pk") int pk);
}
