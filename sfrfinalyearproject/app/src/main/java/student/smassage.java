package student;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ModeClasees.Wish;
import ModeClasees.cuTeacher;
import ModeClasees.user;
import adopter.MessagListAdopter;
import adopter.OnTeacherClickListener;
import facultyClasses.mWishlist;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import studentClasses.GroupsData;
import studentClasses.UserDataSingleton;
import studentClasses.UserRepository;
import studentClasses.studentData;

public class smassage extends AppCompatActivity implements OnTeacherClickListener {

    // Instance of Apiservices for API calls
    private Apiservices apiServices = RetrofitClient.getInstance();
    private RecyclerView recyclerView; // RecyclerView for displaying messages

    private TextView profilename; // TextView for displaying profile name
    private ImageView profile, smipleMessage, groupmessage, createGroup; // ImageViews for profile, simple message, group message, and creating a group

    private String username, firstName, lastName, FullName, profileImage; // User information

    private MessagListAdopter adapter; // Adapter for RecyclerView
    private List<Wish> MessageList = new ArrayList<>(); // List for simple messages
    private List<GroupsData> GroupMessageList = new ArrayList<>(); // List for group messages

    private Handler handler; // Handler for background API calls
    private Runnable apiRunnable; // Runnable for background API calls

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smassage);

        // Initialize views
        initializeViews();

        // Initialize handler for background API calls
        handler = new Handler(Looper.getMainLooper());

        // In any other activity where you want to access the username
        username = UserDataSingleton.getInstance().getUsername();

        // Fetch user data from repository
        UserRepository userRepository = new UserRepository();
        userRepository.fetchUserData(username, new UserRepository.UserRepositoryCallback() {
            @Override
            public void onSuccess(studentData data) {
                // Access user data fields
                String programName = data.getProgramName();
                int semesterName = data.getSemesterName();
                String sectionName = data.getSectionName();

                profileImage = data.getProfileImage();
                firstName = data.getFirstName();
                lastName = data.getLastName();

                // Log user data
                Log.e("UserData.......", "Program Name...........: " + programName);
                Log.e("UserData......", "Semester Name..........: " + semesterName);
                Log.e("UserData.........", "Section Name:........... " + sectionName);

                // Optionally, update UI with user data
                String displayData = "(" + programName + " " + semesterName + "" + sectionName + ")";
                profilename.setText(displayData);
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

        // Set up RecyclerView
        recyclerView = findViewById(R.id.smessage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessagListAdopter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        // Load simple messages initially
      //  loadMessages();

        createGroup.setVisibility(View.VISIBLE);

        // Set click listeners for buttons
        smipleMessage.setOnClickListener(v -> {
           // loadMessages();
            createGroup.setVisibility(View.GONE);
            groupmessage.setVisibility(View.VISIBLE);
            highlightButton(smipleMessage);
            unhighlightButton(groupmessage);
        });

        groupmessage.setOnClickListener(v -> {
          //  loadGroupMessages();
            createGroup.setVisibility(View.VISIBLE);
            smipleMessage.setVisibility(View.VISIBLE);
            highlightButton(groupmessage);
            unhighlightButton(smipleMessage);
        });

        createGroup.setOnClickListener(v -> {
            Intent intent = new Intent(smassage.this, CreateGroup.class);
            startActivity(intent);
            finish();
        });

        // Set up background API call every 5 seconds
        apiRunnable = new Runnable() {
            @Override
            public void run() {
               // loadMessages(); // Load messages in the background
                handler.postDelayed(this, 5000); // Repeat every 5 seconds
            }
        };
        handler.postDelayed(apiRunnable, 5000); // Start the background task
    }

    private void initializeViews() {
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);
        createGroup = findViewById(R.id.creategroup);
        smipleMessage = findViewById(R.id.simlpemessage);
        groupmessage = findViewById(R.id.group);
    }

    private void highlightButton(View button) {
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.highlighted_color));


    }

    private void unhighlightButton(View button) {
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.unhighlighted_color));
    }

    @Override
    public void onTeacherClick(cuTeacher teacher) {
        // Implement as needed
    }

    @Override
    public void onTeacherClick(user teacher) {
        // Implement as needed
    }

    @Override
    public void onTeacherClick(Wish wish) {
        Intent intent = new Intent(this, smessagebody.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", FullName);
        intent.putExtra("profileimage", profileImage);
        intent.putExtra("teacher_username", wish.getUsername());
        intent.putExtra("teacher_firstName", wish.getFirstName());
        intent.putExtra("teacher_lastName", wish.getLastName());
        intent.putExtra("teacher_profileImage", wish.getProfileImage() != null ? wish.getProfileImage() : ""); // Ensure no null
        startActivity(intent);
    }

    @Override
    public void onTeacherClick(mWishlist wish) {

    }

    @Override
    public void onTeacherClick(Object item) {
        // Implement as needed
    }

   /* private void loadMessages() {
        apiServices.GetRelatedWishes(username).enqueue(new Callback<List<Wish>>() {
            @Override
            public void onResponse(Call<List<Wish>> call, Response<List<Wish>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Wish> messageList = response.body();
                    for (Wish message : messageList) {
                        if (message.getProfileImage() == null || message.getProfileImage().isEmpty()) {
                            message.setProfileImage(String.valueOf(R.drawable.baseline_account_circle_24));
                        }
                    }
                    adapter.setTeacherList(messageList);
                    Log.d("loadMessages", "Message list loaded successfully");
                } else {
                    Log.e("loadMessages", "Failed to load Message list. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Wish>> call, Throwable t) {
                Log.e("loadMessages", "Error loading messages: " + t.getMessage());
                // Handle the error
            }
        });
    }

    /*private void loadGroupMessages() {
        apiServices.getGroupinboxMessage(username).enqueue(new Callback<List<GroupsData>>() {
            @Override
            public void onResponse(Call<List<GroupsData>> call, Response<List<GroupsData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GroupsData> groupsDataList = response.body();
                    for (GroupsData grmessage : groupsDataList) {
                        if (grmessage.getGroupicon() == null || grmessage.getGroupicon().isEmpty()) {
                            grmessage.setGroupicon(String.valueOf(R.drawable.baseline_account_circle_24));
                        }
                    }
                    adapter.setTeacherList(groupsDataList); // Set the group messages list here
                    Log.d("loadGroupMessages", "Group message list loaded successfully");
                } else {
                    Log.e("loadGroupMessages", "Failed to load group message list. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<GroupsData>> call, Throwable t) {
                Log.e("loadGroupMessages", "Error loading group messages: " + t.getMessage());
                // Handle the error
            }
        });
    }*/

    @Override
    public void onBackPressed() {
        // Navigate back to the login screen
        Intent intent = new Intent(this, stdashboard.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }


}
