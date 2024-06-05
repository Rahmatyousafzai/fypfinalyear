package com.example.sfrfinalyearproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R.id;

import mydataapi.Apiservices;

public class student_login extends AppCompatActivity {
    private Apiservices apiServices;
    private TextView adusername;
    private TextView adpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        // Find the login button by its ID
        TextView stfaculty, stadmin;
        Button studentlogin = findViewById(R.id.btnslogin);
        stfaculty = findViewById(id.faculty);
        stadmin = findViewById(R.id.stadmin);


    }

}