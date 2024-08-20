package Faculty;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
import facultyClasses.appPermission;
import facultyClasses.forwordsetting;
import facultyClasses.mWishlist;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class facultmessage extends AppCompatActivity implements OnTeacherClickListener {

    private static final String TAG = "facultmessage";

    private Apiservices apiServices = RetrofitClient.getInstance();
    private RecyclerView recyclerView;
    private TextView profilename;
    private TextView designationTextView;
    private ImageView profile;
    private MessagListAdopter adapter;

    private String username, firstName, lastName, profileImage, ForwarduserName;

    private String forwardName = "";  // To store forward name
    private boolean isAutoReplyOn = false;  // Store the state of Auto Reply
    private boolean isMessageForwardOn = false;
    private int deletepermissoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultmessage);

        initViews();
        setupRecyclerView();
        loadTeacherData();
        getPermissions();
        getForwardSetting();
    }

    private void initViews() {
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);
        recyclerView = findViewById(R.id.wishesh);
        ImageView appSetting = findViewById(R.id.setting);

        appSetting.setOnClickListener(v -> showAutoReplyDialog());
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessagListAdopter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "RecyclerView set up");
    }

    private void loadTeacherData() {
        username = UserDataSingleton.getInstance().getUsername();
        Log.d(TAG, "Username retrieved: " + username);

        teacherRepository userRepository = new teacherRepository();
        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data) {
                profileImage = data.getProfileImage();
                firstName = data.getFirstName();
                lastName = data.getLastName();
                String sectionName = data.getDisgnatione();

                profilename.setText(firstName + " " + lastName);
                TextView textView = findViewById(R.id.disgnation);
                textView.setText(sectionName);

                if (profileImage != null && !profileImage.isEmpty()) {
                    String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
                    Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
                } else {
                    profile.setImageResource(R.drawable.baseline_account_circle_24);
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "Error fetching user data: " + e.getMessage());
                Toast.makeText(facultmessage.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
            }
        });

        loadTeachers();
    }

    private void loadTeachers() {
        Log.d(TAG, "Loading teacher wishes for username: " + username);
        apiServices.GetRelatedWishes(username).enqueue(new Callback<List<mWishlist>>() {
            @Override
            public void onResponse(Call<List<mWishlist>> call, Response<List<mWishlist>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<mWishlist> messages = response.body();
                    Log.d(TAG, "Teacher wishes retrieved: " + messages.size() + " messages");

                    if (messages.isEmpty()) {
                        Log.d(TAG, "No messages found");
                    }

                    adapter.setTeacherList(messages);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "Failed to load message list. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<mWishlist>> call, Throwable t) {
                Log.e(TAG, "Error loading teachers: " + t.getMessage());
            }
        });
    }

    @Override
    public void onTeacherClick(user teacher) {
        // Implement as needed
    }

    @Override
    public void onTeacherClick(cuTeacher teacher) {
        // Implement as needed
    }

    @Override
    public void onTeacherClick(Wish wish) {
        // Implement as needed
    }

    @Override
    public void onTeacherClick(mWishlist wish) {
        Intent intent = new Intent(this, facultymessagebody.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        intent.putExtra("teacher_username", wish.getUsername());
        intent.putExtra("teacher_firstName", wish.getFirstName());
        intent.putExtra("teacher_lastName", wish.getLastName());
        intent.putExtra("teacher_profileImage", wish.getProfileImage() != null ? wish.getProfileImage() : "");
        startActivity(intent);
        Log.d(TAG, "Clicked on wish from user: " + wish.getUsername());
    }

    @Override
    public void onTeacherClick(Object item) {
        // Implement as needed
    }

    private void getPermissions() {
        apiServices.getPermissions(username).enqueue(new Callback<List<appPermission>>() {
            @Override
            public void onResponse(Call<List<appPermission>> call, Response<List<appPermission>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<appPermission> permissions = response.body();
                    boolean autoReplyEnabled = false;

                    for (appPermission permission : permissions) {
                        Log.d(TAG, "Permission: " + permission.getUsername() + " (ID: " + permission.getAtid() + ")");
                        deletepermissoin=permission.getAtid();


                        if (permission.getUsername().equals(username)) {
                            autoReplyEnabled = true;
                            break;
                        }
                    }

                    isAutoReplyOn = autoReplyEnabled;
                    // No need to show dialog here, it will be handled in getForwardSetting()
                } else {
                    Toast.makeText(facultmessage.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<appPermission>> call, Throwable t) {
                Toast.makeText(facultmessage.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "API call failed: " + t.getMessage());
            }
        });
    }

    private void getForwardSetting() {
        apiServices.getforwordsetting(username).enqueue(new Callback<List<forwordsetting>>() {
            @Override
            public void onResponse(Call<List<forwordsetting>> call, Response<List<forwordsetting>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<forwordsetting> settings = response.body();
                    boolean messageForwardEnabled = false;

                    for (forwordsetting setting : settings) {
                        Log.d(TAG, "Forward Setting: " + setting.getCurrentuser() + " (Forward To: " + setting.getForworduser() + ")");
                        if (setting.getCurrentuser().equals(username)) {
                            ForwarduserName = setting.getForworduser();
                            messageForwardEnabled = true;
                            break;
                        }
                    }

                    isMessageForwardOn = messageForwardEnabled;
                     // Show dialog after setting the initial state
                } else {
                    Toast.makeText(facultmessage.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<forwordsetting>> call, Throwable t) {
                Toast.makeText(facultmessage.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "API call failed: " + t.getMessage());
            }
        });
    }

    private void showAutoReplyDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_auto_reply);

        Switch autoReplySwitch = dialog.findViewById(R.id.switch_auto_reply);
        Switch messageForwardSwitch = dialog.findViewById(R.id.switch_message_forward);
        Button forwardSettingButton = dialog.findViewById(R.id.btn_forward_setting);
        TextView forwardTextView = dialog.findViewById(R.id.forwordid);

       forwardTextView.setText(ForwarduserName);

        autoReplySwitch.setChecked(isAutoReplyOn);
        messageForwardSwitch.setChecked(isMessageForwardOn);

        autoReplySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                insertAppPermission(username);
            } else {
                deleteAppPermission(deletepermissoin);
            }
            isAutoReplyOn = isChecked;
            Toast.makeText(this, "Auto Reply " + (isChecked ? "On" : "Off"), Toast.LENGTH_SHORT).show();
        });

        messageForwardSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isMessageForwardOn = isChecked;
            Toast.makeText(this, "Message Forward " + (isChecked ? "On" : "Off"), Toast.LENGTH_SHORT).show();
        });

        forwardSettingButton.setOnClickListener(v -> {
            dialog.dismiss();
            showForwardSettingDialog();
        });

        dialog.show();
    }


    // Function to insert app permission using Retrofit
    private void insertAppPermission(String username) {
        // Create Retrofit instance and ApiService

        // Create the Autosetting object with the provided username
        appPermission appPermission = new appPermission(username);

        // Make the API call
        apiServices.insertAppPermission(appPermission).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Display success message
                    Toast.makeText(facultmessage.this, "Record inserted successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failure response
                    Toast.makeText(facultmessage.this, "Failed to insert record: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Log and display error message
                Log.e(TAG, "API call failed: " + t.getMessage());
                Toast.makeText(facultmessage.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Function to delete app permission using Retrofit
    private void deleteAppPermission(int id) {
        // Create Retrofit instance and ApiService


        // Make the API call
        apiServices.deleteAppPermission(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Display success message
                    Toast.makeText(facultmessage.this, "Record deleted successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failure response
                    Toast.makeText(facultmessage.this, "Failed to delete record: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Log and display error message
                Log.e(TAG, "API call failed: " + t.getMessage());
                Toast.makeText(facultmessage.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showForwardSettingDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_forward_setting);

        Spinner spinner = dialog.findViewById(R.id.spinner);
        EditText forwardNameEditText = dialog.findViewById(R.id.et_forward_name);
        Button saveButton = dialog.findViewById(R.id.btn_save_forward_name);

        // API call to fetch data
        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<TeacherData>> call = apiService.getAllTeachers();
        call.enqueue(new Callback<List<TeacherData>>() {
            @Override
            public void onResponse(Call<List<TeacherData>> call, Response<List<TeacherData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TeacherData> userInfoList = response.body();

                    // Create a list of names to display in the Spinner
                    List<String> names = new ArrayList<>();
                    for (TeacherData userInfo : userInfoList) {
                        names.add(userInfo.getFirstName() + " " + userInfo.getLastName());
                    }

                    // Set the data to the Spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(facultmessage.this, android.R.layout.simple_spinner_item, names);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                } else {
                    Toast.makeText(facultmessage.this, "Failed to retrieve user info", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TeacherData>> call, Throwable t) {
                Toast.makeText(facultmessage.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        forwardNameEditText.setText(forwardName);

        saveButton.setOnClickListener(v -> {
            forwardName = forwardNameEditText.getText().toString().trim();
            if (!forwardName.isEmpty()) {
                Toast.makeText(this, "Forward Name Saved: " + forwardName, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, faculty_dashboard.class);
        startActivity(intent);
        finish();  // Finish current activity to prevent returning on back press
        Log.d(TAG, "Navigating back to faculty_dashboard");
        super.onBackPressed();
    }
}
