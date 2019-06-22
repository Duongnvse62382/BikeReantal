package com.duongnv.bikerental;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.duongnv.bikerental.presenter.RegisterAccountPresenter;
import com.duongnv.bikerental.views.InsertAccountView;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements InsertAccountView {

    private Toolbar mToolbar;
    RegisterAccountPresenter mRegisterAccountPresenter;
    private Button btnDatePicker;
    private EditText etDate;
    private int mYear, mMonth, mDay;
    private EditText edtUserName, edtPassword, edtConfirm, edtPhone, edtEmail, edtFullname, edtAddress;
    private String username, password, fullname, email, phone, address;
    String birthDay;
    int role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etDate = findViewById(R.id.editDate);
        btnDatePicker = findViewById(R.id.btnDate);
        edtUserName = findViewById(R.id.editUser);
        edtPassword = findViewById(R.id.editPassword);
        edtConfirm = findViewById(R.id.editConfirm);
        edtPhone = findViewById(R.id.editPhone);
        edtEmail = findViewById(R.id.editEmail);
        edtFullname = findViewById(R.id.editFullname);
        edtAddress = findViewById(R.id.editAddress);
        mToolbar = findViewById(R.id.toolbar_register);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    public void getDatePick(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        etDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }



    public void ClickToRegister(View view) {
        if(!validUsername() | !validPasssrd() | !validConfirm() | !validPhone() | !validEmail()){
            return;
        }
        username = edtUserName.getText().toString();
        password = edtPassword.getText().toString();
        fullname = edtFullname.getText().toString();
        email = edtEmail.getText().toString();
        birthDay = etDate.getText().toString();
        phone = edtPhone.getText().toString();
        address = edtAddress.getText().toString();

        mRegisterAccountPresenter = new RegisterAccountPresenter(this);
        mRegisterAccountPresenter.insertAcocunt(username,password,fullname,phone,email,address,birthDay,1);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean validUsername(){
        String username = edtUserName.getText().toString().trim();
        if(username.isEmpty()){
            edtUserName.setError("Username Can't be blank!");
            return false;
        }
        else{
            edtUserName.setError(null);
            return  true;
        }
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
        if(confirm.isEmpty()){
            edtConfirm.setError("ConfirmPassword Can't be blank!");
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

    @Override
    public void InsertSS(String message) {
        Toast.makeText(this,"get Data success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void InsertFail(String massage) {

    }
}
