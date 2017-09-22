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
    private FlowerClickListener flowerClickListener;

    public FlowerAdapter(FlowerClickListener listener) {
        flowerList = new ArrayList<>();
        flowerClickListener = listener;

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

        Picasso.with(holder.itemView.getContext())
                .load(flower.getPhoto())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.image);

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

    public Flower getSelectedFlower(int position) {
        return flowerList.get(position);
    }

    public class FlowerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView category, instructions, name, price, productId;
        ImageView image;

       public FlowerViewHolder(View itemView) {
           super(itemView);

           image = (ImageView)itemView.findViewById(R.id.image);
           name = (TextView)itemView.findViewById(R.id.name);
           price = (TextView)itemView.findViewById(R.id.price);

           itemView.setOnClickListener(this);
       }

        @Override
        public void onClick(View v) {
            flowerClickListener.onClick(getLayoutPosition());
            Log.i(LOG, "onClick " + v);
        }
    }
    public interface FlowerClickListener {

        void onClick(int position);
    }
}
