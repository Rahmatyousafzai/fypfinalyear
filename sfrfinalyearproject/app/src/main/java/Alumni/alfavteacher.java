package Alumni;

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
import student.sprfile;

public class alfavteacher extends AppCompatActivity implements FavTeachercustomAdopter.OnTeacherClickListener {

    private Apiservices apiServices = RetrofitClient.getInstance();
    RecyclerView recyclerView;
    ImageView imgBack;
    TextView profilename;
    ImageView profile;
    private String username, FullName, profileImage;

    private FavTeachercustomAdopter adapter;
    private List<cuTeacher> teacherList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alfavteacher);


        imgBack = findViewById(R.id.imgback);
        recyclerView = findViewById(R.id.allteacherRec);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profileimage);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        FullName = intent.getStringExtra("FullName");
        profileImage = intent.getStringExtra("profileimage");
        Log.d("stteacher", "Profile Name: " + FullName);
        // Set profile name
        profilename.setText(FullName);

        // Load profile image using Picasso
        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }







        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavTeachercustomAdopter(new ArrayList<>(), this);
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
            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}





