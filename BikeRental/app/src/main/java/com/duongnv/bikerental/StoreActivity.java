package com.duongnv.bikerental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.duongnv.bikerental.adapter.BikeAdapter;
import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.presenter.PrintBikePresenter;
import com.duongnv.bikerental.views.GetBikeByStoreView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity  implements GetBikeByStoreView {


    private BikeAdapter bikeAdapter;
    private RecyclerView mrecyclerView;
    private List<Bike> listBike;

    PrintBikePresenter mprintBikePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        initView();
        initData();




    }

    public void initView(){
        //ánh xạ
        mrecyclerView = findViewById(R.id.listView);

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
