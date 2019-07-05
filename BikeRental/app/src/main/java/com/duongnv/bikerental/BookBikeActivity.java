package com.duongnv.bikerental;

import android.app.DatePickerDialog;
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
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookBikeActivity extends AppCompatActivity {

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

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");



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
        textPrice.setText(bikes.getPrice()+ "đ");
    }


    public void sumDay(){
        int sum = (int) ((calendarReturn.getTimeInMillis() - calendarRental.getTimeInMillis()) / (1000*60*60*24));

        if(sum <= 0 || sum >7){
            Toast.makeText(BookBikeActivity.this, "Only Rental for 7 day", Toast.LENGTH_SHORT).show();
            edtDateReturn.setText("");
            textSumDay.setText("Total "+ "0" + " Day");
        }
        else {
            textSumDay.setText("Total "+ sum + " Day");
            float price = Float.parseFloat(bikes.getPrice());
            float totalPrice = sum * price;
            textTotalPrice.setText(totalPrice+ "đ");
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
                        calendarRental.set(dayOfMonth, monthOfYear, year);

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
                        calendarReturn.set(dayOfMonth, monthOfYear, year);

                        edtDateReturn.setText(simpleDateFormat.format(calendarReturn.getTime()));
                        sumDay();

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

}
