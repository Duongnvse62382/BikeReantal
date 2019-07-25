package com.duongnv.bikerental;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duongnv.bikerental.adapter.HistoryAdapter;
import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.model.Booking;
import com.duongnv.bikerental.model.BookingDetails;
import com.duongnv.bikerental.presenter.HistoryBookingPresenter;
import com.duongnv.bikerental.views.HistoryBookingView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements HistoryBookingView {


    private HistoryBookingPresenter mHistoryBookingPresenter;
    private HistoryAdapter historyAdapter;
    private Account mAccount;
    private RecyclerView mrecyclerView;
    private List<Booking> bookingList;
    private TextView textHistory;

    private List<BookingDetails> bookingDetails;
    private BookingDetails details;
    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        textHistory = (TextView) view.findViewById(R.id.txtHistory);
        mrecyclerView = (RecyclerView) view.findViewById(R.id.listhistory);

        mAccount  =(Account) getActivity().getIntent().getExtras().getSerializable("key");

        //set layout cho recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(linearLayoutManager);
        bookingList = new ArrayList<>();
        mHistoryBookingPresenter = new HistoryBookingPresenter(this);
        mHistoryBookingPresenter.getHistoryBooking(mAccount.getUserID());
        return view;
    }


    @Override
     public  final  void onResume() {

        mHistoryBookingPresenter.getHistoryBooking(mAccount.getUserID());
        super.onResume();

    }

    public void updateViewHistory(){
        if(historyAdapter == null){
            historyAdapter = new HistoryAdapter(getContext() ,bookingDetails);
            mrecyclerView.setAdapter(historyAdapter);

        }
        else{
            historyAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void getHistorySS(List<Booking> list) {
        bookingList = list;
        bookingDetails = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
               details = new BookingDetails();
               details.setBikeName(list.get(i).getBookingDetails().get(0).getBikeName());
               details.setRentalBike(list.get(i).getBookingDetails().get(0).getRentalBike());
               details.setReturnBike(list.get(i).getBookingDetails().get(0).getReturnBike());
               bookingDetails.add(details);
        }
        if(list.size() > 0){
            textHistory.setText("");
        }
        updateViewHistory();
    }

    @Override
    public void getFail(String msg) {

    }
}
