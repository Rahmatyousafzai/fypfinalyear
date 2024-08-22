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
import adopter.OnTeacherClickListener;
import adopter.TeachercustomAdopter;
import facultyClasses.mWishlist;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import studentClasses.UserDataSingleton;
import studentClasses.UserRepository;
import studentClasses.studentData;

public class AllTeacherActivity extends AppCompatActivity implements OnTeacherClickListener {
    private Apiservices apiServices = RetrofitClient.getInstance();
    RecyclerView recyclerView;

    TextView profilename;
    ImageView profile;

    private String username,firstName,lastName, FullName, profileImage;
    private TeachercustomAdopter adapter;


    TextView txtNews,txtNotification,txtMessage;
    private List<user> teacherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allteacher);

        initializeViews();

        // Set profile name

// In any other activity where you want to access the username
        String username = UserDataSingleton.getInstance().getUsername();

        UserRepository userRepository = new UserRepository();
        userRepository.fetchUserData(username, new UserRepository.UserRepositoryCallback() {
            @Override
            public void onSuccess(studentData data) {
                // Access user data fields



                String programName = data.getProgramName();
                int semesterName = data.getSemesterName();
                String sectionName = data.getSectionName();

                profileImage=data.getProfileImage();
                firstName=data.getFirstName();
                lastName=data.getLastName();

                // Log user data
                Log.e("UserData.......", "Program Name...........: " + programName);
                Log.e("UserData......", "Semester Name..........: " + semesterName);
                Log.e("UserData.........", "Section Name:........... " + sectionName);

                // Optionally, update UI with user data
                TextView textView = findViewById(R.id.sectionandsamester);
                String displayData = "("+programName + " "+semesterName +""+sectionName+")";
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
        recyclerView=findViewById(R.id.allteacherRec);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TeachercustomAdopter(teacherList, this);
        recyclerView.setAdapter(adapter);

      //  loadTeachers();
    }




    private void initializeViews() {
        txtNews = findViewById(R.id.news);
        txtMessage = findViewById(R.id.txtmessage);
        txtNotification = findViewById(R.id.txtnotification);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);

    }






    @Override
    public void onTeacherClick(user teacher) {
        Intent intent = new Intent(this, sprfile.class);
        intent.putExtra("username", teacher.getUsername());
        intent.putExtra("firstName", teacher.getFirstName());
        intent.putExtra("lastName", teacher.getLastName());
        intent.putExtra("profileImage", teacher.getProfileImage());
        startActivity(intent);
    }

    @Override
    public void onTeacherClick(cuTeacher teacher) {
        Intent intent = new Intent(this, sprfile.class);
        intent.putExtra("username", teacher.getUsername());
        intent.putExtra("firstName", teacher.getFirstName());
        intent.putExtra("lastName", teacher.getLastName());
        intent.putExtra("profileImage", teacher.getProfileImage());
        startActivity(intent);

    }

    @Override
    public void onTeacherClick(Wish wish) {
        Intent intent = new Intent(this, sprfile.class);
        intent.putExtra("username", wish.getUsername());
        intent.putExtra("firstName", wish.getFirstName());
        intent.putExtra("lastName", wish.getLastName());
        intent.putExtra("profileImage", wish.getProfileImage());
        startActivity(intent);

    }

    @Override
    public void onTeacherClick(mWishlist wish) {

    }

    @Override
    public void onTeacherClick(Object item) {

    }

    /*private void loadTeachers() {
        apiServices.getAllTeachers().enqueue(new Callback<List<user>>() {
            @Override
            public void onResponse(Call<List<user>> call, Response<List<user>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<user> teachers = response.body();
                    for (user teacher : teachers) {
                        if (teacher.getProfileImage() == null || teacher.getProfileImage().isEmpty()) {
                            teacher.setProfileImage(String.valueOf(R.drawable.baseline_account_circle_24));
                        }
                    }
                    adapter.setTeacherList(teachers);
                }
            }

            @Override
            public void onFailure(Call<List<user>> call, Throwable t) {
                // Handle the error
            }
        });
    }

    */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }




    @Override
    public void onBackPressed() {
        // Navigate back to the login screen
        Intent intent = new Intent(this, stteacher.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }
}
