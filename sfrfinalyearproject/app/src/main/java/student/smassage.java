package student;

import android.content.Intent;
import android.os.Bundle;
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
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.GroupsData;
import studentClasses.UserDataSingleton;
import studentClasses.UserRepository;
import studentClasses.studentData;

public class smassage extends AppCompatActivity implements OnTeacherClickListener {
    private Apiservices apiServices = RetrofitClient.getInstance();
    private RecyclerView recyclerView;

    private TextView profilename;
    private ImageView profile, smipleMessage, groupmessage, createGroup;

    private String username, firstName, lastName, FullName, profileImage;

    private MessagListAdopter adapter;
    private List<Wish> MessageList = new ArrayList<>();
    private List<GroupsData> GroupMessageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smassage);

        createGroup = findViewById(R.id.creategroup);
        smipleMessage = findViewById(R.id.simlpemessage);
        groupmessage = findViewById(R.id.group);
        initializeViews();


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

        recyclerView = findViewById(R.id.smessage); // Make sure to use the correct ID for RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessagListAdopter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        // Load simple messages initially
        loadMessages();

        smipleMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMessages();
                createGroup.setVisibility(View.GONE);
                highlightButton(smipleMessage);
                unhighlightButton(groupmessage);
            }
        });

        groupmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadGroupMessages();
                createGroup.setVisibility(View.VISIBLE);
                highlightButton(groupmessage);
                unhighlightButton(smipleMessage);
            }
        });
    }

    private void initializeViews() {
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);
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
    public void onTeacherClick(Object item) {
        // Implement as needed
    }

    private void loadMessages() {
        apiServices.getinboxlist(username).enqueue(new Callback<List<Wish>>() {
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

    private void loadGroupMessages() {
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
    }

}
