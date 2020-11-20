package com.example.gson_retrofit2_test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService networkService;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private Retrofit retrofit;
    private NetworkService(){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static NetworkService getInstance() {
        if (networkService == null) {
            networkService = new NetworkService();
        }
        return networkService;
    }

    public JSONPlaceHolderApi getJSONApi() {
        return retrofit.create(JSONPlaceHolderApi.class);
    }
}
