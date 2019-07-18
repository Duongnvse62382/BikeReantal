package com.duongnv.bikerental;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.presenter.BookingBikePresenter;
import com.duongnv.bikerental.views.BookingBikeView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BookBikeActivity extends AppCompatActivity  implements BookingBikeView, OnMapReadyCallback  {


    private static final String TAG = "BookBikeActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 16f;

    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;


    private BookingBikePresenter mbookingBikePresenter;
    private Bike bikes;
    private Account mAccount;
    private TextView textSumDay, textFullname, textPhoneNo, textTotalPrice, textAddress;
    private EditText etDateRental, edtDateReturn;
    private int mYear, mMonth, mDay;
    private ImageView btnDateRental, btndateRuturn;
    Calendar calendarRental, calendarReturn;
    private ImageView iconback;
    SimpleDateFormat simpleDateFormat;
    private GoogleMap mmap;


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
//        imagebike = findViewById(R.id.imageBikeb);
        textFullname = findViewById(R.id.txtFullname);
        textPhoneNo = findViewById(R.id.txtPhoneNo);
        textAddress = findViewById(R.id.textAddress);
//        textPrice = findViewById(R.id.txtPrice);
//        textDeciption = findViewById(R.id.txtDecription);

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
        getLocationPermission();






    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

        }

    }


    private List<Address> addresses;
    private Geocoder geocoder;
    private void getDeviceLocation(){
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM);
                            geocoder =  new Geocoder(BookBikeActivity.this, Locale.getDefault());
                            try {
                                addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
                                String address = addresses.get(0).getAddressLine(0);

                                textAddress.setText("[ " +address + " ]");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }else{
                            Toast.makeText(BookBikeActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            e.getMessage();
        }
    }


    private void moveCamera(LatLng latLng, float zoom){

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }






    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(BookBikeActivity.this);
    }


    private void getLocationPermission(){
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    initMap();
                }
            }
        }
    }






    public void BikeInfo(){
        Bundle bundle = getIntent().getExtras();
        bikes = (Bike) bundle.getSerializable("bikes");
        mAccount = (Account) bundle.getSerializable("account");
//        Picasso.get().load(bikes.getImage()).into(imagebike);
        textFullname.setText(mAccount.getFullname()+ "");
        textPhoneNo.setText(mAccount.getPhonnumber()+ "");
//        textDeciption.setText(bikes.getDescription()+ "");
//        textPrice.setText(bikes.getPrice()+ "$/day");
    }

     private int sum;
    private int totalPrice;

    public void sumDay(){

        if(!validDate()){
            Toast.makeText(BookBikeActivity.this, "Please choose Day Rental", Toast.LENGTH_SHORT).show();
            edtDateReturn.setText("");
            return;
        }

        sum = (int) ((calendarReturn.getTimeInMillis() - calendarRental.getTimeInMillis()) / (1000 * 60 * 60 * 24));
            if (sum <= 0 || sum > 7) {
                Toast.makeText(BookBikeActivity.this, "Only Rental for 7 day", Toast.LENGTH_SHORT).show();
                edtDateReturn.setText("");
                textSumDay.setText("0" + "");
            } else {
                textSumDay.setText("Total " + sum + " Day");
                int price = Integer.parseInt(bikes.getPrice());
                totalPrice = sum * price;
                textTotalPrice.setText(totalPrice + "$");
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
//        String amount = textTotalPrice.getText().toString();
//        int slot = Integer.parseInt(textSumDay.getText().toString());
        int amount = totalPrice;
        int slot = sum;
        String bikerental = etDateRental.getText().toString();
        String bikeRetuen = edtDateReturn.getText().toString();
        int bikeID = bikes.getBikeID();
        int userID = mAccount.getUserID();
        mbookingBikePresenter =  new BookingBikePresenter(this);
        mbookingBikePresenter.bookingBike(amount, slot, userID, bikeID, bikerental, bikeRetuen);


    }


    @Override
    public void InsertSS(String message) {
        Intent intent =  new Intent(BookBikeActivity.this, PaymentInfoActivity.class);
        Bundle bundle  = new Bundle();
        bundle.putSerializable("bikepm", bikes);
        bundle.putInt("total", totalPrice);
        bundle.putString("rental", etDateRental.getText().toString());
        bundle.putString("return", edtDateReturn.getText().toString());
        bundle.putString("address", textAddress.getText().toString());
        intent.putExtras(bundle);

        finish();
        startActivity(intent);

    }




    @Override
    public void InsertFail(String massage) {

    }


}
