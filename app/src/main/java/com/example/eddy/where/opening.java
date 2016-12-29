package com.example.eddy.where;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.app.Activity;




import android.widget.Button;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class opening extends AppCompatActivity {



    public Button where_i_am_button;

    public Button where_my_friends_button;



    public void init(){
        where_i_am_button=(Button)findViewById(R.id.but_1_1);
        where_my_friends_button=(Button)findViewById(R.id.but_1_2);

        where_i_am_button.setOnClickListener(new View.OnClickListener(){
            // @override
            public void onClick(View v) {

                try
                {
                    Intent k = new Intent(opening.this,me_activity.class);
                    startActivity(k);
                }catch(Exception e){

                }

            }


        });

        where_my_friends_button.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v) {
                ///now we go to fire base
                try
                {
//                    Intent k = new Intent(opening.this,they_activity.class);
//                    startActivity(k);
                    // TODO Meni: where this class?
                }catch(Exception e){

                }

            }




        });



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        init();
        Context context = getApplicationContext();
        CharSequence text = "Hello to our app that is the average app ever!! again";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
