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
import mydataapi.Reaction;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class faculty_dashboard extends AppCompatActivity implements wishingadopter.EmojiClickListener {

    private static final String TAG = "FacultyDashboard";

    private Apiservices apiServices;
    private TextView news, notification, message, student, favstudent, typepost, profilename;
    private ImageView imgnews, imgNotification, imgmessage, imgstudent, imgfavstudent, imgpost, profile;
    private RecyclerView recyclerView;
    private wishingadopter adapter;
    private List<wishingclass> wishList = new ArrayList<>();
    private String username, firstName, lastName, profileImage;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isLoading = false;
    private int currentSwId;
    private int currentEmojiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_dashboard);

        // Initialize views
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

        // Retrieve username from UserDataSingleton (assuming it's set previously)
        username = UserDataSingleton.getInstance().getUsername();

        // Fetch user data using repository
        teacherRepository userRepository = new teacherRepository();
        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data) {
                // Access user data fields
                String sectionName = data.getDisgnatione();
                profileImage = data.getProfileImage();
                firstName = data.getFirstName();
                lastName = data.getLastName();

                Log.e("UserData.........", "Section Name:........... " + sectionName);

                // Update UI with user data
                profilename.setText(firstName + " " + lastName);
                TextView textView = findViewById(R.id.disgnation);
                textView.setText(sectionName);

                // Load profile image using Picasso
                if (profileImage != null && !profileImage.isEmpty()) {
                    String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
                    Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
                } else {
                    profile.setImageResource(R.drawable.baseline_account_circle_24);
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("SomeActivity", "Error fetching user data: " + e.getMessage());
                Toast.makeText(faculty_dashboard.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new wishingadopter(this, wishList, this); // Pass this as EmojiClickListener
        recyclerView.setAdapter(adapter);

        // Initialize SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData(); // Refresh data when swipe gesture is performed
            }
        });

        // Initial data fetch
        fetchData();

        // Set click listeners for various actions
        setClickListeners();
    }



    @Override
    public void onEmojiClick(int wishId, int emojiId) {
        this.currentSwId = wishId;
        this.currentEmojiId = emojiId;
        postReaction(username ,currentSwId, currentEmojiId);
        adapter.animateEmojiZoom(emojiId);
    }

    @Override
    public void onEmojiClick(int emojiId) {
        // This method is not needed for this scenario
    }
    private void postReaction(String reacterId,int wishid,int emojiid) {
        Apiservices apiService = RetrofitClient.getInstance();

        // Create the reaction object
        Reaction reaction = new Reaction();
        reaction.setReacterID(reacterId);
        reaction.setSw_id(wishid);
        reaction.setEmojiId(emojiid);
        reaction.setDatetime(""); // Ensure the server handles this correctly

        // Log the request payload
        Log.d("postReaction", "Posting reaction: " + reaction.toString());

        // Call the API
        Call<Void> call = apiService.postReaction(reaction);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("API Response", "Reaction processed successfully");
                    // Update the reaction count for the specific wish
                    updateReactionCount(wishid);

                } else {
                    Log.e("API Error", "Error response: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API Failure", "Failed to post reaction", t);
            }
        });
    }





    // Method to fetch data using Retrofit
    private void fetchData() {
        swipeRefreshLayout.setRefreshing(true); // Show refresh indicator

        // Retrofit call to fetch wishes
        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<wishingclass>> call = apiService.getDashboardMessages(username);

        call.enqueue(new Callback<List<wishingclass>>() {
            @Override
            public void onResponse(Call<List<wishingclass>> call, Response<List<wishingclass>> response) {
                swipeRefreshLayout.setRefreshing(false); // Hide refresh indicator

                if (response.isSuccessful()) {
                    List<wishingclass> wishes = response.body();
                    if (wishes != null) {
                        // Clear previous data if not loading more
                        if (!isLoading) {
                            wishList.clear();
                            isLoading = true;
                        }
                        wishList.addAll(wishes); // Add new data
                        if (adapter != null) {
                            adapter.notifyDataSetChanged(); // Notify adapter of data change
                        } else {
                            Log.e(TAG, "Adapter is null in onResponse");
                        }
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
                swipeRefreshLayout.setRefreshing(false); // Hide refresh indicator
                Log.e(TAG, "onFailure: Error fetching data", t);
                Toast.makeText(faculty_dashboard.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Emoji click listener method implementation


    // Other methods for handling various click actions
    private void setClickListeners() {
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

    // Methods to handle various actions
    private void TYPEPOST() {
        Intent intent = new Intent(faculty_dashboard.this, faculty_select_message_option.class);
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
        Intent intent = new Intent(faculty_dashboard.this,ftNotification.class);
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


    private void updateReactionCount(int wishId) {
        // Assuming you have a method to get the adapter and update data
        if (adapter != null) {
            adapter.updateReactionCount(wishId);
        }

    }
}
