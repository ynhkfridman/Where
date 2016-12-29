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

import com.firebase.client.Firebase;
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

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

  /* private Location getLastKnownLocation() {////////////////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            List<String> providers = locationManager.getProviders(true);
            Location bestLocation = null;
            for (String provider : providers) {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
            double speed= (Math.sqrt(Math.pow(currentLocation.getLatitude() - lastLocation.getLatitude(), 2)
                    + Math.pow((currentLocation.getLongitude() - lastLocation.getLongitude()),2)))
                    / (lastLocation.getTime()-currentLocation.getTime());
            lastLocation = currentLocation;
            currentLocation = bestLocation;

            Toast.makeText(this, "Speed: " + speed, Toast.LENGTH_SHORT).show();

            return bestLocation;
        } catch (SecurityException e) {

        }
        return null;
    }

    //
    public void SaveGPSLocation(View v) {
        Location location = getLastKnownLocation();
        Firebase ref = new Firebase(Config.FIREBASE_URL);
        if (Config.getUser() != null) {
            String name = Config.getUser().getDisplayName();
            if (location != null) {
                ref.child(name).removeValue();

                LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
                ref.child(name).child("Latitude").setValue(location.getLatitude());
                ref.child(name).child("Longitude").setValue(location.getLongitude());
                ref.child(name).child("Nickname").setValue(name);


                Toast.makeText(this, "Saving Location", Toast.LENGTH_SHORT).show();
            } else {
                //Location is null
            }

        } else {
            //   Toast.makeText(this, "User Not Found, return to login page", Toast.LENGTH_SHORT).show();
        }

    }/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    */

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
