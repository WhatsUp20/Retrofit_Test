package com.example.gson_retrofit2_test;

import androidx.cardview.widget.CardView;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderApi {
    @GET ("posts")
    Call<List<POJO>> getPosts();
}
