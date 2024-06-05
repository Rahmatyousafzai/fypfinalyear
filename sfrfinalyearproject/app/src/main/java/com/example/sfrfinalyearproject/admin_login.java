package com.example.sfrfinalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import Admin.ad_dashboard;
import Alumni.al_dahsboard;
import Faculty.faculty_dashboard;
import ModeClasees.user;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.stdashboard;

public class admin_login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private boolean passwordVisible = false;
    private ImageView hidePasswordImage;

    // API service instance
    private Apiservices apiServices = RetrofitClient.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        // Initialize views
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.btnadlogin);
        hidePasswordImage = findViewById(R.id.hidepassword);

        // Set click listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = username.getText().toString().trim();
                String inputPassword = password.getText().toString().trim();
                Log.d("LoginActivity", "Attempting login with username: " + inputUsername);
                performLogin(inputUsername, inputPassword);
            }
        });

        // Initialize password toggle functionality
        hidePasswordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });
    }

    // Method to handle login
    private void performLogin(String username, String password) {
        try {
            Call<String> call = apiServices.login(username, password);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try {
                        if (response.isSuccessful()) {
                            String jsonResponse = response.body();
                            Log.d("LoginActivity", "Login response: " + jsonResponse);
                            Gson gson = new Gson();
                            user user = gson.fromJson(jsonResponse, user.class);
                            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                                Log.d("LoginActivity", "Login successful for user: " + username);
                                navigateToNextActivity(user);
                            } else {
                                Toast.makeText(admin_login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                                Log.e("LoginActivity", "Invalid username or password");
                            }
                        } else {
                            Log.e("LoginActivity", "Login failed: " + response.message());
                            Toast.makeText(admin_login.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.e("LoginActivity", "Error parsing login response", e);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(admin_login.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("LoginActivity", "Network error: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e("LoginActivity", "Error initiating login call", e);
        }
    }

    // Method to navigate to the next activity based on user type
    private void navigateToNextActivity(user user) {
        String userType = user.getUserType();
        Intent intent;
        if ("Student".equals(userType)) {
            intent = new Intent(admin_login.this, stdashboard.class);
        } else if ("Teacher".equals(userType)) {
            intent = new Intent(admin_login.this, faculty_dashboard.class);
        } else if ("Admin".equals(userType)) {
            intent = new Intent(admin_login.this, ad_dashboard.class);
        } else if ("Alumni".equals(userType)) {
            intent = new Intent(admin_login.this, al_dahsboard.class);
        } else {




            Toast.makeText(this, "Unknown user type", Toast.LENGTH_SHORT).show();
            Log.e("LoginActivity", "Unknown user type: " + userType);
            return;
        }

        // Add user details to the intent
        intent.putExtra("username", user.getUsername());
        intent.putExtra("firstname", user.getFirstName());
        intent.putExtra("lastname", user.getLastName());
        intent.putExtra("profileimage", user.getProfileImage());

        Log.d("LoginActivity", "Navigating to next activity with user: " + user.getUsername());
        startActivity(intent);
    }

    // Method to toggle password visibility
    private void togglePasswordVisibility() {
        if (passwordVisible) {
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            hidePasswordImage.setImageResource(R.drawable.hidepassword);
        } else {
            password.setTransformationMethod(null);
            hidePasswordImage.setImageResource(R.drawable.unhidepassword);
        }
        passwordVisible = !passwordVisible;
    }
}
