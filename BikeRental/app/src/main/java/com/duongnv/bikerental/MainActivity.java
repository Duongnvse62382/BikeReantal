package com.duongnv.bikerental;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.presenter.LoginAccountPresenter;
import com.duongnv.bikerental.views.LoginAccountView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoginAccountView {
    Button btnLogin;
    private EditText edtUserName, edtPassword;
    private LoginAccountPresenter mloginAccountPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUserName = findViewById(R.id.editUsername);
        edtPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }






    public void clickToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void ClickToHome(View view) {
        if(!validUsername() | !validPasssrd()){
            return;
        }
        mloginAccountPresenter = new LoginAccountPresenter(this);
        mloginAccountPresenter.loginAccount();
        Toast.makeText(MainActivity.this,"Login success!",Toast.LENGTH_LONG).show();
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

    @Override
    public void loginSS(List<Account> list) {
        Intent intent = new Intent(this, HomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", (Serializable) list);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail(String message) {

        Toast.makeText(MainActivity.this,"Username or Password Wrong!",Toast.LENGTH_LONG).show();
    }
}