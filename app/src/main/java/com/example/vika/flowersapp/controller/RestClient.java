package com.example.vika.flowersapp.controller;

import com.example.vika.flowersapp.api.FlowerService;
import com.example.vika.flowersapp.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vika on 21.09.2017.
 */

public class RestClient {

    private static Retrofit getRetrofitInstance(){
        Retrofit retrofit = null;
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.Http.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static FlowerService getFlowerApiClient(){
        FlowerService flowerService = getRetrofitInstance().create(FlowerService.class);
        return flowerService;
    }

}
