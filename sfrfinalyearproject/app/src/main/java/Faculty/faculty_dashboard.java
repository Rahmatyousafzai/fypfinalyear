package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dashboardclasese.wishingadopter;
import dashboardclasese.wishingclass;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class faculty_dashboard extends AppCompatActivity {


    private static final String TAG = "FacultyDashboard";

    private Apiservices apiServices;
    TextView news, notification, message, student, favstudent, typepost, profilename;
    ImageView imgnews, imgNotification, imgmessage, imgstudent, imgfavstudent, imgpost, profile;
    private RecyclerView recyclerView;

    // private wish adapter;
    private List<wishingclass> wishList = new ArrayList<>();
    private wishingadopter adapter;
    private String username;
    private String firstName;
    private String lastName;
    private String profileImage;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_dashboard);

        /////////////////////////////////////////////////////////////////
        //////////////////////////////  Find view.////////////////////////
        /////////////////////////////////////////////////////////////////
        news = findViewById(R.id.news);
        notification = findViewById(R.id.txtnotification);
        message = findViewById(R.id.txtmessage);
        favstudent = findViewById(R.id.txtfavstudent);
        student = findViewById(R.id.textstudent);
        imgnews = findViewById(R.id.imgnews);
        imgNotification = findViewById(R.id.imgnptification);
        imgmessage = findViewById(R.id.imgmessage);
        imgstudent = findViewById(R.id.imgstudent);
        imgfavstudent = findViewById(R.id.imgfavstudent);
        imgpost = findViewById(R.id.typepost);
        typepost = findViewById(R.id.addpost);

        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);


         username = UserDataSingleton.getInstance().getUsername();

        teacherRepository userRepository = new teacherRepository();

        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data) {
                // Access user data fields

                String sectionName = data.getDisgnation();

                profileImage = data.getProfileImage();
                firstName = data.getFirstName();
                lastName = data.getLastName();

                Log.e("UserData.........", "Section Name:........... " + sectionName);

                // Optionally, update UI with user data
                TextView textView = findViewById(R.id.disgnation);
                String displayData = (sectionName);
                textView.setText(displayData);
                if (profileImage != null && !profileImage.isEmpty()) {
                    String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
                    Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
                } else {
                    profile.setImageResource(R.drawable.baseline_account_circle_24);
                }


                String fullName = firstName + " " + lastName;
                profilename.setText(fullName);

            }

            @Override
            public void onFailure(Exception e) {
                Log.e("SomeActivity", "Error fetching user data: " + e.getMessage());
                // Handle error case, e.g., show a toast or an error message
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new wishingadopter(this, wishList);
        recyclerView.setAdapter(adapter);

        // Configure SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData(); // Call fetchData() when user swipes to refresh
            }
        });

        // Initial data load
        fetchData();

        // Fetch data from API

        //    adapter = new Wishadapter(this, wishList, this);
        //  recyclerView.setAdapter(adapter);

        Log.d("recycl", "recyclerView Data " + recyclerView.toString());
        //Log.d("recycl", "adopter Data " + adapter.toString());
        // fetchAndDisplayWishes();

        /////////////////////////////////////////////////////////////////////////
        ///////////////////////// Set click listeners////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        imgpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TYPEPOST();
            }
        });

        typepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TYPEPOST();
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news();
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification();
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message();
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student();
            }
        });

        favstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favstudent();
            }
        });

        imgnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news();
            }
        });

        imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification();
            }
        });

        imgmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message();
            }
        });

        imgstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student();
            }
        });

        imgfavstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favstudent();
            }
        });

    }

    //////////////////////////////////////////////////////
    /////////////////////////FUNCTIONS/////////////////////
    //////////////////////////////////////////////////////
    private void TYPEPOST() {
        Intent intent = new Intent(faculty_dashboard.this, faculty_select_message_option.class); //faculty_select_message_option
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void message() {
        Intent intent = new Intent(faculty_dashboard.this, facultmessage.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void student() {
        Intent intent = new Intent(faculty_dashboard.this, ftStudent.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void favstudent() {
        Intent intent = new Intent(faculty_dashboard.this, ftfavstudent.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void notification() {
        Intent intent = new Intent(faculty_dashboard.this, ftStudent.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    public void news() {
        Intent intent = new Intent(faculty_dashboard.this, faculty_dashboard.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    ///////////////////////////display wishes//////////

    private void fetchData() {
        // Show progress indicator
        swipeRefreshLayout.setRefreshing(true);
        String messagetype = "Teacher";

        // Implement your Retrofit call here to fetch data
        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<wishingclass>> call = apiService.getDashboardMessages(username, "BIIT0001", messagetype);

        Log.d(TAG, "fetchData: Making Retrofit call...");

        call.enqueue(new Callback<List<wishingclass>>() {
            @Override
            public void onResponse(Call<List<wishingclass>> call, Response<List<wishingclass>> response) {
                // Hide progress indicator
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful()) {
                    List<wishingclass> wishes = response.body();
                    if (wishes != null) {
                        // If loading for the first time or refreshing
                        if (!isLoading) {
                            wishList.clear(); // Clear previous data
                            isLoading = true; // Set loading state
                        }

                        wishList.addAll(wishes); // Add new data to list
                        adapter.notifyDataSetChanged(); // Notify adapter

                        isLoading = false; // Reset loading state

                        Log.d(TAG, "onResponse: Data loaded successfully. Size: " + wishes.size());
                    } else {
                        Log.d(TAG, "onResponse: Response body is null");
                    }
                } else {
                    Log.d(TAG, "onResponse: Response not successful. Code: " + response.code());
                    Toast.makeText(faculty_dashboard.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<wishingclass>> call, Throwable t) {
                // Hide progress indicator
                swipeRefreshLayout.setRefreshing(false);

                // Handle failure
                Log.e(TAG, "onFailure: Error fetching data", t);
                Toast.makeText(faculty_dashboard.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }












}

