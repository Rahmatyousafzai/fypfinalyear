
package com.example.sfrfinalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Admin.ad_dashboard;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class  admin_login extends AppCompatActivity {
    private Apiservices apiServices;
    private TextView adusername;
    private TextView adpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        // Initialize views
        adusername = findViewById(R.id.adusername);
        adpassword = findViewById(R.id.adpassword);
        Button loginButton = findViewById(R.id.btnadlogin);
        TextView txtfeculty = findViewById(R.id.txtAdfaculty);
        TextView txtstudent = findViewById(R.id.adstudent);

        // Set click listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  performLogin();
                login();
            }
        });

        txtfeculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFacultyLogin();
            }
        });

        txtstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToStudentLogin();
            }
        });

        // Initialize Retrofit
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        // Create Retrofit instance
        Retrofit retrofit = RetrofitClient.getClient();

        // Create API service instance
        apiServices = retrofit.create(Apiservices.class);
    }
private  void login(){
    Intent intent = new Intent(admin_login.this, ad_dashboard.class);
    startActivity(intent);
    // Finish the current activity
    finish();
}
    private void performLogin() {
        // Get username and password from input fields
        String username = adusername.getText().toString().trim();
        String password = adpassword.getText().toString().trim();
        String usertype = "Admin";

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            // Show error message for empty fields
            Toast.makeText(admin_login.this, "Username and password are required", Toast.LENGTH_SHORT).show();
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

                    // Log the error message
                    Log.e("Login Error", "Error Body: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Handle failure
                handleLoginFailure(t.getMessage());

                // Log the exception
                Log.e("Login Error", "Exception: " + t.getMessage());
            }
        });
    }

    private void handleSuccessfulLogin() {
        // Start admin dashboard activity
        Intent intent = new Intent(admin_login.this, ad_dashboard.class);
        intent.putExtra("username", adusername.getText().toString());
        startActivity(intent);
        // Finish the current activity
        finish();
    }

    private void handleUnsuccessfulLogin(int errorCode, String errorMessage) {
        // Display appropriate error message based on response status code
        if (errorCode == 401) {
            // Unauthorized: Incorrect username or password
            Toast.makeText(admin_login.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
        } else {
            // Other errors
            Toast.makeText(admin_login.this, "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private void handleLoginFailure(String errorMessage) {
        // Display error message
        Toast.makeText(admin_login.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void navigateToFacultyLogin() {
        // Start faculty login activity
        Intent intent = new Intent(admin_login.this, faculty_login.class);
        startActivity(intent);
        // Finish the current activity
        finish();
    }

    private void navigateToStudentLogin() {
        // Start student login activity
        Intent intent = new Intent(admin_login.this, student_login.class);
        startActivity(intent);
        // Finish the current activity
        finish();
    }
}
