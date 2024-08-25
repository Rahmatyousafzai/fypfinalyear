package student;

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
import studentClasses.UserDataSingleton;
import studentClasses.UserRepository;
import studentClasses.studentData;

public class stdashboard extends AppCompatActivity implements wishingadopter.EmojiClickListener {

    private static final String TAG = "StudentDashboard";

    private TextView txtFavTeacher, profilename, textteacher, sectionandsamester;
    private ImageView profile, ImgMessage, ImgNOtification;
    private RecyclerView recyclerView;
    private wishingadopter adapter;
    private List<wishingclass> wishList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isLoading = false;
    private String username, firstName, lastName, profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdashboard);

        initializeViews();

        username = UserDataSingleton.getInstance().getUsername();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new wishingadopter(this, wishList, this);
        recyclerView.setAdapter(adapter);

        fetchData();

        swipeRefreshLayout.setOnRefreshListener(this::fetchData);

        setupClickListeners();

        fetchUserData();
    }

    private void initializeViews() {
        ImgMessage = findViewById(R.id.imgmessage);
        ImgNOtification = findViewById(R.id.imgnptification);
        txtFavTeacher = findViewById(R.id.favtecher);
        textteacher = findViewById(R.id.textteacher);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);
        sectionandsamester = findViewById(R.id.sectionandsamester);
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
    }

    private void setupClickListeners() {
        textteacher.setOnClickListener(v -> openActivity(stteacher.class));
        ImgNOtification.setOnClickListener(v -> openActivity(snotification.class));
        txtFavTeacher.setOnClickListener(v -> openActivity(favteacher.class));
        ImgMessage.setOnClickListener(v -> openActivity(smassage.class));
    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(stdashboard.this, activityClass);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void fetchUserData() {
        UserRepository userRepository = new UserRepository();
        userRepository.fetchUserData(username, new UserRepository.UserRepositoryCallback() {
            @Override
            public void onSuccess(studentData data) {
                String programName = data.getProgramName();
                int semesterName = data.getSemesterName();
                String sectionName = data.getSectionName();
                profileImage = data.getProfileImage();
                firstName = data.getFirstName();
                lastName = data.getLastName();

                Log.e("UserData", "Program Name: " + programName);
                Log.e("UserData", "Semester Name: " + semesterName);
                Log.e("UserData", "Section Name: " + sectionName);

                String displayData = "(" + programName + " " + semesterName + " " + sectionName + ")";
                sectionandsamester.setText(displayData);

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
                Log.e(TAG, "Error fetching user data: " + e.getMessage());
                Toast.makeText(stdashboard.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchData() {
        swipeRefreshLayout.setRefreshing(true);

        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<wishingclass>> call = apiService.getDashboardMessages(username);

        call.enqueue(new Callback<List<wishingclass>>() {
            @Override
            public void onResponse(Call<List<wishingclass>> call, Response<List<wishingclass>> response) {
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful()) {
                    List<wishingclass> wishes = response.body();
                    if (wishes != null) {
                        if (!isLoading) {
                            wishList.clear();
                        }
                        wishList.addAll(wishes);
                        adapter.notifyDataSetChanged();
                        isLoading = false;
                        Log.d(TAG, "Data loaded successfully. Size: " + wishes.size());
                    } else {
                        Log.d(TAG, "Response body is null");
                    }
                } else {
                    Log.d(TAG, "Response not successful. Code: " + response.code());
                    Toast.makeText(stdashboard.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<wishingclass>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.e(TAG, "Error fetching data", t);
                Toast.makeText(stdashboard.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEmojiClick(int wishId, int emojiId) {
        Log.d(TAG, "Emoji Click: Wish ID: " + wishId + ", Emoji ID: " + emojiId);
        // Handle emoji click event here
    }

    @Override
    public void onEmojiClick(int emojiId) {

    }


}
