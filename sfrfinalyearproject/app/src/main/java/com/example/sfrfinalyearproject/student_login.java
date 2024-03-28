package com.example.sfrfinalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R.id;

import java.io.IOException;

import Admin.ad_dashboard;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import student.stdashboard;

public class student_login extends AppCompatActivity {
    private Apiservices apiServices;
    private TextView adusername;
    private TextView adpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        // Find the login button by its ID
        TextView stfaculty,stadmin;
        Button studentlogin = findViewById(R.id.btnslogin);
        stfaculty=findViewById(id.faculty);
        stadmin=findViewById(R.id.stadmin);

        // Set a click listener for the login button
        studentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();

            }
        });
        stfaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faculty();

            }
        });
        stadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the student_login activity
              Admin();
            }
        });


    }

    public void faculty(){


        Intent intent = new Intent(student_login.this, faculty_login.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack
        // when the student_login activity starts
        finish();
    }
    public void login(){

        // Create an Intent to start the student_login activity
        Intent intent = new Intent(student_login.this, stdashboard.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack
        // when the student_login activity starts
        finish();


    }

    public void Admin(){


        Intent intent = new Intent(student_login.this, admin_login.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack
        // when the student_login activity starts
        finish();
    }




    private void initializeRetrofit() {
        // Create Retrofit instance
        Retrofit retrofit = RetrofitClient.getClient();

        // Create API service instance
        apiServices = retrofit.create(Apiservices.class);
    }

    private void performLogin() {
        // Get username and password from input fields
        String username = adusername.getText().toString();
        String password = adpassword.getText().toString();
        String usertype = "Student";

        // Validate input
        if (username.isEmpty() || password.isEmpty() || usertype.isEmpty()) {
            // Show error message for empty fields
            Toast.makeText(student_login.this, "Username and password are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Make API call
        Call<String> call = apiServices.login(username, password, usertype);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    // Handle successful login
                    handleSuccessfulLogin();
                } else {
                    // Handle unsuccessful login
                    handleUnsuccessfulLogin(response.code(), response.message());

                    // Log the response body for debugging
                    try {
                        Log.e("Login Error", "Error Body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Handle failure
                handleLoginFailure(t.getMessage());
            }
        });
    }

    private void handleSuccessfulLogin() {
        // Start admin dashboard activity
        Intent intent = new Intent(student_login.this, ad_dashboard.class);
        intent.putExtra("username", adusername.getText().toString());
        startActivity(intent);
        // Finish the current activity
        finish();
    }

    private void handleUnsuccessfulLogin(int errorCode, String errorMessage) {
        // Display appropriate error message based on response status code
        if (errorCode == 401) {
            // Unauthorized: Incorrect username or password
            Toast.makeText(student_login.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
        } else {
            // Other errors
            Toast.makeText(student_login.this, "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private void handleLoginFailure(String errorMessage) {
        // Display error message
        Toast.makeText(student_login.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
    }



}