package com.example.easy_atten;

import android.app.Application;

import com.backendless.Backendless;

import java.util.List;

public class Default extends Application {

    public static final String APPLICATION_ID = "5FE34ED1-39FF-4D8B-A6A8-D99CADD8900F";
    public static final String API_KEY = "B41485C2-B692-4522-9502-7830A354F793";
    public static final String SERVER_URL = "http://api.backendless.com";
    public static List<STUDENT> students;


    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );
    }
}
