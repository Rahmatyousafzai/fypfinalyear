package com.example.sfrfinalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the login button by its ID
        Button buttonNext = findViewById(R.id.login);

        // Set a click listener for the login button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the student_login activity
                Intent intent = new Intent(MainActivity.this, student_login.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack
                // when the student_login activity starts
                finish();

            }
        });





    }   }