package Faculty;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import facultyClasses.Course;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class ft_send_message_by_course extends AppCompatActivity {
Button done;
String username;
    private String firstName;
    private String lastName;
    private String profileImage;
    ImageView profile;
    private Apiservices apiService;
    TextView profilename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_send_message_by_course);

        username = UserDataSingleton.getInstance().getUsername();

        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);

        done=findViewById(R.id.done);

        username = UserDataSingleton.getInstance().getUsername();

        teacherRepository userRepository = new teacherRepository();

        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data)
            {
                // Access user data fields

                String Disignation = data.getDisgnation();

                String profileImage = data.getProfileImage();
                String firstName = data.getFirstName();
                String lastName = data.getLastName();

                Log.e("UserData.........", "Section Name:........... " + Disignation);

                // Optionally, update UI with user data
                TextView textView = findViewById(R.id.disgnation);
                String displayData = (Disignation);
                textView.setText(displayData);
                if (profileImage != null && !profileImage.isEmpty()) {
                    String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" +profileImage + ".jpg";
                    Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
                } else {
                    profile.setImageResource(R.drawable.baseline_account_circle_24);
                }



                String fullName = firstName+ " " + lastName;
                profilename.setText(fullName);

            }

            @Override
            public void onFailure(Exception e) {
                Log.e("SomeActivity", "Error fetching user data: " + e.getMessage());
                // Handle error case, e.g., show a toast or an error message
            }
        });

        apiService = RetrofitClient.getInstance();

        fetchCourses();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    private void fetchCourses() {
        Call<List<Course>> call = apiService.getCourse(username);
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    displayCourses(response.body());
                } else {
                    Toast.makeText(ft_send_message_by_course.this, "Failed to fetch courses", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(ft_send_message_by_course.this, "Network error! Please try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayCourses(List<Course> courses) {
        LinearLayout container = findViewById(R.id.container);

        for (Course course : courses) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(course.getCourseid()); // Display course id or title as needed
            checkBox.setTag(course); // Store Course object in tag for reference
            container.addView(checkBox);
        }
    }

    // Example method to get selected course ids
    private List<String> getSelectedCourseIds() {
        LinearLayout container = findViewById(R.id.container);
        List<String> selectedIds = new ArrayList<>();

        for (int i = 0; i < container.getChildCount(); i++) {
            View childView = container.getChildAt(i);
            if (childView instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) childView;
                if (checkBox.isChecked()) {
                    Course course = (Course) checkBox.getTag();
                    if (course != null) {
                        selectedIds.add(course.getCourseid());
                    }
                }
            }
        }

        return selectedIds;
    }
}