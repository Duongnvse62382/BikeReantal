package com.duongnv.bikerental;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.presenter.UpdateAccountPresenter;
import com.duongnv.bikerental.views.UpdateAccountView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity implements UpdateAccountView{

    private UpdateAccountPresenter mupdateAccountPresenter;

    private Button btnDatePicker;
    private EditText etDate;
    private int mYear, mMonth, mDay;
    private TextView textUsername;
    private Account mAccount;
    private EditText  edtPassword, edtConfirm, edtPhone, edtEmail, edtAddress, edtFullname;

    private String  username, password, fullname, email, phone, address, birthDay;
    private int userid, role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        etDate = findViewById(R.id.editDate);
        btnDatePicker = findViewById(R.id.btnDate);
        edtPassword = findViewById(R.id.editPassword);
        edtConfirm = findViewById(R.id.editConfirm);
        edtPhone = findViewById(R.id.editPhone);
        edtEmail = findViewById(R.id.editEmail);
        edtAddress = findViewById(R.id.editAddress);
        textUsername = findViewById(R.id.txtUsername);
        edtFullname = findViewById(R.id.editFullname);
       viewAccount();



        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                getDatePick();
            }
        });

    }

    public void viewAccount(){

        Bundle bundle = getIntent().getExtras();
        mAccount = (Account) bundle.getSerializable("keyedit");
        textUsername.setText("Username: "+ mAccount.getUsername() + "");
        edtPassword.setText(mAccount.getPassword()+ "");
        edtConfirm.setText(mAccount.getPassword()+"");
        edtEmail.setText(mAccount.getEmail()+ "");
        edtPhone.setText(mAccount.getPhonnumber()+ "");
        edtAddress.setText(mAccount.getAddress()+"");
        edtFullname.setText(mAccount.getFullname()+"");
        etDate.setText(mAccount.getBrithdate()+"");

    }

    public void getDatePick(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        etDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }


    public void ClickToSave(View view) {
        if(!validPasssrd() | !validConfirm() | !validPhone() | !validEmail()){
            return;
        }

//        password = edtPassword.getText().toString();
//        fullname = edtFullname.getText().toString();
//        email = edtEmail.getText().toString();
//        birthDay = etDate.getText().toString();
//        phone = edtPhone.getText().toString();
//        address = edtAddress.getText().toString();
//        mupdateAccountPresenter =  new UpdateAccountPresenter(this);
//        mupdateAccountPresenter.updateAccount(mAccount.getUserID(), mAccount.getUsername(), password, fullname, phone, email, address, birthDay, mAccount.getRole());

    }


    public boolean validPasssrd(){
        String password = edtPassword.getText().toString().trim();
        if(password.isEmpty()){
            edtPassword.setError("Password Can't be blank!");
            return false;
        }
        else{
            edtPassword.setError(null);
            return  true;
        }
    }

    public boolean validConfirm(){
        String confirm = edtConfirm.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if(password.isEmpty()){
            edtPassword.setError("Confirm password Can't be blank!");
            return false;
        }
        else if (!confirm.equals(password)){
            edtConfirm.setError("Confirm Password Wrong");
            return false;
        }
        else {
            edtConfirm.setError(null);
            return  true;
        }
    }


    public boolean validPhone(){
        String phoneNo = edtPhone.getText().toString().trim();
        if(phoneNo.isEmpty()){
            edtPhone.setError("PhoneNumber Can't be blank!");
            return false;
        }
        else {
            edtPhone.setError(null);
            return  true;
        }
    }

    public boolean validEmail(){
        String email = edtEmail.getText().toString().trim();
        if(email.isEmpty()){
            edtEmail.setError("Email Can't be blank!");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Please enter a valid email address");
            return false;
        }
        else {
            edtEmail.setError(null);
            return  true;
        }
    }


    public void ClickToBack(View view) {
        finish();
    }

    @Override
    public void updateSS(String message) {

        Toast.makeText(this, "Edit Success!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void updateFail(String massage) {

    }
}
