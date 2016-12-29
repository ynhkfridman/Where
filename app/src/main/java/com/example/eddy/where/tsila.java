package com.example.eddy.where;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseUser;

public class tsila extends AppCompatActivity {
    public static String friendName;
    public Button but_Go;
    public TextView toremove;
    private String ans;
    private FirebaseUser user;
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tsila);

        Firebase firebase = new Firebase(Config.FIREBASE_URL);
        but_Go.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                Firebase firebase = new Firebase(Config.FIREBASE_URL);
                String ans=firebase.child(friendName).getKey();
                toremove.setText(ans);

          /*      ///now we go to fire base
                try {


                    Intent k = new Intent(tsila.this, they_activity.class);/////////////////
                    startActivity(k);
                } catch (Exception e) {

                }
*/
            }


        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("tsila Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

