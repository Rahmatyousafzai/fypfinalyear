package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import ModeClasees.user;
import facultyClasses.BirhdaynotificationAdopter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class ftNotification extends AppCompatActivity {
    ImageView imgback;
    ListView tclistview;
    String username;
    private RecyclerView recyclerView;
    private BirhdaynotificationAdopter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_notification);



        TextView profilename = findViewById(R.id.profelname);
        ImageView profile = findViewById(R.id.profilepicture);

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





        recyclerView = findViewById(R.id.StudentRC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchAlumni();
    }



    private void fetchAlumni() {
        Apiservices apiService = RetrofitClient.getInstance();

        Call<List<user>> call = apiService.getBirhday(username);
        call.enqueue(new Callback<List<user>>() {
            @Override
            public void onResponse(Call<List<user>> call, Response<List<user>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<user> studentList = response.body();
                    adapter = new BirhdaynotificationAdopter(ftNotification.this, studentList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(ftNotification.this, "Failed to fetch alumni", Toast.LENGTH_SHORT).show();
                    Log.d("msg","student"+recyclerView);


                }

            }

            @Override
            public void onFailure(Call<List<user>> call, Throwable t) {
                Log.e("AlumniActivity", "API call failed: " + t.getMessage());
                Toast.makeText(ftNotification.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
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