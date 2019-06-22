package com.duongnv.bikerental;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duongnv.bikerental.model.Account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    TextView txtFullname, textEmail,txtPhone, txtAddress;
    List<Account> mAccountList;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
             View view = inflater.inflate(R.layout.fragment_account, container, false);
             txtFullname = (TextView) view.findViewById(R.id.txtFullname);
             textEmail = (TextView) view.findViewById(R.id.txtEmail);
             txtPhone = (TextView) view.findViewById(R.id.txtPhone);
             txtAddress = (TextView) view.findViewById(R.id.txtAddress);
             mAccountList = new ArrayList<>();
             mAccountList  =(List<Account>) getActivity().getIntent().getExtras().getSerializable("key");
             txtFullname.setText(mAccountList.get(0).getFullname()+"");
             textEmail.setText(mAccountList.get(0).getEmail()+ "");
             txtPhone.setText(mAccountList.get(0).getPhonnumber());
             txtAddress.setText(mAccountList.get(0).getAddress());
             return view;


    }


}
