package Alumni;

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
import facultyClasses.mWishlist;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import student.snotification;


public class al_dahsboard extends AppCompatActivity implements OnTeacherClickListener {

    private static final String TAG = "stdashboard";
    private Apiservices apiServices;
    private RecyclerView recyclerView;
    private ImageView profile;
    private TextView txtNews, txtMessage, txtNotification, txtFavTeacher, profilename, textteacher;
  //  private Wishadapter adapter;
    private List<Wish> wishList = new ArrayList<>();

    private String username;
    private String firstName;
    private String lastName;
    private String profileImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_dahsboard);
        txtNews = findViewById(R.id.news);
        txtMessage = findViewById(R.id.txtmessage);
        txtNotification = findViewById(R.id.txtnotification);
        txtFavTeacher = findViewById(R.id.favtecher);
        textteacher = findViewById(R.id.textteacher);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        firstName = intent.getStringExtra("firstname");
        lastName = intent.getStringExtra("lastname");
        profileImage = intent.getStringExtra("profileimage");

        String fullName = firstName + " " + lastName;
        profilename.setText(fullName);

        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }

        apiServices = RetrofitClient.getInstance();
        recyclerView = findViewById(R.id.strcview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    //    adapter = new Wishadapter(this,wishList, this);
      //  recyclerView.setAdapter(adapter);

        Log.d("recycl","recyclerView Data "+recyclerView.toString());
        //Log.d("recycl","adopter Data "+adapter.toString());
     //   fetchAndDisplayWishes();

        textteacher.setOnClickListener(v -> {
            Intent teacherIntent = new Intent(al_dahsboard.this, alteachers.class);
            teacherIntent.putExtra("username", username);
            teacherIntent.putExtra("FullName", fullName);
            teacherIntent.putExtra("profileimage", profileImage);
            startActivity(teacherIntent);
            finish();
        });



        txtNotification.setOnClickListener(v -> {
            Intent teacherIntent = new Intent(al_dahsboard.this, snotification.class);
            teacherIntent.putExtra("username", username);
            teacherIntent.putExtra("FullName", fullName);
            teacherIntent.putExtra("profileimage", profileImage);
            startActivity(teacherIntent);
            finish();
        });

        txtFavTeacher.setOnClickListener(v -> {
            Intent favTeacherIntent = new Intent(al_dahsboard.this, alfavteacher.class);
            favTeacherIntent.putExtra("username", username);
            favTeacherIntent.putExtra("FullName", fullName);
            favTeacherIntent.putExtra("profileimage", profileImage);
            startActivity(favTeacherIntent);
            finish();
        });

        txtMessage.setOnClickListener(v -> {
            Intent messageIntent = new Intent(al_dahsboard.this, almessages.class);
            messageIntent.putExtra("username", username);
            messageIntent.putExtra("FullName", fullName);
            messageIntent.putExtra("profileimage", profileImage);
            startActivity(messageIntent);
            finish();
        });

        txtNotification.setOnClickListener(v -> {
            Intent notificationIntent = new Intent(al_dahsboard.this, snotification.class);
            startActivity(notificationIntent);
            finish();
        });
    }

    /*private void fetchAndDisplayWishes() {
        apiServices.getWishes().enqueue(new Callback<List<Wish>>() {
            @Override
            public void onResponse(Call<List<Wish>> call, Response<List<Wish>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    wishList.clear();
                    wishList.addAll(response.body());
          //          adapter.notifyDataSetChanged(); // Notify adapter of data change
                    Log.d(TAG, "Wishes fetched successfully. List size: " + wishList.size());
                } else {
                    Log.d(TAG, "Failed to get wishes. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Wish>> call, Throwable t) {
                Log.d(TAG, "Error fetching wishes: " + t.getMessage());
                Toast.makeText(al_dahsboard.this, "Error fetching wishes", Toast.LENGTH_SHORT).show();
            }
        });
    }
*/
    @Override
    public void onTeacherClick(user teacher) {
        // Handle teacher click
    }

    @Override
    public void onTeacherClick(cuTeacher teacher) {
        // Handle cuTeacher click
    }

    @Override
    public void onTeacherClick(Wish wish) {
        showToast("Clicked on: " + wish.getSwid());
    }

    @Override
    public void onTeacherClick(mWishlist wish) {

    }

    @Override
    public void onTeacherClick(Object item) {
        // Handle other item clicks
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
