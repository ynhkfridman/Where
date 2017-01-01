package com.example.eddy.where;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public Button but_saveGPS;
    private GoogleMap mMap;
    private FirebaseUser user;
    private LocationManager locationManager;
    private Firebase ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        user=Config.getUser();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        but_saveGPS =(Button)findViewById(R.id.SaveGps);
        but_saveGPS.setOnClickListener(new View.OnClickListener(){
            // @override
            public void onClick(View v) {

                try
                {
                    getCurrentLocation();

                }catch(Exception e){

                }

            }


        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        Firebase.setAndroidContext(this);

        ref = new Firebase(Config.FIREBASE_URL);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                mMap.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    if (postSnapshot.child("latitude").exists() && postSnapshot.child("longitude").exists()) {
                        double lat = postSnapshot.child("latitude").getValue(Double.class);
                        double lon = postSnapshot.child("longitude").getValue(Double.class);
                        String name = postSnapshot.child("provider").getValue(String.class);
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(lat, lon))
                                .title(name));
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }


    void getCurrentLocation() {
        Location myLocation  = mMap.getMyLocation();
        if(myLocation!=null)
        {
            double dLatitude = myLocation.getLatitude();
            double dLongitude = myLocation.getLongitude();
            Log.i("APPLICATION"," : "+dLatitude);
            Log.i("APPLICATION"," : "+dLongitude);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();//"dLatitude: "+dLatitude+".dLongitude: "+dLongitude);
            myLocation.setProvider(Config.getUser().getDisplayName());
            myRef.child(Config.getUser().getDisplayName()).setValue(myLocation);

            //Firebase ref = new Firebase(Config.getURL()).child("12345");

            //myRef.setValue("Hello, World!");

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dLatitude, dLongitude), 8));

        }
        else
        {
            Toast.makeText(this, "Unable to fetch the current location", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        Config.currentLocation = sydney;
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        //    getCurrentLocation();
            return;
        }
      //  getCurrentLocation();
        mMap.setMyLocationEnabled(true);
       // Firebase ref = new Firebase(Config.getURL()).child("12345");
       // ref.setValue("on the bahaiim");

    }

}
