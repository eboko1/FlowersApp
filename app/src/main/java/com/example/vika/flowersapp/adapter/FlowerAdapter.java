package com.example.vika.flowersapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vika.flowersapp.R;
import com.example.vika.flowersapp.model.Flower;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vika on 21.09.2017.
 */

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder> {
    private static final String LOG = "FlowerAdapter";
    private List<Flower> flowerList;

    public FlowerAdapter() {
        flowerList = new ArrayList<>();
    }




    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(LOG, " onCreateViewHolder ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flower, parent, false);
        return new FlowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlowerViewHolder holder, int position) {
        Log.i(LOG, " onBindViewHolder ");
        Flower flower = flowerList.get(position);

        holder.name.setText(flower.getName());
        holder.price.setText(flower.getPrice().toString());

      //  Picasso.with(holder.itemView.getContext())
         //       .load(flower.getPhoto())
         //       .into(holder.image);

    }

    @Override
    public int getItemCount() {
        Log.i(LOG, " getItemCount =  " + flowerList.size());
        return flowerList.size();
    }

    public void addFlower(Flower flower) {
        Log.i(LOG, "addFlower ");
      flowerList.add(flower);
    }

    public class FlowerViewHolder extends RecyclerView.ViewHolder{
        TextView category, instructions, name, price, productId;
        ImageView image;

       public FlowerViewHolder(View itemView) {
           super(itemView);

           ImageView image = (ImageView)itemView.findViewById(R.id.image);
           TextView name = (TextView)itemView.findViewById(R.id.name);
           TextView price = (TextView)itemView.findViewById(R.id.price);
       }
   }
}
