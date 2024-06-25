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

public class stdashboard extends AppCompatActivity implements wishingadopter.EmojiClickListener{
    //implements OnTeacherClickListener {

    private static final String TAG = "StudentDashboard";

    // Views
    private TextView txtNews, sectionandsamester, txtMessage, txtNotification, txtFavTeacher, profilename, textteacher;
    private ImageView profile;








    private RecyclerView recyclerView;
    private wishingadopter adapter;
    private List<wishingclass> wishList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isLoading = false;
    // Data

    private String username, firstName, lastName, profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdashboard);


        initializeViews();

        Intent intent = getIntent();

       // setupRecyclerView();

        setupClickListeners();


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
        //fetchAndDisplayWishes();


// In any other activity where you want to access the username
        username = UserDataSingleton.getInstance().getUsername();

        UserRepository userRepository = new UserRepository();
        userRepository.fetchUserData(username, new UserRepository.UserRepositoryCallback() {
            @Override
            public void onSuccess(studentData data) {
                // Access user data fields
                String programName = data.getProgramName();
                int semesterName = data.getSemesterName();
                String sectionName = data.getSectionName();
                String Profilename = data.getProgramName();
                profileImage = data.getProfileImage();
                firstName = data.getFirstName();
                lastName = data.getLastName();

                // Log user data
                Log.e("UserData.......", "Program Name...........: " + programName);
                Log.e("UserData......", "Semester Name..........: " + semesterName);
                Log.e("UserData.........", "Section Name:........... " + sectionName);

                // Optionally, update UI with user data
                TextView textView = findViewById(R.id.sectionandsamester);
                String displayData = "(" + programName + " " + semesterName + "" + sectionName + ")";
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


    }

    private void initializeViews() {
        txtNews = findViewById(R.id.news);
        txtMessage = findViewById(R.id.txtmessage);
        txtNotification = findViewById(R.id.txtnotification);
        txtFavTeacher = findViewById(R.id.favtecher);
        textteacher = findViewById(R.id.textteacher);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);

    }

   /* private void extractUserInfo(Intent intent) {
        username = intent.getStringExtra("username");
        firstName = intent.getStringExtra("firstname");
        lastName = intent.getStringExtra("lastname");
        profileImage = intent.getStringExtra("profileimage");

        String fullName = firstName + " " + lastName;
        profilename.setText(fullName);

        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }
    }*/

    private void setupClickListeners() {
        textteacher.setOnClickListener(v -> openTeacherActivity());
        txtNotification.setOnClickListener(v -> openNotificationActivity());
        txtFavTeacher.setOnClickListener(v -> openFavTeacherActivity());
        txtMessage.setOnClickListener(v -> openMessageActivity());
    }

    private void openTeacherActivity() {
        Intent teacherIntent = new Intent(stdashboard.this, stteacher.class);
        teacherIntent.putExtra("username", username);
        teacherIntent.putExtra("FullName", firstName + " " + lastName);
        teacherIntent.putExtra("profileimage", profileImage);
        startActivity(teacherIntent);
        finish();
    }

    private void openNotificationActivity() {
        Intent notificationIntent = new Intent(stdashboard.this, snotification.class);
        notificationIntent.putExtra("username", username);
        notificationIntent.putExtra("FullName", firstName + " " + lastName);
        notificationIntent.putExtra("profileimage", profileImage);
        startActivity(notificationIntent);
        finish();
    }

    private void openFavTeacherActivity() {
        Intent favTeacherIntent = new Intent(stdashboard.this, favteacher.class);
        favTeacherIntent.putExtra("username", username);
        favTeacherIntent.putExtra("FullName", firstName + " " + lastName);
        favTeacherIntent.putExtra("profileimage", profileImage);
        startActivity(favTeacherIntent);
        finish();
    }

    private void openMessageActivity() {
        Intent messageIntent = new Intent(stdashboard.this, smassage.class);
        messageIntent.putExtra("username", username);
        messageIntent.putExtra("FullName", firstName + " " + lastName);
        messageIntent.putExtra("profileimage", profileImage);
        startActivity(messageIntent);
        finish();
    }

  /*  private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter(messages);
        recyclerView.setAdapter(messageAdapter);
    }

    private void fetchAndDisplayWishes() {
        apiServices.getMessages().enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    messages.clear();
                    messages.addAll(response.body());
                    messageAdapter.notifyDataSetChanged();
                    Log.d(TAG, "Wishes fetched successfully. List size: " + messages.size());
                } else {
                    Log.d(TAG, "Failed to get wishes. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.d(TAG, "Error fetching wishes: " + t.getMessage());
                Toast.makeText(stdashboard.this, "Error fetching wishes", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onTeacherClick(user teacher) {

    }

    @Override
    public void onTeacherClick(cuTeacher teacher) {

    }

    @Override
    public void onTeacherClick(Wish wish) {
        showToast("Clicked on: " + wish.getSwid());
    }

    @Override
    public void onTeacherClick(Object item) {

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

   */


    private void fetchData() {
        swipeRefreshLayout.setRefreshing(true); // Show refresh indicator

        // Retrofit call to fetch wishes
        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<wishingclass>> call = apiService.getDashboardMessages(username, "null", "Student");

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
                    Toast.makeText(stdashboard.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<wishingclass>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false); // Hide refresh indicator
                Log.e(TAG, "onFailure: Error fetching data", t);
                Toast.makeText(stdashboard.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Emoji click listener method implementation
    @Override
    public void onEmojiClick(int wishId, int emojiId) {
        Log.d(TAG, "Emoji Click: Wish ID: " + wishId + ", Emoji ID: " + emojiId);
        // Handle emoji click event here
    }

    @Override
    public void onEmojiClick(int emojiId) {

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clear user data when exiting
        UserDataSingleton.getInstance().clearData();
    }


}