package com.duongnv.bikerental;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duongnv.bikerental.databases.CartDatabase;
import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.model.Cart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailBikeActivity extends AppCompatActivity {
    private ImageView imageBike, imageheart;
    private TextView textName, textType, textDecription, textPrice;
    private Button btnRental;
    private Bike bikes;
    private ImageView iconback;
    private Account mAccount;
    private CartDatabase cartDatabase;

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


    private void initView() {
        imageBike = findViewById(R.id.imageBikedt);
        textName = findViewById(R.id.txtNameBike);
        textType = findViewById(R.id.txtNameType);
        textDecription = findViewById(R.id.txtDecription);
        textPrice = findViewById(R.id.txtPrice);
        btnRental = findViewById(R.id.btnRental);
        iconback = findViewById(R.id.iconback);
        imageheart = findViewById(R.id.imageHeart);

    }

    public void getBikePostion() {
        Bundle bundle = getIntent().getExtras();
        bikes = (Bike) bundle.getSerializable("bikes");
        mAccount = (Account) bundle.getSerializable("account");
        Picasso.get().load(bikes.getImage()).into(imageBike);
        textName.setText(bikes.getBikeName() + "");
//        textType.setText(mAccount.getUsername()+ "");
        textType.setText(bikes.getTypeName() + "");
        textDecription.setText(bikes.getDescription() + "");
        textPrice.setText(bikes.getPrice() + "$/day");
    }

    public void clickToAddHeart(View view) {
        final int bikeid = bikes.getBikeID();

        cartDatabase = CartDatabase.getInstance(this);

        AsyncTask<Void, Void, Boolean> asyncTask = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                Cart cart = new Cart();
                try {

                    cart.setBikeid(bikes.getBikeID());
                    cart.setBikename(bikes.getBikeName());
                    cart.setPrice(bikes.getPrice());
                    cart.setImage(bikes.getImage());
                    cart.setDecription(bikes.getDescription());


                    List<Cart> listCart = cartDatabase.cartDAO().getAll();
                    if (listCart != null) {
                        for (Cart cartEach : listCart) {

                            int bId = cartEach.bikeid;

                            if (bikeid == bId) {
                                cartDatabase.cartDAO().delete(cartEach);
                                return true;

                            }
                        }
                    }
                    cartDatabase.cartDAO().insertCart(cart);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                return false;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    imageheart.setImageResource(R.drawable.heart);
                    Toast.makeText(DetailBikeActivity.this, "UnLike", Toast.LENGTH_SHORT).show();
                } else {
                    imageheart.setImageResource(R.drawable.hearts);
                    Toast.makeText(DetailBikeActivity.this, "Like", Toast.LENGTH_SHORT).show();
                }

            }
        };
        asyncTask.execute();


    }

}
