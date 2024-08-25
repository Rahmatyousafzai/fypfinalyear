package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class ft_send_message_by_course extends AppCompatActivity {
    private Button done;
    private String username;
    private ImageView profile;
    private Apiservices apiService;
    private TextView profilename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_send_message_by_course);

        username = UserDataSingleton.getInstance().getUsername();

        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);
        done = findViewById(R.id.done);

        // Initialize API service
        apiService = RetrofitClient.getInstance();

        // Fetch and display user data
        fetchUserData();

        // Fetch and display courses


        // Handle Done button click

    }

    private void fetchUserData() {
        teacherRepository userRepository = new teacherRepository();

        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data) {
                String designation = data.getDisgnatione();
                String profileImage = data.getProfileImage();
                String firstName = data.getFirstName();
                String lastName = data.getLastName();

                Log.d("UserData", "Designation: " + designation);

                // Update UI with user data
                TextView designationView = findViewById(R.id.disgnation);
                designationView.setText(designation);

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
                Log.e("UserData", "Error fetching user data: " + e.getMessage());
                Toast.makeText(ft_send_message_by_course.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void sendSelectedCourses(List<String> selectedCourseIds) {
        Intent intent = new Intent(ft_send_message_by_course.this, course_message_body.class); // Target activity
        intent.putStringArrayListExtra("selectedCourseIds", new ArrayList<>(selectedCourseIds));
        startActivity(intent);
    }
}
