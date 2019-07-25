package com.duongnv.bikerental;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duongnv.bikerental.databases.CartDatabase;
import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.model.Cart;
import com.duongnv.bikerental.presenter.BookingBikePresenter;
import com.duongnv.bikerental.presenter.UpdateStatusBikePresenter;
import com.duongnv.bikerental.views.BookingBikeView;
import com.duongnv.bikerental.views.UpdateStatusBikeView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class BookingCartActivity extends AppCompatActivity implements BookingBikeView, UpdateStatusBikeView {

    private CartDatabase cartDatabase;
    private Cart cart;
    List<Cart> cartList;
    private Account mAccount;

    private BookingBikePresenter mbookingBikePresenter;
    private UpdateStatusBikePresenter mupdateStatusBikePresenter;
    private TextView textSumDay,  textTotalPrice;
    private EditText etDateRental, edtDateReturn;
    private int mYear, mMonth, mDay;
    private ImageView btndateRuturn, btndateRental;
    Calendar calendarRental, calendarReturn;
    private ImageView iconback;
    SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_cart);
        init();
        getCartPostion();

        iconback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });




        btndateRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDaterental();
            }
        });

        btndateRuturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDateReturn();
            }
        });
    }

    public void init(){
        etDateRental = findViewById(R.id.editDate);
        iconback = findViewById(R.id.iconback);
        btndateRuturn = findViewById(R.id.btnDatereturn);
        btndateRental = findViewById(R.id.btnDateRental);
        edtDateReturn = findViewById(R.id.editDateReturn);

        textSumDay = findViewById(R.id.txtTotal);
        textTotalPrice = findViewById(R.id.txtTotalPrice);
        simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    }


    public void getCartPostion() {
        Bundle bundle = getIntent().getExtras();
        cart = (Cart) bundle.getSerializable("cart");
        mAccount = (Account) bundle.getSerializable("account");
    }



    private int sum;
    private int totalPrice;

    public void sumDay(){

        if(!validDate()){
            Toast.makeText(BookingCartActivity.this, "Please choose Day Rental", Toast.LENGTH_SHORT).show();
            edtDateReturn.setText("");
            return;
        }

        sum = (int) ((calendarReturn.getTimeInMillis() - calendarRental.getTimeInMillis()) / (1000 * 60 * 60 * 24));
        if (sum <= 0 || sum > 7) {
            Toast.makeText(BookingCartActivity.this, "Only Rental for 7 day", Toast.LENGTH_SHORT).show();
            edtDateReturn.setText("");
            textSumDay.setText("0" + "");
        } else {
            textSumDay.setText("Total " + sum + " Day");
            int price = Integer.parseInt(cart.price);
            totalPrice = sum * price;
            textTotalPrice.setText(totalPrice + "$");
        }


    }


    public void getDaterental(){
        calendarRental = Calendar.getInstance();
        mDay = calendarRental.get(Calendar.DATE);
        mMonth = calendarRental.get(Calendar.MONTH);
        mYear = calendarRental.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int dayOfMonth,
                                          int monthOfYear, int year) {
                        calendarRental.set(dayOfMonth , monthOfYear, year);

                        etDateRental.setText(simpleDateFormat.format(calendarRental.getTime()));

                    }

                }, mYear, mMonth, mDay);


        datePickerDialog.show();


    }

    public void getDateReturn(){
        calendarReturn = Calendar.getInstance();
        mDay = calendarReturn.get(Calendar.DATE);
        mMonth = calendarReturn.get(Calendar.MONTH);
        mYear = calendarReturn.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int dayOfMonth,
                                          int monthOfYear, int year) {
                        calendarReturn.set(dayOfMonth , monthOfYear, year);

                        edtDateReturn.setText(simpleDateFormat.format(calendarReturn.getTime()));

                        sumDay();

                    }

                }, mYear, mMonth, mDay);


        datePickerDialog.show();


    }




    public boolean validDate(){
        String dateRental = etDateRental.getText().toString().trim();
        if(dateRental.isEmpty()){
            Toast.makeText(BookingCartActivity.this, "Please choose Time", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return  true;
        }
    }

    public boolean validDateReturn(){
        String dateReturn = edtDateReturn.getText().toString().trim();
        if(dateReturn.isEmpty()){
            Toast.makeText(BookingCartActivity.this, "Please choose Time", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return  true;
        }
    }



        public void ClickToBookCart(View view) {
        if(!validDate() | !validDateReturn()){
            return;
        }

        getCartPostion();


        int status = 0;
        int amount = totalPrice;
        int slot = sum;
        String bikerental = etDateRental.getText().toString();
        String bikeRetuen = edtDateReturn.getText().toString();

            int bikeID = cart.bikeid;
            int userID = mAccount.getUserID();
            mbookingBikePresenter =  new BookingBikePresenter(this);
            mbookingBikePresenter.bookingBike(amount, slot, userID, bikeID, bikerental, bikeRetuen);
            mupdateStatusBikePresenter = new UpdateStatusBikePresenter(this);
            mupdateStatusBikePresenter.updateStatusBike(cart.bikeid, cart.bikename, cart.image, cart.decription, cart.price, status, 1, 5);


    }
    @Override
    public void InsertSS(String message) {
        Toast.makeText(BookingCartActivity.this, "Book Bike rental", Toast.LENGTH_SHORT).show();
        deleteItem(cart);
        finish();

    }

    @Override
    public void InsertFail(String massage) {

    }



    @Override
    public void updateSS(String message) {
        finish();

    }

    @Override
    public void updateFail(String massage) {

    }

    private void deleteItem(final Cart cart) {
        cartDatabase = CartDatabase.getInstance(this);
        class DeleteItem extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                cartDatabase.cartDAO().delete(cart);
                return null;
            }

        }
        DeleteItem dt = new DeleteItem();
        dt.execute();
    }



}
