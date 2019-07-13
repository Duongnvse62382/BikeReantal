package com.duongnv.bikerental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.model.Bike;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailBikeActivity extends AppCompatActivity {
    private ImageView imageBike;
    private TextView textName, textType, textDecription, textPrice;
    private Button btnRental;
    private Bike bikes;
    private ImageView iconback;
    private Account mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bike);
        initView();
        getBikePostion();

        iconback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailBikeActivity.this, BookBikeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bikes", bikes);
                bundle.putSerializable("account", mAccount);
                intent.putExtras(bundle);
                finish();
                startActivity(intent);
            }
        });
    }


    private void initView(){
        imageBike = findViewById(R.id.imageBikedt);
        textName = findViewById(R.id.txtNameBike);
        textType = findViewById(R.id.txtNameType);
        textDecription = findViewById(R.id.txtDecription);
        textPrice = findViewById(R.id.txtPrice);
        btnRental = findViewById(R.id.btnRental);
        iconback = findViewById(R.id.iconback);

    }

    public void getBikePostion(){
        Bundle bundle = getIntent().getExtras();
        bikes = (Bike) bundle.getSerializable("bikes");
        mAccount = (Account) bundle.getSerializable("account");
        Picasso.get().load(bikes.getImage()).into(imageBike);
        textName.setText(bikes.getBikeName()+ "");
//        textType.setText(mAccount.getUsername()+ "");
        textType.setText(bikes.getTypeName()+ "");
        textDecription.setText(bikes.getDescription()+ "");
        textPrice.setText(bikes.getPrice()+ "$/day");
    }
}
