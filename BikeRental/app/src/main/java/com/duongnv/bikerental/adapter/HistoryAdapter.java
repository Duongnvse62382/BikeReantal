package com.duongnv.bikerental.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duongnv.bikerental.R;
import com.duongnv.bikerental.model.Booking;
import com.duongnv.bikerental.model.BookingDetails;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolderBike> {

    private Context context;
    //data json
    List<BookingDetails> bookingDetails;


    public HistoryAdapter(Context context, List<BookingDetails> bookingDetails) {
        this.context = context;
        this.bookingDetails = bookingDetails;
    }

    @NonNull
    @Override
    public ViewHolderBike onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.show_history, viewGroup, false);
        return new HistoryAdapter.ViewHolderBike(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBike viewHolderBike, int i) {
        viewHolderBike.textNameBike.setText(bookingDetails.get(i).getBikeName());
        viewHolderBike.textRental.setText(bookingDetails.get(i).getRentalBike());
        viewHolderBike.textReturn.setText(bookingDetails.get(i).getReturnBike());
        viewHolderBike.container.getShowDividers();
    }



    @Override
    public int getItemCount() {
        return bookingDetails.size();
    }


    public class ViewHolderBike extends RecyclerView.ViewHolder {

        private TextView textNameBike, textRental, textReturn;
        private LinearLayout container;

        public ViewHolderBike(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.containerHistory);
            textNameBike = itemView.findViewById(R.id.txtNamebike);
            textRental = itemView.findViewById(R.id.dayrental);
            textReturn = itemView.findViewById(R.id.dayreturn);
        }
    }
}
