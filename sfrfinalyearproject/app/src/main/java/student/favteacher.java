package student;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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
import adopter.FavTeachercustomAdopter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.UserDataSingleton;
import studentClasses.UserRepository;
import studentClasses.studentData;

public class favteacher extends AppCompatActivity implements FavTeachercustomAdopter.OnTeacherClickListener {

    private Apiservices apiServices = RetrofitClient.getInstance();
    private RecyclerView recyclerView;
    private ImageView imgBack;
    private TextView profilename, sectionsamester;
    private ImageView profile;
    private String username, firstName, lastName, profileImage;

    private FavTeachercustomAdopter adapter;
    private List<cuTeacher> teacherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favteacher);

        initializeViews();

        // Retrieve username from UserDataSingleton
        username = UserDataSingleton.getInstance().getUsername();

        // Fetch user data
        fetchUserData();

        recyclerView = findViewById(R.id.faveacherRec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavTeachercustomAdopter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        loadTeachers();
    }

    private void initializeViews() {

        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);
        sectionsamester = findViewById(R.id.sectionandsamester);
    }

    private void fetchUserData() {
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
                String displayData = "(" + programName + " " + semesterName + " " + sectionName + ")";
                profilename.setText(firstName + " " + lastName);
                sectionsamester.setText(displayData);

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
                // Handle error case, e.g., show a toast or an error message
            }
        });
    }

    @Override
    public void onTeacherClick(user teacher) {
        // Handle click on user teacher
    }

    @Override
    public void onTeacherClick(cuTeacher teacher) {
        // Handle click on custom teacher
    }

    @Override
    public void onTeacherClick(Wish wish) {
        // Handle click on wish
    }

    @Override
    public void onTeacherClick(Object item) {
        // Handle click on any other item
    }

    private void loadTeachers() {
        apiServices.getFavTeacher(username).enqueue(new Callback<List<cuTeacher>>() {
            @Override
            public void onResponse(Call<List<cuTeacher>> call, Response<List<cuTeacher>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<cuTeacher> teachers = response.body();
                    for (cuTeacher teacher : teachers) {
                        if (teacher.getProfileImage() == null || teacher.getProfileImage().isEmpty()) {
                            teacher.setProfileImage(String.valueOf(R.drawable.baseline_account_circle_24));
                        }
                    }
                    adapter.setTeacherList(teachers);
                }
            }

            @Override
            public void onFailure(Call<List<cuTeacher>> call, Throwable t) {
                // Handle the error
                Log.e("FavTeacher", "Error loading teachers: " + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Navigate back to the dashboard
        Intent intent = new Intent(this, stdashboard.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }
}
