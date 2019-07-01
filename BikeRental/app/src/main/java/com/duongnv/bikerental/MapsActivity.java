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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.duongnv.bikerental.clusterRenderer.MarkerClusterRenderer;
import com.duongnv.bikerental.model.Bike;
import com.duongnv.bikerental.model.Store;
import com.duongnv.bikerental.presenter.PrintBikePresenter;
import com.duongnv.bikerental.utils.GoogleMapHelper;
import com.duongnv.bikerental.views.GetBikeByStoreView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GetBikeByStoreView, ClusterManager.OnClusterClickListener<Store> {


    private PrintBikePresenter mprintBikePresenter;
    private ImageButton mGps;
    private EditText edtSearch;
    private static final String TAG = "MapsActiviti";
    private boolean mLocationPremisstionsGranted = false;


    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PREMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 16f;

    private FusedLocationProviderClient mfusedLocationProviderClient;
    private GoogleMap mMap;



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


    public void ClickToStore(View view) {
        mprintBikePresenter = new PrintBikePresenter(this);
        mprintBikePresenter.getBikeByStore();

    }

    @Override
    public void getBikeSS(List<Bike> list) {
        Intent intent = new Intent(this, StoreActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bikes", (Serializable) list);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void getBikeFail(String message) {
        Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();

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

    private List<Store> getItems() {
        return Arrays.asList(
                new Store("Store1", new LatLng(10.855356, 106.628619)),
                new Store("Store1", new LatLng(10.851738, 106.622810)),
                new Store("Store1", new LatLng(10.851401, 106.623721)),
                new Store("Store1", new LatLng(10.858401, 106.623621)),
                new Store("Store1", new LatLng(10.851601, 106.623221)),
                new Store("Store1", new LatLng(10.856401, 106.623121)),
                new Store("Store1", new LatLng(10.851401, 106.623521)),
                new Store("Store1", new LatLng(10.852401, 106.623121)),
                new Store("Store1", new LatLng(10.851401, 106.623121)),
                new Store("Store1", new LatLng(10.851401, 106.623181)),
                new Store("Store1", new LatLng(10.853401, 106.623191))
        );
    }
    private void setUpClusterManager(GoogleMap googleMap) {
        ClusterManager<Store> clusterManager = new ClusterManager<>(this, googleMap);
        clusterManager.setRenderer(new MarkerClusterRenderer(this, googleMap, clusterManager));
        googleMap.setOnCameraIdleListener(clusterManager);
        List<Store> items = getItems();
        clusterManager.addItems(items);
        clusterManager.cluster();
    }
}
