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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sfrfinalyearproject.R;
import com.example.sfrfinalyearproject.admin_login;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dashboardclasese.wishingadopter;
import dashboardclasese.wishingclass;
import facultyClasses.Reaction;
import facultyClasses.appPermission;
import facultyClasses.forwordsetting;
import mydataapi.Apiservices;
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
    private String username, firstName, lastName, profileImage, ForwarduserName,forwrodprofileName;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isLoading = false;
    private int currentSwId;


    private String forwardName = "", selectedName,selectedusername;  // To store forward name
    private boolean isAutoReplyOn = false;  // Store the state of Auto Reply
    private boolean isMessageForwardOn = false;
    private int deletepermissoin;
    private int forwordingID;
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

        ImageView appSetting = findViewById(R.id.back);

        appSetting.setOnClickListener(v -> showAutoReplyDialog());
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
        reaction.setEmojiID(emojiid);
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
                Intent intent =new Intent(faculty_dashboard.this, admin_login.class);
                startActivity(intent);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(faculty_dashboard.this, admin_login.class);
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
                    Toast.makeText(faculty_dashboard.this, "Record inserted successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failure response
                    Toast.makeText(faculty_dashboard.this, "Failed to insert record: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Log and display error message
                Log.e(TAG, "API call failed: " + t.getMessage());
                Toast.makeText(faculty_dashboard.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(faculty_dashboard.this, "Record deleted successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failure response
                    Toast.makeText(faculty_dashboard.this, "Failed to delete record: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Log and display error message
                Log.e(TAG, "API call failed: " + t.getMessage());
                Toast.makeText(faculty_dashboard.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
                    faculty_dashboard.TeacherAdapter teacherAdapter = new faculty_dashboard.TeacherAdapter(faculty_dashboard.this, userInfoList);

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
                    Toast.makeText(faculty_dashboard.this, "Failed to retrieve user info", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TeacherData>> call, Throwable t) {
                Toast.makeText(faculty_dashboard.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(faculty_dashboard.this, "Record deleted successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failure response
                    Toast.makeText(faculty_dashboard.this, "Failed to delete record: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Log and display error message
                Log.e(TAG, "API call failed: " + t.getMessage());
                Toast.makeText(faculty_dashboard.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(this, admin_login.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }


}
