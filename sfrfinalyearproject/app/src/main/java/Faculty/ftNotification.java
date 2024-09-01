package Faculty;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import facultyClasses.UserDetailsAdapter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class ftNotification extends AppCompatActivity {

    private TextView messageTextView, profilename, news, notification, message, textViewDesignation;
    private ImageView profile, imgnews, imgNotification, imgmessage;
    private PopupWindow popupWindow;
    private UserDetailsAdapter userDetailsAdapter;
    private String username, firstName, lastName, profileImage;

    private TextView student, favstudent, typepost;
    private ImageView imgstudent, imgfavstudent, imgpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_notification);



        news = findViewById(R.id.news);
        notification = findViewById(R.id.txtnotification);
        message = findViewById(R.id.txtmessage);
        imgnews = findViewById(R.id.imgnews);
        imgNotification = findViewById(R.id.imgnptification);
        imgmessage = findViewById(R.id.imgmessage);

        imgpost = findViewById(R.id.typepost);
        typepost = findViewById(R.id.addpost);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);

        ImageView appSetting = findViewById(R.id.back);

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
                Toast.makeText(ftNotification.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize views

        // Retrieve username from UserDataSingleton
        username = UserDataSingleton.getInstance().getUsername();

        if (username != null) {
            fetchBirthdayMessage(username);
            fetchUserData(username);
        } else {
            Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show();
        }


       setClickListeners();



    }




    // Other methods for handling various click actions
    private void setClickListeners() {




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


    private void message() {
        Intent intent = new Intent(ftNotification.this, facultmessage.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void student() {
        Intent intent = new Intent(ftNotification.this, ftStudent.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void favstudent() {
        Intent intent = new Intent(ftNotification.this, ftfavstudent.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void notification() {
        Intent intent = new Intent(ftNotification.this,ftNotification.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    public void news() {
        Intent intent = new Intent(ftNotification.this, faculty_dashboard.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }


    private void fetchUserData(String username) {
        teacherRepository userRepository = new teacherRepository();
        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data) {
                if (data != null) {
                    firstName = data.getFirstName();
                    lastName = data.getLastName();
                    profileImage = data.getProfileImage();
                    String sectionName = data.getDisgnatione();

                    // Update UI with user data
                    profilename.setText(String.format("%s %s", firstName, lastName));
                    textViewDesignation.setText(sectionName);

                    // Load profile image
                    loadProfileImage(profileImage);
                } else {
                    Toast.makeText(ftNotification.this, "User data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("ftNotification", "Error fetching user data: " + e.getMessage());
                Toast.makeText(ftNotification.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProfileImage(String profileImage) {
        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl)
                    .placeholder(R.drawable.baseline_account_circle_24)
                    .error(R.drawable.baseline_account_circle_24)
                    .into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }
    }

    private void fetchBirthdayMessage(String username) {
        Apiservices apiService = RetrofitClient.getInstance();
        Call<String> call = apiService.getBirthdayUsers(username);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String message = response.body();
                    messageTextView.setText(message);

                    messageTextView.setOnClickListener(v -> fetchBirthdayUsersDetails(username));
                } else {
                    Toast.makeText(ftNotification.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ftNotification.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchBirthdayUsersDetails(String username) {
        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<BirthdayUser>> call = apiService.getBirthdayUsersdetails(username);

        call.enqueue(new Callback<List<BirthdayUser>>() {
            @Override
            public void onResponse(Call<List<BirthdayUser>> call, Response<List<BirthdayUser>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BirthdayUser> userDetails = response.body();
                    showBirthdayDetailsPopup(messageTextView, userDetails);
                } else {
                    Toast.makeText(ftNotification.this, "No details found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BirthdayUser>> call, Throwable t) {
                Toast.makeText(ftNotification.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showBirthdayDetailsPopup(View anchorView, List<BirthdayUser> detailsList) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }

        View popupView = LayoutInflater.from(this).inflate(R.layout.list_item_user_details, null);
        RecyclerView detailsRecyclerView = popupView.findViewById(R.id.Dtrecyccler);
        detailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        userDetailsAdapter = new UserDetailsAdapter(this, detailsList);
        detailsRecyclerView.setAdapter(userDetailsAdapter);

        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popupWindow.showAsDropDown(anchorView);

        popupView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                popupWindow.dismiss();
                return true;
            }
            return false;
        });

        userDetailsAdapter.setOnItemClickListener(position -> {
            BirthdayUser selectedUser = detailsList.get(position);
            Intent intent = new Intent(ftNotification.this, facultymessagebody.class);
            intent.putExtra("teacher_username", selectedUser.getUsername());
            intent.putExtra("teacher_firstName", selectedUser.getFirstName());
            intent.putExtra("teacher_lastName", selectedUser.getLastName());
            intent.putExtra("teacher_profileImage", selectedUser.getProfileImage() != null ? selectedUser.getProfileImage() : "");
            startActivity(intent);
        });
    }

    public void navigateToMessage() {
        Intent intent = new Intent(this, facultmessage.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    public void navigateToNotification() {
        Intent intent = new Intent(this, ftNotification.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    public void navigateToNews() {
        Intent intent = new Intent(this, faculty_dashboard.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }





    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, faculty_dashboard.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
