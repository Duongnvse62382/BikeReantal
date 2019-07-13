package com.duongnv.bikerental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duongnv.bikerental.adapter.BikeAdapter;
import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.presenter.PrintBikePresenter;
import com.duongnv.bikerental.views.GetBikeByStoreView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity  implements GetBikeByStoreView {


    private Account mAccount;
    private ImageView iconback;
    private BikeAdapter bikeAdapter;
    private RecyclerView mrecyclerView;
    private List<Bike> listBike;
    private TextView textStore;

    PrintBikePresenter mprintBikePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        initView();
        initData();

        Bundle bundle = getIntent().getExtras();
        mAccount = (Account) bundle.getSerializable("account");
//        textStore.setText(mAccount.getUsername()+"");

        iconback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });








    }

    public void initView(){
        //ánh xạ
        mrecyclerView = findViewById(R.id.listView);
        iconback = findViewById(R.id.iconback);
        textStore = findViewById(R.id.txtStore);

        //set layout cho recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StoreActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(linearLayoutManager);

    }

    public void initData(){
        listBike = new ArrayList<>();
        mprintBikePresenter = new PrintBikePresenter(this);
        mprintBikePresenter.getBikeByStore();


    }


    public void updateViewBike(){
        if(bikeAdapter == null){
            bikeAdapter = new BikeAdapter(StoreActivity.this, listBike);
            mrecyclerView.setAdapter(bikeAdapter);
            bikeAdapter.OnItemClick(new BikeAdapter.OnClickIteam() {
                @Override
                public void getPosition(int position) {
                    Intent intent = new Intent(StoreActivity.this, DetailBikeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bikes", listBike.get(position));
                    bundle.putSerializable("account", mAccount);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        else{
            bikeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getBikeSS(List<Bike> list) {
        listBike = list;
        updateViewBike();

    }

    @Override
    public void getBikeFail(String message) {

    }
}
