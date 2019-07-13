package com.duongnv.bikerental;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.duongnv.bikerental.model.Account;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {



    ImageButton imgMotorbike;
    ImageButton imgScooter;
    Account mAccount;
//    TextView textusername;

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
//        textusername =  (TextView) view.findViewById(R.id.txtusername);
        mAccount  =(Account) getActivity().getIntent().getExtras().getSerializable("key");
//        textusername.setText(mAccount.getUsername() + "");

        imgMotorbike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), MapsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("account",mAccount);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });

        imgScooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), MapsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("account",mAccount);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }

}
