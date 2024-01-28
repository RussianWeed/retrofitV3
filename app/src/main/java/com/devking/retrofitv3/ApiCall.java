package com.devking.retrofitv3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCall {

    @GET("photos")
    Call<List<Model>> getmodel();
}
