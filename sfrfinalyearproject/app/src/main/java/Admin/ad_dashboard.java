package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
//import adopter.Wishadapter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ad_dashboard extends AppCompatActivity implements OnTeacherClickListener {

    private TextView adminPost, addTeacher, adNews, adMessage, addAchievement, adStudent, profileName, notification;
    private RecyclerView recyclerView;
    //private Wishadapter adapter;
    private List<Wish> wishList = new ArrayList<>();
    private ImageView profile, popup;

    private static final String TAG = "ad_dashboard";
    private Apiservices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_dashboard);

        // Initialize views and image list
        initViews();

        // Set up RecyclerView and click listeners for views
        setClickListeners();

        fetchAndDisplayWishes();

    }

    private void initViews() {
        // Initialize views by finding them through their IDs
        adminPost = findViewById(R.id.adminpost);
        notification = findViewById(R.id.adtxtnotification);
        addTeacher = findViewById(R.id.textadteacher);
        adNews = findViewById(R.id.adnews);
        adMessage = findViewById(R.id.adtxtmessage);
        addAchievement = findViewById(R.id.adtextachvment);
        adStudent = findViewById(R.id.adstudent);
        profileName = findViewById(R.id.profelname);
        recyclerView = findViewById(R.id.adrcview);
        profile = findViewById(R.id.profileimage);
        popup = findViewById(R.id.popup);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String firstName = intent.getStringExtra("firstname");
        String lastName = intent.getStringExtra("lastname");
        String profileImage = intent.getStringExtra("profileimage");

        String fullName = firstName + " " + lastName;
        profileName.setText(fullName);

        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }

        apiServices = RetrofitClient.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // adapter = new Wishadapter(this, wishList, this);
       // recyclerView.setAdapter(adapter);
    }

    private void fetchAndDisplayWishes() {
        apiServices.getWishes().enqueue(new Callback<List<Wish>>() {
            @Override
            public void onResponse(Call<List<Wish>> call, Response<List<Wish>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    wishList.clear();
                    wishList.addAll(response.body());
               //     adapter.notifyDataSetChanged(); // Notify adapter of data change
                    Log.d(TAG, "Wishes fetched successfully. List size: " + wishList.size());
                } else {
                    Log.d(TAG, "Failed to get wishes. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Wish>> call, Throwable t) {
                Log.d(TAG, "Error fetching wishes: " + t.getMessage());
                Toast.makeText(ad_dashboard.this, "Error fetching wishes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTeacherClick(user teacher) {
        // Handle teacher click
    }

    @Override
    public void onTeacherClick(cuTeacher teacher) {

    }

    @Override
    public void onTeacherClick(Wish wish) {
        showToast("Clicked on: " + wish.getSwid());
    }

    @Override
    public void onTeacherClick(Object item) {
        // Handle other item clicks
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setClickListeners() {
        // Set click listeners for each view to trigger the corresponding methods
        adminPost.setOnClickListener(v -> adminpost());
        notification.setOnClickListener(v -> notification());
        adMessage.setOnClickListener(v -> adMessage());
        adNews.setOnClickListener(v -> news());
        addTeacher.setOnClickListener(v -> addTeacher());
        adStudent.setOnClickListener(v -> addStudent());
        addAchievement.setOnClickListener(v -> viewAchievement());
        popup.setOnClickListener(v -> {
            // Handle popup click
        });
    }

    // Methods for handling clicks on different views
    private void adminpost() {
        // Start the activity for admin post sharing options
        Intent intent = new Intent(ad_dashboard.this, Admin.postshareoption.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    private void viewAchievement() {
        // Start the activity for viewing achievements
        Intent intent = new Intent(ad_dashboard.this, ad_addachivment.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    public void notification() {
        // Start the activity for notifications
        Intent intent = new Intent(ad_dashboard.this, ad_notification.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    public void adMessage() {
        // Start the activity for admin messages
        Intent intent = new Intent(ad_dashboard.this, Admin.admessage.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    public void news() {
        // Start the activity for news
        Intent intent = new Intent(ad_dashboard.this, ad_dashboard.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    public void addTeacher() {
        // Start the activity for adding teachers
        Intent intent = new Intent(ad_dashboard.this, Admin.ad_teachers.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }

    public void addStudent() {
        // Start the activity for adding students
        Intent intent = new Intent(ad_dashboard.this, Admin.ad_student.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }
}
