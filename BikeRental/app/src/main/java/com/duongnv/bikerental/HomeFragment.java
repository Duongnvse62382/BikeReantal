package com.duongnv.bikerental;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {



    ImageButton imgMotorbike;
    ImageButton imgScooter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_home, container, false);
        imgMotorbike = (ImageButton) view.findViewById(R.id.btnimMortobike);
        imgScooter = (ImageButton) view.findViewById(R.id.imgScooter) ;

        imgMotorbike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), MapsActivity.class);
                getActivity().startActivity(intent);
            }
        });

        imgScooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), MapsActivity.class);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }

}
