package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import facultyClasses.BirhdaynotificationAdopter;
import mydataapi.RetrofitClient;
import studentClasses.UserDataSingleton;
import studentClasses.UserRepository;
import studentClasses.studentData;

public class ftNotification extends AppCompatActivity {
    ImageView imgback;
    ListView tclistview;
    String username;

    TextView txtNews,txtNotification,txtMessage;

    String firstName,lastName,profileImage;
    ImageView profile;
    TextView profilename;
    private RecyclerView recyclerView;
    private BirhdaynotificationAdopter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_notification);


        recyclerView = findViewById(R.id.rcnotification);

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


        recyclerView = findViewById(R.id.rcnotification);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));




    }


        private void initializeViews() {
            txtNews = findViewById(R.id.news);
            txtMessage = findViewById(R.id.txtmessage);
            txtNotification = findViewById(R.id.txtnotification);

            profilename = findViewById(R.id.profelname);
            profile = findViewById(R.id.profilepicture);
            recyclerView = findViewById(R.id.strcview);
        }











        @Override
    public void onBackPressed() {
        // Navigate back to the login screen
        Intent intent = new Intent(this, faculty_dashboard.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }







}