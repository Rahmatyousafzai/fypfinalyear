package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class ad_dashboard extends AppCompatActivity implements wishingadopter.EmojiClickListener {

    private TextView adminPost, addTeacher, adNews, adMessage, addAchievement, adStudent, profileName, notification;
    private wishingadopter adapter;
    private List<wishingclass> wishList = new ArrayList<>();
    private String username, firstName, lastName, profileImage;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isLoading = false;
    private ImageView profile, popup;
    private RecyclerView recyclerView;
    private static final String TAG = "ad_dashboard";
    private Apiservices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_dashboard);

        // Initialize views and image list
        initViews();

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new wishingadopter(this, wishList, this); // Pass this as EmojiClickListener
            recyclerView.setAdapter(adapter);
        } else {
            Log.e(TAG, "RecyclerView is null. Check the layout file for the RecyclerView with id 'recyclerView'");
        }

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

                // Update UI with user data
                profileName.setText(firstName + " " + lastName);
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
                Toast.makeText(ad_dashboard.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
            }
        });

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

    private void initViews() {
        // Initialize views by finding them through their IDs
        adminPost = findViewById(R.id.adminpost);
        notification = findViewById(R.id.adtxtnotification);
        addTeacher = findViewById(R.id.textadteacher);
        adNews = findViewById(R.id.adnews);
        adMessage = findViewById(R.id.adtxtmessage);
        addAchievement = findViewById(R.id.adtextachvment);
        adStudent = findViewById(R.id.adstudent);
        profileName = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);
        popup = findViewById(R.id.popup);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String firstName = intent.getStringExtra("firstname");
        String lastName = intent.getStringExtra("lastname");
        String profileImage = intent.getStringExtra("profileimage");

        String fullName = firstName + " " + lastName;
        profileName.setText(fullName);

        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }

        apiServices = RetrofitClient.getInstance();
    }

    private void setClickListeners() {
        // Set click listeners for each view to trigger the corresponding methods
        adminPost.setOnClickListener(v -> adminpost());
        notification.setOnClickListener(v -> notification());
        adMessage.setOnClickListener(v -> adMessage());
        adNews.setOnClickListener(v -> news());
        addTeacher.setOnClickListener(v -> addTeacher());
        adStudent.setOnClickListener(v -> addStudent());
        addAchievement.setOnClickListener(v -> viewAchievement());
        popup.setOnClickListener(v -> {
            // Handle popup click
        });
    }

    private void fetchData() {
        swipeRefreshLayout.setRefreshing(true); // Show refresh indicator

        // Retrofit call to fetch wishes
        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<wishingclass>> call = apiService.getDashboardMessages(username, null, "Admin");

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
                    Toast.makeText(ad_dashboard.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<wishingclass>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false); // Hide refresh indicator
                Log.e(TAG, "onFailure: Error fetching data", t);
                Toast.makeText(ad_dashboard.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Methods for handling clicks on different views
    private void adminpost() {
        // Start the activity for admin post sharing options
        Intent intent = new Intent(ad_dashboard.this, Admin.postshareoption.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    private void viewAchievement() {
        // Start the activity for viewing achievements
        Intent intent = new Intent(ad_dashboard.this, ad_addachivment.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    public void notification() {
        // Start the activity for notifications
        Intent intent = new Intent(ad_dashboard.this, ad_notification.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    public void adMessage() {
        // Start the activity for admin messages
        Intent intent = new Intent(ad_dashboard.this, Admin.admessage.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    public void news() {
        // Start the activity for news
        Intent intent = new Intent(ad_dashboard.this, ad_dashboard.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    public void addTeacher() {
        // Start the activity for adding teachers
        Intent intent = new Intent(ad_dashboard.this, Admin.ad_teachers.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    public void addStudent() {
        // Start the activity for adding students
        Intent intent = new Intent(ad_dashboard.this, Admin.ad_student.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    // Emoji click listener method implementation
    @Override
    public void onEmojiClick(int wishId, int emojiId) {
        Log.d(TAG, "Emoji Click: Wish ID: " + wishId + ", Emoji ID: " + emojiId);
        // Handle emoji click event here
    }

    @Override
    public void onEmojiClick(int emojiId) {
        // Handle emoji click event here
    }
}
