package com.example.vika.flowersapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vika.flowersapp.R;
import com.example.vika.flowersapp.adapter.FlowerAdapter;
import com.example.vika.flowersapp.controller.RestClient;
import com.example.vika.flowersapp.model.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String LOG = "MainActivity";

    private RecyclerView recyclerView;
    private RestClient restClient;
    private FlowerAdapter flowerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG, "onCreate ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        flowerAdapter = new FlowerAdapter();
        recyclerView.setAdapter(flowerAdapter);

        getApiData();
    }

    private void getApiData(){
        Call<List<Flower>> callList = RestClient.getFlowerApiClient().getAllFlowers();
        callList.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                Log.i(LOG, "onResponse "+ response.isSuccessful());
                if(response.isSuccessful()){
                    List<Flower> flowers = response.body();
                    for(int i=0; i<flowers.size(); i++){
                     Flower flower = flowers.get(i);
                     flowerAdapter.addFlower(flower);
                        Log.i(LOG, "flowers " + flower.getName());
                    }
                } else {
                    int codeState = response.code();
                    switch (codeState){

                    }

                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {
                Log.i(LOG, "onFailure " + t.getMessage());
            }
        });

    }
}
