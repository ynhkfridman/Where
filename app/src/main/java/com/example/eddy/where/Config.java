package com.example.eddy.where;

import com.firebase.client.Firebase;


import com.google.firebase.auth.FirebaseUser;



public class Config {

    public static final String FIREBASE_URL = "https://ssex2-b7558.firebaseio.com/";
    private static FirebaseUser user;

    public static FirebaseUser getUser(){

         return user;
    }
    public static String getURL(){

        return FIREBASE_URL;
    }

    public static void setUser(FirebaseUser fbUser){
        user = fbUser;
    }


}
