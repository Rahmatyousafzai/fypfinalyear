package Faculty;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.example.sfrfinalyearproject.admin_login;
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

    private String username, firstName, lastName, profileImage, ForwarduserName,forwrodprofileName;

    private String forwardName = "", selectedName,selectedusername;  // To store forward name
    private boolean isAutoReplyOn = false;  // Store the state of Auto Reply
    private boolean isMessageForwardOn = false;
    private int deletepermissoin;
    private int forwordingID;

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
        intent.putExtra("forwordedName",forwardName);

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
                            ForwarduserName = setting.getFname()+""+setting.getLname();

                            forwrodprofileName = setting.getFname()+""+setting.getLname();
                            forwordingID=setting.getStid();
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
        TextView forwardSettingButton = dialog.findViewById(R.id.btn_forward_setting);
        TextView forwardTextView = dialog.findViewById(R.id.forwordid);
        TextView Logout=dialog.findViewById(R.id.logout);

        ImageView imgLogout=dialog.findViewById(R.id.imgLogout);
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(facultmessage.this, admin_login.class);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(facultmessage.this, admin_login.class);
            }
        });

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


    // Custom Adapter for AutoCompleteTextView and Spinner
    private void showForwardSettingDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_forward_setting);

        AutoCompleteTextView autoCompleteTextView = dialog.findViewById(R.id.autoCompleteTextView);
        Spinner spinner = dialog.findViewById(R.id.spinner);
        EditText forwardNameEditText = dialog.findViewById(R.id.et_forward_name);
        Button saveButton = dialog.findViewById(R.id.btn_save_forward_name);
        Button removeButton = dialog.findViewById(R.id.btn_save_remove_name);
        Button updateButton = dialog.findViewById(R.id.btn_save_update_name);

        forwardNameEditText.setText(forwrodprofileName);

        // API call to fetch data
        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<TeacherData>> call = apiService.getAllTeachers();
        call.enqueue(new Callback<List<TeacherData>>() {
            @Override
            public void onResponse(Call<List<TeacherData>> call, Response<List<TeacherData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TeacherData> userInfoList = response.body();

                    // Use the custom TeacherAdapter
                    TeacherAdapter teacherAdapter = new TeacherAdapter(facultmessage.this, userInfoList);

                    // Set the adapter to AutoCompleteTextView
                    autoCompleteTextView.setAdapter(teacherAdapter);
                    autoCompleteTextView.setThreshold(1);

                    // Set the adapter to Spinner
                    spinner.setAdapter(teacherAdapter);

                    // Handle AutoCompleteTextView item selection
                    autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
                        TeacherData selectedTeacher = teacherAdapter.getItem(position);
                        if (selectedTeacher != null) {
                            forwardNameEditText.setText(selectedTeacher.getFirstName() + " " + selectedTeacher.getLastName());
                            selectedusername = selectedTeacher.getUsername();
                            selectedName = selectedTeacher.getFirstName() + " " + selectedTeacher.getLastName();
                            ;
                        }
                    });

                    // Handle Spinner item selection
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            TeacherData selectedTeacher = teacherAdapter.getItem(position);
                            if (selectedTeacher != null) {
                                forwardNameEditText.setText(selectedTeacher.getFirstName() + " " + selectedTeacher.getLastName());
                                selectedusername = selectedTeacher.getUsername();
                                selectedName = selectedTeacher.getFirstName() + " " + selectedTeacher.getLastName();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {


                            forwardNameEditText.setText(ForwarduserName);
                            // Handle case when nothing is selected, if needed
                        }
                    });

                } else {
                    Toast.makeText(facultmessage.this, "Failed to retrieve user info", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TeacherData>> call, Throwable t) {
                Toast.makeText(facultmessage.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Handle save button click
        saveButton.setOnClickListener(v -> {
            forwardName = forwardNameEditText.getText().toString().trim();
            if (!forwardName.isEmpty()) {
                insertForwordSetting(username, selectedusername);
                Toast.makeText(this, "Forward Name Saved: " + forwardName, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle remove button click
        removeButton.setOnClickListener(v -> {
            deleteAppsetting(forwordingID);
            Toast.makeText(this, "Record removed", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        // Handle update button click
        updateButton.setOnClickListener(v -> {
            updateForwardSetting(forwordingID, selectedusername);
            Toast.makeText(this, "Record updated", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        // Show the dialog
        dialog.show();
    }

    private void updateForwardSetting(int id, String forworduser) {
        // Create the updated forward setting object
        forwordsetting updatedForwordSetting = new forwordsetting(id,selectedusername);
         // Assuming `id` is a field in your `forwordsetting` class
        updatedForwordSetting.setForworduser(forworduser);

        // Create the API service instance
        Apiservices apiService = RetrofitClient.getInstance();

        // Make the API call to update the forward setting
        Call<Void> call = apiService.updateForwardSetting(id, updatedForwordSetting);

        // Enqueue the call to execute it asynchronously
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Record updated successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to update record: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void deleteAppsetting(int id) {
        // Create Retrofit instance and ApiService


        // Make the API call
        apiServices.Deleteforwodsetting(id).enqueue(new Callback<Void>() {
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


    private void insertForwordSetting(String currentuser, String forworduser) {
        // Create the newForwordSetting object
        forwordsetting newForwordSetting = new forwordsetting(currentuser,forworduser);
        newForwordSetting.setCurrentuser(currentuser);
        newForwordSetting.setForworduser(forworduser);

        // Get the API service
        Apiservices apiService = RetrofitClient.getInstance();

        // Make the POST request
        Call<Void> call = apiService.insertForwordSetting(newForwordSetting);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    Toast.makeText(getApplicationContext(), "Record inserted successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle error response
                    Toast.makeText(getApplicationContext(), "Failed to insert record: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public class TeacherAdapter extends ArrayAdapter<TeacherData> {

        public TeacherAdapter(Context context, List<TeacherData> teachers) {
            super(context, 0, teachers);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
            }

            TeacherData teacher = getItem(position);
            TextView textView = convertView.findViewById(android.R.id.text1);

            if (teacher != null) {
                textView.setText(teacher.getFirstName() + " " + teacher.getLastName());
            }

            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            }

            TeacherData teacher = getItem(position);
            TextView textView = convertView.findViewById(android.R.id.text1);

            if (teacher != null) {
                textView.setText(teacher.getFirstName() + " " + teacher.getLastName());
            }

            return convertView;
        }

        @Override
        public TeacherData getItem(int position) {
            return super.getItem(position);
        }
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
