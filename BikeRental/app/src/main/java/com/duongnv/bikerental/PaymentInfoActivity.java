package com.duongnv.bikerental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.model.Bike;
import com.squareup.picasso.Picasso;

public class PaymentInfoActivity extends AppCompatActivity {


    private Account mAccount;
    private Bike bike;
    private int total;
    private String rental, returnbike, address;
    TextView textInfo, textrental, textreturn, textAddress, textTotal;
    private ImageView imagebike, imagecancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);
        textInfo = findViewById(R.id.txtinfoPayment);
        imagebike = findViewById(R.id.imageBikepm);
        textrental = findViewById(R.id.dayrental);
        textreturn = findViewById(R.id.dayreturn);
        imagecancel = findViewById(R.id.cancel);
        textTotal = findViewById(R.id.totalprice);

        textAddress = findViewById(R.id.txtAddressss);
        showInfo();

        imagecancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Intent intent = new Intent(PaymentInfoActivity.this, StoreActivity.class);
//                Bundle bundle  = new Bundle();
//                bundle.putSerializable("account", mAccount);
//                startActivity(intent);
                finish();
            }
        });

    }


    public void showInfo(){
        Bundle bundle = getIntent().getExtras();
        mAccount = (Account) bundle.getSerializable("account") ;
        bike = (Bike) bundle.getSerializable("bikepm");
        total = (int) bundle.getInt("total");
        rental = (String) bundle.getString("rental");
        returnbike = (String) bundle.getString("return");
        address = (String) bundle.getString("address");
        Picasso.get().load(bike.getImage()).into(imagebike);
        textInfo.setText( bike.getBikeName());
        textrental.setText(rental + "");
        textreturn.setText(returnbike + "");
        textTotal.setText(total+ "$");
        textAddress.setText(address + "");
    }
}
