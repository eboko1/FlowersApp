package com.example.vika.flowersapp.model.api;

import com.example.vika.flowersapp.model.pojo.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Vika on 21.09.2017.
 */

public interface FlowerService {
    @GET("/feeds/flowers.json")
    Call<List<Flower>> getAllFlowers();
}
