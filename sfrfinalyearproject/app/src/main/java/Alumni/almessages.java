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
import adopter.MessagListAdopter;
import adopter.OnTeacherClickListener;
import facultyClasses.mWishlist;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import student.sprfile;

public class almessages extends AppCompatActivity implements OnTeacherClickListener {



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
        setContentView(R.layout.activity_almessages);









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
        adapter = new MessagListAdopter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        //loadTeachers();
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
        Intent intent = new Intent(this, almessagebody.class);
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
    public void onTeacherClick(mWishlist wish) {

    }

    @Override
    public void onTeacherClick(Object item) {
        // Implement as needed
    }
/*
    private void loadTeachers() {
        apiServices.GetRelatedWishes(username).enqueue(new Callback<List<Wish>>() {
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

    */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
