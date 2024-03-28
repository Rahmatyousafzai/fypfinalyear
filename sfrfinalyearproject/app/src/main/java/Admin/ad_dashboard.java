package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import adopter.userdetail;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ad_dashboard extends AppCompatActivity {

    private Apiservices apiServices;

    // Declare views
    TextView adpost, addteacher, adnews, admessage, addachivemet, adstudent, profilename;
    ImageView profileimage;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_dashboard);

        // Initialize views
        adpost = findViewById(R.id.adpost);
        addteacher = findViewById(R.id.textadteacher);
        adnews = findViewById(R.id.adnews);
        admessage = findViewById(R.id.adtxtmessage);
        addachivemet = findViewById(R.id.adtextachvment);
        adstudent = findViewById(R.id.adstudent);
        profilename = findViewById(R.id.profelname);
        recyclerView = findViewById(R.id.adrcview);
        profileimage = findViewById(R.id.fileimage);

        // Get username from intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        // Initialize Retrofit
        Retrofit retrofit = RetrofitClient.getClient();
        apiServices = retrofit.create(Apiservices.class);

        // Load profile information
        profileinformation(username);

        // Set click listeners
        admessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admessage();
            }
        });
        adnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news();
            }
        });
        addteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addteacher();
            }
        });
        adstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addstudent();
            }
        });
        addachivemet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click
            }
        });
        adpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click
            }
        });
    }

    public void admessage() {
        Intent intent = new Intent(ad_dashboard.this, Admin.admessage.class);
        startActivity(intent);
        finish();
    }

    public void news() {
        Intent intent = new Intent(ad_dashboard.this, ad_dashboard.class);
        startActivity(intent);
        finish();
    }

    public void addteacher() {
        Intent intent = new Intent(ad_dashboard.this, ad_teachers.class);

        intent.putExtra("prfilename", profilename.getText().toString());
        startActivity(intent);
        // Finish the current activity
        finish();


        startActivity(intent);
        finish();
    }

    public void addstudent() {
        Intent intent = new Intent(ad_dashboard.this, ad_student.class);
        startActivity(intent);
        finish();
    }

    public void profileinformation(String username) {
        // Make the API call
        Call<userdetail> call = apiServices.getUserDetails(username);
        call.enqueue(new Callback<userdetail>() {
            @Override
            public void onResponse(Call<userdetail> call, Response<userdetail> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    userdetail userDetails = response.body();
                    if (userDetails != null) {
                        // Set full name
                        String fullName = userDetails.getFname() + " " + userDetails.getLname();
                        profilename.setText(fullName);

                        // Set profile image
                        String imagePath = userDetails.getProfileimage();
                        if (imagePath != null && !imagePath.isEmpty()) {
                            Picasso.get().load(imagePath).into(profileimage);
                        }
                    }
                } else {
                    // API call failed
                    Toast.makeText(ad_dashboard.this, "Failed to get user details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<userdetail> call, Throwable t) {
                // Error handling for network failures or other exceptions
                Toast.makeText(ad_dashboard.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });











    }









}
