package com.duongnv.bikerental.adapter;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duongnv.bikerental.R;
import com.duongnv.bikerental.model.Bike;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BikeAdapter extends RecyclerView.Adapter<BikeAdapter.ViewHolderBike> {
    private Context context;
    //data json
    List<Bike> bikeList;

    OnClickIteam onClickIteam;
    public BikeAdapter(Context context, List<Bike> bikeList) {
        this.context = context;
        this.bikeList = bikeList;
    }

    @NonNull
    @Override
    public ViewHolderBike onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.show_bikerental, viewGroup, false);
        return new BikeAdapter.ViewHolderBike(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBike viewHolderBike, int i) {
        viewHolderBike.txtBikeName.setText(bikeList.get(i).getBikeName());
        viewHolderBike.txtTypeName.setText(bikeList.get(i).getTypeName());
        viewHolderBike.txtprice.setText(bikeList.get(i).getPrice()+ " $/day");
        Picasso.get().load(bikeList.get(i).getImage()).into(viewHolderBike.imageBike);
        viewHolderBike.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickIteam!= null){
                    onClickIteam.getPosition(i);
                }
            }
        });
    }

    public void  OnItemClick(OnClickIteam onClickIteam) {
        this.onClickIteam = onClickIteam;
    }

    @Override
    public int getItemCount() {
        return bikeList.size();
    }


    public class ViewHolderBike extends RecyclerView.ViewHolder {

        private ImageView imageBike;
        private TextView  txtBikeID, txtBikeName, txtTypeName, txtprice;
        private LinearLayout container;

        public ViewHolderBike(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            txtBikeName = itemView.findViewById(R.id.txtBikeName);
            txtTypeName = itemView.findViewById(R.id.txtTypeName);
            txtprice = itemView.findViewById(R.id.txtBikePrice);
            imageBike = itemView.findViewById(R.id.imageBike);
        }
    }

    public interface OnClickIteam{
        void  getPosition(int position);
    }
}
