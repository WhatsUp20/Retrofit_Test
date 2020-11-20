package com.example.gson_retrofit2_test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @GET ("posts")
    Call<List<POJO>> getPosts();
}
