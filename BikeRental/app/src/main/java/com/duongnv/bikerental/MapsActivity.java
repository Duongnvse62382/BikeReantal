package com.duongnv.bikerental;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.duongnv.bikerental.clusterRenderer.MarkerClusterRenderer;
import com.duongnv.bikerental.model.Account;
import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.model.Store;
import com.duongnv.bikerental.presenter.PrintBikePresenter;
import com.duongnv.bikerental.presenter.PrintStorePresenter;
import com.duongnv.bikerental.utils.GoogleMapHelper;
import com.duongnv.bikerental.views.GetBikeByStoreView;
import com.duongnv.bikerental.views.GetStoreView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, ClusterManager.OnClusterClickListener<Store>,
        GetStoreView {


    private Account mAccount;
    PrintStorePresenter mprintStorePresenter;
    private Random mRandom = new Random(1984);
    private ImageButton mGps, imageLogo;
    private EditText edtSearch;

    private static final String TAG = "MapsActiviti";
    private boolean mLocationPremisstionsGranted = false;


    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PREMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 16f;

    private FusedLocationProviderClient mfusedLocationProviderClient;
    private GoogleMap mMap;

    private List<Store> stores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_maps);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(googleMap -> {
            this.mMap = googleMap;
            GoogleMapHelper.defaultMapSettings(googleMap);
            setUpClusterManager(googleMap);
        });






        edtSearch = findViewById(R.id.editSearch);
        mGps = findViewById(R.id.ic_gps);
        getLocationPremission();
        init();


        Bundle bundle = getIntent().getExtras();
        mAccount = (Account) bundle.getSerializable("account");
    }







    private void init(){
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE
                            || event.getAction() == KeyEvent.ACTION_DOWN
                            || event.getAction() == KeyEvent.KEYCODE_SEARCH){
                        geoLocate();


                }
                return false;
            }
        });
        geoLocate();




        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDeviceLocation();
            }
        });
        hideSoftKeyBround();
    }

    private void geoLocate(){

        String search = edtSearch.getText().toString();
        Geocoder geocoder = new Geocoder(MapsActivity.this);

        List<Address> list = new ArrayList<>();
        try {
             list = geocoder.getFromLocationName(search, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(list.size()>0){
            Address address = list.get(0);
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM, address.getAddressLine(0));
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mLocationPremisstionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                                           != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            init();
        }

    }
    private void getDeviceLocation(){
        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            if(mLocationPremisstionsGranted){

                Task location = mfusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Location currentLocation = (Location) task.getResult();
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM, "My Location");
                        }else{
                            Toast.makeText(MapsActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        }catch (SecurityException e){
            Log.e(TAG, "getDiviceLocation " + e.getMessage());
        }
    }


    private void moveCamera(LatLng latLng, float zoom, String title){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        if(!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }
        hideSoftKeyBround();
    }

    private void intiMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
    }

    private void getLocationPremission() {
        String[] premission = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPremisstionsGranted = true;
                intiMap();
            } else {
                ActivityCompat.requestPermissions(this, premission, LOCATION_PREMISSION_REQUEST_CODE);

            }
        } else {
            ActivityCompat.requestPermissions(this, premission, LOCATION_PREMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPremisstionsGranted = false;
        switch (requestCode){
            case LOCATION_PREMISSION_REQUEST_CODE:{
                if(grantResults.length>0){
                    for (int i = 0 ; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPremisstionsGranted = false;
                            return;
                        }
                    }
                    mLocationPremisstionsGranted = true;
                    intiMap();
                }
            }
        }
    }

    private void hideSoftKeyBround(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }




    public void showDialog(){
        LayoutInflater layoutInflater  =getLayoutInflater();
        View alertLayout  = layoutInflater.inflate(R.layout.dialog, null);
        final Button btnGotoStore;
        btnGotoStore = (Button) alertLayout.findViewById(R.id.btnGotostore);
        btnGotoStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, StoreActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("account", mAccount);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(true);
        AlertDialog dialog = alert.create();
        dialog.show();

    }


    @Override
    public boolean onClusterClick(Cluster<Store> cluster) {
        if (cluster == null) return false;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Store store : cluster.getItems())
            builder.include(store.getPosition());
        LatLngBounds bounds = builder.build();
        try {
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    private void initData(){
        mprintStorePresenter = new PrintStorePresenter(this);
        mprintStorePresenter.getStore();
        stores = new ArrayList<>();


    }

    private List<Store> getItems() {
//        initData();
//        getBikeSS(stores);
//        int i;
//        for (i = 0; i < stores.size(); i++){
//            stores.get(i);
//        }

        return Arrays.asList(


                new Store("Store1", position()),
                new Store("Store2", position()),
                new Store("Store3", position()),
                new Store("Store4", position()),
                new Store("Store5", position())
//                new Store("Store7", position()),
//                new Store("Store8", position()),
//                new Store("Store9", position()),
//                new Store("Store10", position())
        );
    }


    private void setUpClusterManager(GoogleMap googleMap) {
        ClusterManager<Store> clusterManager = new ClusterManager<>(this, googleMap);
        clusterManager.setRenderer(new MarkerClusterRenderer(this, googleMap, clusterManager));
        googleMap.setOnCameraIdleListener(clusterManager);
        List<Store> items = getItems();
        clusterManager.addItems(items);
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                showDialog();
                return true;
            }
        });
        clusterManager.cluster();
    }

    @Override
    public void getBikeSS(List<Store> list) {
    }

    @Override
    public void getBikeFail(String message) {

    }

    private LatLng position() {
        return new LatLng(random(10.842702, 10.861058), random(106.617169, 106.647742));
    }

    private double random(double min, double max) {
        return mRandom.nextDouble() * (max - min) + min;
    }
}
