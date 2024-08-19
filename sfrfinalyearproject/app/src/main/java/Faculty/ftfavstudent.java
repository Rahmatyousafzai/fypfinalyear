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
import adopter.FavTeachercustomAdopter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class ftfavstudent extends AppCompatActivity  implements FavTeachercustomAdopter.OnTeacherClickListener{

    private Apiservices apiServices = RetrofitClient.getInstance();
    private RecyclerView recyclerView;
    private FavTeachercustomAdopter adapter;
    private List<cuTeacher> teacherList = new ArrayList<>();

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftfavstudent);
        TextView profilename = findViewById(R.id.profelname);
       ImageView profile = findViewById(R.id.profilepicture);

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

        recyclerView = findViewById(R.id.Rcfavestudent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavTeachercustomAdopter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        loadfavstudent();



    }


    private void loadfavstudent() {
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
        // Navigate back to the login screen
        Intent intent = new Intent(this, faculty_dashboard.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }

    @Override
    public void onTeacherClick(user teacher) {

    }

    @Override
    public void onTeacherClick(cuTeacher teacher) {

    }

    @Override
    public void onTeacherClick(Wish wish) {

    }

    @Override
    public void onTeacherClick(Object item) {

    }
}