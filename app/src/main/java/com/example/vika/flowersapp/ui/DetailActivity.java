package com.example.vika.flowersapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vika.flowersapp.R;
import com.example.vika.flowersapp.model.pojo.Flower;
import com.example.vika.flowersapp.model.utils.Constants;

public class DetailActivity extends AppCompatActivity {
    TextView category, instructions, name, price, productId;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        Intent intent = getIntent();
        Flower flower = (Flower) intent.getSerializableExtra(Constants.REFERENCE.FLOWER);

        name.setText(flower.getName());
        price.setText(flower.getPrice().toString());
        instructions.setText(flower.getInstructions());
        productId.setText(String.format("%d", flower.getProductId()));

    }

    private void initView() {
        image = (ImageView)findViewById(R.id.detail_image);
        name = (TextView)findViewById(R.id.detail_name);
        instructions = (TextView)findViewById(R.id.detail_instructions);
        productId = (TextView)findViewById(R.id.detail_id);
        price = (TextView)findViewById(R.id.detail_price);
    }

}
