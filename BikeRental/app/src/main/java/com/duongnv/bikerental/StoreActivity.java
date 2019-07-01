package com.duongnv.bikerental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.duongnv.bikerental.model.Bike;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    private TextView txtBike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        txtBike = findViewById(R.id.txtbike);
        Bundle bundle = getIntent().getExtras();

        List<Bike>  mbikes = (List<Bike>) bundle.getSerializable("bikes");
        for (Bike bike: mbikes
             ) {
            txtBike.setText(bike.getBikeName() + "-" + bike.getStoreName() + "" );

        }







    }
}
