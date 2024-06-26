package student;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import adopter.CurrentTeachercustomAdopter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.UserDataSingleton;
import studentClasses.UserRepository;
import studentClasses.studentData;

public class stteacher extends AppCompatActivity implements CurrentTeachercustomAdopter.OnTeacherClickListener {

    private Apiservices apiServices = RetrofitClient.getInstance();
    RecyclerView recyclerView;
    ImageView imgBack;
    TextView profilename,txtviewall;
    ImageView profile,imgviewall;
    private String username, firstName,lastName, profileImage;

    private CurrentTeachercustomAdopter adapter;
    private List<cuTeacher> teacherList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stteacher);
        imgBack = findViewById(R.id.imgback);
        recyclerView = findViewById(R.id.allteacherRec);
        profilename = findViewById(R.id.profelname);

        txtviewall=findViewById(R.id.txtviewall);
        imgviewall=findViewById(R.id.imgviewall);
        profile = findViewById(R.id.profilepicture);



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



        txtviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(stteacher.this, AllTeacherActivity.class);

                intent.putExtra("profileimage", profileImage);

                startActivity(intent);
                finish();

            }
        });
        txtviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(stteacher.this, AllTeacherActivity.class);

                intent.putExtra("profileimage", profileImage);

                startActivity(intent);
                finish();

            }
        });



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CurrentTeachercustomAdopter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        loadTeachers();

        imgBack.setOnClickListener(v -> finish());

        loadTeachers();
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

    }

    @Override
    public void onTeacherClick(Object item) {

    }

    private void loadTeachers() {
        apiServices.getCurrentTeacher(username).enqueue(new Callback<List<cuTeacher>>() {
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
            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
