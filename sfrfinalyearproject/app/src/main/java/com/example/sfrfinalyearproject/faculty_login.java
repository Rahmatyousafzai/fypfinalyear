package com.example.sfrfinalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Admin.ad_section;
import Faculty.faculty_dashboard;

public class faculty_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);

        Button btnlogin=findViewById(R.id.btnslogin);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(faculty_login.this, faculty_dashboard.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack

                // when the student_login activity starts
                finish();

            }
        });
    }
}