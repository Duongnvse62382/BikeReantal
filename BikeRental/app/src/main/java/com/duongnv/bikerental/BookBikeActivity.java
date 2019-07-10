package com.duongnv.bikerental;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.presenter.BookingBikePresenter;
import com.duongnv.bikerental.views.BookingBikeView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookBikeActivity extends AppCompatActivity  implements BookingBikeView {


    private BookingBikePresenter mbookingBikePresenter;

    private Bike bikes;
    private TextView textSumDay, textName, textType, textPrice, textDeciption, textTotalPrice;
    private EditText etDateRental, edtDateReturn;
    private int mYear, mMonth, mDay;
    private ImageView btnDateRental, btndateRuturn, imagebike;
    Calendar calendarRental, calendarReturn;
    private ImageView iconback;
    SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_bike);
        etDateRental = findViewById(R.id.editDate);
        iconback = findViewById(R.id.iconback);
        btnDateRental = findViewById(R.id.btnDateRental);
        btndateRuturn = findViewById(R.id.btnDatereturn);
        edtDateReturn = findViewById(R.id.editDateReturn);
        textSumDay = findViewById(R.id.txtTotal);
        textTotalPrice = findViewById(R.id.txtTotalPrice);
        imagebike = findViewById(R.id.imageBikeb);
        textName = findViewById(R.id.txtBikeName);
        textType = findViewById(R.id.txtTypeName);
        textPrice = findViewById(R.id.txtPrice);
        textDeciption = findViewById(R.id.txtDecription);

        simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");



        iconback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        btnDateRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDateRental();
            }
        });

        btndateRuturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getDateReturn();
        }
        });

        BikeInfo();








    }


    public void BikeInfo(){
        Bundle bundle = getIntent().getExtras();
        bikes = (Bike) bundle.getSerializable("bikes");
        Picasso.get().load(bikes.getImage()).into(imagebike);
        textName.setText(bikes.getBikeName()+ "");
        textType.setText(bikes.getTypeName()+ "");
        textDeciption.setText(bikes.getDescription()+ "");
        textPrice.setText(bikes.getPrice()+ "$/day");
    }


    public void sumDay(){

        if(!validDate()){
            Toast.makeText(BookBikeActivity.this, "Please choose Day Rental", Toast.LENGTH_SHORT).show();
            edtDateReturn.setText("");
            return;
        }

            int sum = (int) ((calendarReturn.getTimeInMillis() - calendarRental.getTimeInMillis()) / (1000 * 60 * 60 * 24));
            if (sum <= 0 || sum > 7) {
                Toast.makeText(BookBikeActivity.this, "Only Rental for 7 day", Toast.LENGTH_SHORT).show();
                edtDateReturn.setText("");
                textSumDay.setText("0" + "");
            } else {
                textSumDay.setText(sum + "");
                int price = Integer.parseInt(bikes.getPrice());
                int totalPrice = sum * price;
                textTotalPrice.setText(totalPrice + "");
            }


    }


    public void getDateRental(){
        calendarRental = Calendar.getInstance();
        mDay = calendarRental.get(Calendar.DATE);
        mMonth = calendarRental.get(Calendar.MONTH);
        mYear = calendarRental.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet (DatePicker view, int dayOfMonth,
                                          int monthOfYear, int year)  {
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
            Toast.makeText(BookBikeActivity.this, "Please choose Date", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return  true;
        }
    }

    public boolean validDateReturn(){
        String dateReturn = edtDateReturn.getText().toString().trim();
        if(dateReturn.isEmpty()){
            Toast.makeText(BookBikeActivity.this, "Please choose Date", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return  true;
        }
    }


    public void ClickToBook(View view) {
        if(!validDate() | !validDateReturn()){
            return;
        }

        BikeInfo();
        String amount = textTotalPrice.getText().toString();
        int slot = Integer.parseInt(textSumDay.getText().toString());
        String bikerental = etDateRental.getText().toString();
        String bikeRetuen = edtDateReturn.getText().toString();
        int bikeID = bikes.getBikeID();
        mbookingBikePresenter =  new BookingBikePresenter(this);
        mbookingBikePresenter.bookingBike(amount, slot, bikeID, bikerental, bikeRetuen);


    }


    @Override
    public void InsertSS(String message) {
        Intent intent =  new Intent(BookBikeActivity.this, StoreActivity.class);
        finish();
        startActivity(intent);
        Toast.makeText(BookBikeActivity.this,"ss",Toast.LENGTH_SHORT).show();
    }




    @Override
    public void InsertFail(String massage) {

    }


}
