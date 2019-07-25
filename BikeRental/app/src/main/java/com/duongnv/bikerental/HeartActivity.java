package com.duongnv.bikerental;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.duongnv.bikerental.adapter.HeartAdapter;
import com.duongnv.bikerental.databases.CartDatabase;
import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.model.Cart;


import java.util.ArrayList;
import java.util.List;

public class HeartActivity extends AppCompatActivity {


    private CartDatabase cartDatabase;
    private HeartAdapter heartAdapter;
    private RecyclerView recyclerView;
    private ImageView imgBack;
    private Account mAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart);
        recyclerView = findViewById(R.id.listheart);
        cartDatabase = CartDatabase.getInstance(this);
        imgBack = findViewById(R.id.iconback);
        Bundleaccount();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        GetListItem();
    }

    public void Bundleaccount() {
        Bundle bundle = getIntent().getExtras();
        mAccount = (Account) bundle.getSerializable("account");
    }


    public void GetListItem() {

        class GetListItem extends AsyncTask<Void, Void, List<Cart>> {

            @Override
            protected List<Cart> doInBackground(Void... voids) {
                List<Cart> cartList = cartDatabase.cartDAO().getAll();
                return cartList;
            }

            @Override
            protected void onPostExecute(final List<Cart> carts) {
                super.onPostExecute(carts);
                if (carts != null) {
                    LinearLayoutManager linearLayoutManager
                            = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    heartAdapter = new HeartAdapter(HeartActivity.this, carts);
                    recyclerView.setAdapter(heartAdapter);
                    heartAdapter.OnclickItem(new HeartAdapter.OnClickIteam() {
                        @Override
                        public void getPosition(int position) {

                            Intent intent = new Intent(HeartActivity.this, BookingCartActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("cart", carts.get(position));
                            bundle.putSerializable("account", mAccount);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                    });
                }

            }
        }
        GetListItem gl = new GetListItem();
        gl.execute();
    }
}
