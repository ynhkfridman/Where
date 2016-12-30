package com.example.eddy.where;

import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.net.URL;

import static com.example.eddy.where.R.id.url;

public class me_activity extends AppCompatActivity {

    public Button but_21;
    public Button but_22;

    public void init() {
        but_21 = (Button) findViewById(R.id.but_21);
        but_22 = (Button) findViewById(R.id.but_22);

        but_21.setOnClickListener(new View.OnClickListener() {
            // @override
            public void onClick(View v) {
                try {
                    Intent k = new Intent(me_activity.this, MapsActivity.class);
                    startActivity(k);

                } catch (Exception e) {

                }

            }


        });
        but_22.setOnClickListener(new View.OnClickListener() {
            // @override
            public void onClick(View v) {
                try {
                    Intent k = new Intent(me_activity.this, QRActivity.class);
                    startActivity(k);
                } catch (Exception e) {

                }

            }


        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_activity);
        init();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        if (resultCode == 1075) {
            final String result = intent.getStringExtra("BLE_Status");

            //LatLng point = Config.currentLocation;
//            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            try {
              //  URL url = new URL(result);
                TextView tv = (TextView)findViewById(R.id.textView4);
                tv.setText(result);
            } catch (Exception e) {
            }
        }
    }

    public void startBLE(View v) {
        Intent i = new Intent(this, BLEActivity.class);
        startActivityForResult(i, 1);
    }

}
