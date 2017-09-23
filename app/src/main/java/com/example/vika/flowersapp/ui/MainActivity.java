package com.example.vika.flowersapp.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.vika.flowersapp.R;
import com.example.vika.flowersapp.model.adapter.FlowerAdapter;
import com.example.vika.flowersapp.controller.RestClient;
import com.example.vika.flowersapp.model.pojo.Flower;
import com.example.vika.flowersapp.model.utils.Constants;
import com.example.vika.flowersapp.model.utils.InternetConnection;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FlowerAdapter.FlowerClickListener{
    private static final String LOG = "MainActivity";

    private RecyclerView recyclerView;
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

        flowerAdapter = new FlowerAdapter(this);
        recyclerView.setAdapter(flowerAdapter);

        if(InternetConnection.isInternetConnection(this)) {
            Toast.makeText(this, R.string.internet_text, Toast.LENGTH_LONG).show();
            getApiData();
        } else {
            Toast.makeText(this, R.string.error_internet_text, Toast.LENGTH_LONG).show();
        }
    }


    public void onClick(int position) {
        Log.i(LOG, "onClick position " + position);
        Flower selectedFlower = flowerAdapter.getSelectedFlower(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(Constants.REFERENCE.FLOWER, (Serializable) selectedFlower);
        startActivity(intent);
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
                        Log.i(LOG, "flowers name " + flower.getName());
                      //  Log.i(LOG, "flowers photo " + flower.getPhoto());
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
