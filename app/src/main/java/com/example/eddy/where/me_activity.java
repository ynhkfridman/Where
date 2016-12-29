package com.example.eddy.where;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


import android.widget.Button;

public class me_activity extends AppCompatActivity {

    public Button but_21;
    public Button but_22;
    public void init(){
        but_21 =(Button)findViewById(R.id.but_21);
        but_22=(Button)findViewById(R.id.but_22);

        but_21.setOnClickListener(new View.OnClickListener(){
            // @override
            public void onClick(View v) {
                try
                {
                    Intent k = new Intent(me_activity.this,MapsActivity.class);
                    startActivity(k);

                }catch(Exception e){

                }

            }


        });
        but_22.setOnClickListener(new View.OnClickListener(){
            // @override
            public void onClick(View v) {
                try
                {
                    Intent k = new Intent(me_activity.this,QRActivity.class);
                    startActivity(k);
                }catch(Exception e){

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
}
