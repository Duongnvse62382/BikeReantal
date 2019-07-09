package com.duongnv.bikerental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.presenter.LoginAccountPresenter;
import com.duongnv.bikerental.views.LoginAccountView;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoginAccountView {
//    LoginButton btnLogin;
    private EditText edtUserName, edtPassword;
    private LoginAccountPresenter mloginAccountPresenter;
//    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUserName = findViewById(R.id.editUsername);
        edtPassword = findViewById(R.id.editPassword);
//        btnLogin = findViewById(R.id.login_button);

//        callbackManager = CallbackManager.Factory.create();
//        btnLogin.setReadPermissions(Arrays.asList("email","public_profile"));
//
//        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                    String token = loginResult.getAccessToken().getToken().toString();
//                System.out.println("token : " + token);
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                error.printStackTrace();
//            }
//        });
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//    }



    public void clickToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void ClickToHome(View view) {
        if(!validUsername() | !validPasssrd()){
            return;
        }
        String username = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();
        mloginAccountPresenter = new LoginAccountPresenter(this);
        mloginAccountPresenter.loginAccount(username, password);

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
    public void loginSS(Account account) {
        Intent intent = new Intent(this, HomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", (Serializable) account);
        intent.putExtras(bundle);
        finish();
        startActivity(intent);


    }

    @Override
    public void loginFail(String message) {

        Toast.makeText(MainActivity.this,"Username or Password Wrong!",Toast.LENGTH_LONG).show();
        edtPassword.setText("");
    }
}
