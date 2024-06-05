package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class faculty_dashboard extends AppCompatActivity implements OnTeacherClickListener {

    private static final String TAG = "ftdashboard";
    private Apiservices apiServices;
    TextView news, notification, message, student, favstudent, typepost, profilename;
    ImageView imgnews, imgNotification, imgmessage, imgstudent, imgfavstudent, imgpost, profile;
    private RecyclerView recyclerView;

    // private wish adapter;
    private List<Wish> wishList = new ArrayList<>();

    private String username;
    private String firstName;
    private String lastName;
    private String profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_dashboard);

        /////////////////////////////////////////////////////////////////
        //////////////////////////////  Find view.////////////////////////
        /////////////////////////////////////////////////////////////////
        news = findViewById(R.id.news);
        notification = findViewById(R.id.txtnotification);
        message = findViewById(R.id.txtmessage);
        favstudent = findViewById(R.id.txtfavstudent);
        student = findViewById(R.id.textstudent);
        imgnews = findViewById(R.id.imgnews);
        imgNotification = findViewById(R.id.imgnptification);
        imgmessage = findViewById(R.id.imgmessage);
        imgstudent = findViewById(R.id.imgstudent);
        imgfavstudent = findViewById(R.id.imgfavstudent);
        imgpost = findViewById(R.id.typepost);
        typepost = findViewById(R.id.addpost);

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
    //    adapter = new Wishadapter(this, wishList, this);
      //  recyclerView.setAdapter(adapter);

        Log.d("recycl", "recyclerView Data " + recyclerView.toString());
        //Log.d("recycl", "adopter Data " + adapter.toString());
       // fetchAndDisplayWishes();

        /////////////////////////////////////////////////////////////////////////
        ///////////////////////// Set click listeners////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        imgpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TYPEPOST();
            }
        });

        typepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TYPEPOST();
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news();
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification();
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message();
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student();
            }
        });

        favstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favstudent();
            }
        });

        imgnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news();
            }
        });

        imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification();
            }
        });

        imgmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message();
            }
        });

        imgstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student();
            }
        });

        imgfavstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favstudent();
            }
        });

    }

    //////////////////////////////////////////////////////
    /////////////////////////FUNCTIONS/////////////////////
    //////////////////////////////////////////////////////
    private void TYPEPOST() {
        Intent intent = new Intent(faculty_dashboard.this, faculty_select_message_option.class); //faculty_select_message_option
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void message() {
        Intent intent = new Intent(faculty_dashboard.this, facultmessage.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void student() {
        Intent intent = new Intent(faculty_dashboard.this, ftStudent.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void favstudent() {
        Intent intent = new Intent(faculty_dashboard.this, ftfavstudent.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void notification() {
        Intent intent = new Intent(faculty_dashboard.this, ftStudent.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    public void news() {
        Intent intent = new Intent(faculty_dashboard.this, faculty_dashboard.class);
        intent.putExtra("username", username);
        intent.putExtra("FullName", firstName + " " + lastName);
        intent.putExtra("profileimage", profileImage);
        startActivity(intent);
        finish();
    }

    private void fetchAndDisplayWishes() {
        apiServices.getWishes().enqueue(new Callback<List<Wish>>() {
            @Override
            public void onResponse(Call<List<Wish>> call, Response<List<Wish>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    wishList.clear();
                    wishList.addAll(response.body());
                    //adapter.notifyDataSetChanged(); // Notify adapter of data change
                    Log.d(TAG, "Wishes fetched successfully. List size: " + wishList.size());
                } else {
                    Log.d(TAG, "Failed to get wishes. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Wish>> call, Throwable t) {
                Log.d(TAG, "Error fetching wishes: " + t.getMessage());
                Toast.makeText(faculty_dashboard.this, "Error fetching wishes", Toast.LENGTH_SHORT).show();
            }
        });
    }

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
    public void onTeacherClick(Object item) {
        // Handle other item clicks
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
