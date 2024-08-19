package Faculty;

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
import adopter.MessagListAdopter;
import adopter.OnTeacherClickListener;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.smessagebody;
import student.sprfile;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class facultmessage extends AppCompatActivity implements OnTeacherClickListener {
    private Apiservices apiServices = RetrofitClient.getInstance();
    RecyclerView recyclerView;

    TextView profilename;
    ImageView profile;
    private String username, FullName, profileImage;

    private MessagListAdopter adapter;
    private List<Wish> MessageList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultmessage);

        recyclerView = findViewById(R.id.allteacherRec);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profileimage);

        username = UserDataSingleton.getInstance().getUsername();

        teacherRepository userRepository = new teacherRepository();

        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data)
            {
                // Access user data fields

                String Disignation = data.getDisgnatione();

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


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessagListAdopter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

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
        // Implement as needed
    }

    @Override
    public void onTeacherClick(Wish wish) {
        Intent intent = new Intent(this, smessagebody.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", FullName);
        intent.putExtra("profileimage", profileImage);
        intent.putExtra("teacher_username", wish.getUsername());
        intent.putExtra("teacher_firstName", wish.getFirstName());
        intent.putExtra("teacher_lastName", wish.getLastName());
        intent.putExtra("teacher_profileImage", wish.getProfileImage() != null ? wish.getProfileImage() : ""); // Ensure no null
        startActivity(intent);
    }

    @Override
    public void onTeacherClick(Object item) {
        // Implement as needed
    }

    private void loadTeachers() {
        apiServices.getinboxlist(username).enqueue(new Callback<List<Wish>>() {
            @Override
            public void onResponse(Call<List<Wish>> call, Response<List<Wish>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Wish> message = response.body();
                    for (Wish messagelist : message) {
                        if (messagelist.getProfileImage() == null || messagelist.getProfileImage().isEmpty()) {
                            messagelist.setProfileImage(String.valueOf(R.drawable.baseline_account_circle_24));
                        }
                    }
                    adapter.setTeacherList(message);
                    Log.d("loadTeachers", "Message list loaded successfully");
                } else {
                    Log.e("loadTeachers", "Failed to load Message list. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Wish>> call, Throwable t) {
                Log.e("loadTeachers", "Error loading teachers: " + t.getMessage());
                // Handle the error
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
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






