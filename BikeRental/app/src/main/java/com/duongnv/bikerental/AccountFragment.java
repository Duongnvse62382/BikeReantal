package com.duongnv.bikerental;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duongnv.bikerental.model.Account;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    TextView txtFullname, textEmail,txtPhone, txtAddress;
    TextView buttonEdit;
    Account mAccount;

    public AccountFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
             View view = inflater.inflate(R.layout.fragment_account, container, false);
             buttonEdit =  (TextView) view.findViewById(R.id.btnEdit);
             txtFullname = (TextView) view.findViewById(R.id.txtFullname);
             textEmail = (TextView) view.findViewById(R.id.txtEmail);
             txtPhone = (TextView) view.findViewById(R.id.txtPhone);
             txtAddress = (TextView) view.findViewById(R.id.txtAddress);

             mAccount  =(Account) getActivity().getIntent().getExtras().getSerializable("key");
             txtFullname.setText(mAccount.getFullname()+"");
             textEmail.setText(mAccount.getEmail()+ "");
             txtPhone.setText(mAccount.getPhonnumber()+ "");
             txtAddress.setText(mAccount.getAddress()+ "");
              buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(),EditProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("keyedit",mAccount);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
             return view;


    }


}
