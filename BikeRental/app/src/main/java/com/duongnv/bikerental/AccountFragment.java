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
    List<Account> mAccountList;

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

             mAccountList = new ArrayList<>();
             mAccountList  =(List<Account>) getActivity().getIntent().getExtras().getSerializable("key");
             txtFullname.setText(mAccountList.get(0).getFullname()+"");
             textEmail.setText(mAccountList.get(0).getEmail()+ "");
             txtPhone.setText(mAccountList.get(0).getPhonnumber());
             txtAddress.setText(mAccountList.get(0).getAddress());

             buttonEdit.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(getActivity().getBaseContext(),EditProfileActivity.class);
                     Bundle bundle = new Bundle();
                     bundle.putSerializable("keyedit",mAccountList.get(0));
                     intent.putExtras(bundle);
                     getActivity().startActivity(intent);
                 }
             });




             return view;


    }


}
