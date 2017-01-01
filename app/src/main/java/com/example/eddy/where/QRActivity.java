package com.example.eddy.where;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eddy.where.IntentIntegrator;
import com.example.eddy.where.IntentResult;
import com.firebase.client.Firebase;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QRActivity extends AppCompatActivity {

    TextView mContentsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        mContentsTextView = (TextView) findViewById(R.id.textView_content);
        startScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            if (scanResult.getContents() != null) {
                Log.d("tag", scanResult.getContents());
                mContentsTextView.setText(scanResult.getContents());
                SaveLocation(scanResult.getContents());
            }
        }

    }

    public void scanButtonPressed(View view) {
        startScan();
    }

    public void startScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    public void SaveLocation(String loactionAndName) {
        Location QRLocation = new Location("fused");
        String[] list = loactionAndName.split(",");
        double lat = Double.parseDouble(list[0]);
        double lng = Double.parseDouble(list[1]);
        String name = list[2];

        // Firebase ref = new Firebase(Config.FIREBASE_URL);
        // ref.child(name).removeValue();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();//"dLatitude: "+dLatitude+".dLongitude: "+dLongitude);
        QRLocation.setLatitude(lat);
        QRLocation.setLongitude(lng);
        QRLocation.setAccuracy(5);
        QRLocation.setProvider(Config.getUser().getDisplayName());
        myRef.child(Config.getUser().getDisplayName()).setValue(QRLocation);

        LatLng point = new LatLng(lat, lng);
        //  ref.child(name).child("Latitude").setValue(lat);
        // ref.child(name).child("Longitude").setValue(lng);
        //ref.child(name).child("Nickname").setValue(name);
        Toast.makeText(this, "Saving Location", Toast.LENGTH_SHORT).show();
    }
}
