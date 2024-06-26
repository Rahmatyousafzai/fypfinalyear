package Alumni;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ModeClasees.Emoji;
import ModeClasees.Message;
import ModeClasees.Wish;
import adopter.ChatAdapter;
import adopter.MessagListAdopter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class almessagebody extends AppCompatActivity {



    private Apiservices apiServices = RetrofitClient.getInstance();
    RecyclerView recyclerView;

    private GridView gridView;
    private ChatAdapter chatAdapter;
    private List<Message> messageList;
    TextView profilename, pfimageofteacher, tcname;
    ImageView profile, profileImageView, Emojipapolate;
    private String username, FullName, profileImage, TeacherUsername, teacher_lastname, teacher_firstname, teacher_username, TCfullname;
    private LinearLayout linearLayout;
    private MessagListAdopter adapter;
    private List<Wish> MessageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almessagebody);
        recyclerView = findViewById(R.id.messagesrecord);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profileimage);
        tcname = findViewById(R.id.tcname);
        profileImageView = findViewById(R.id.tcImage);
        Emojipapolate = findViewById(R.id.imojipapolate);
        gridView=findViewById(R.id.grid_view_emojis);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        FullName = intent.getStringExtra("FullName");
        profileImage = intent.getStringExtra("profileimage");
        TeacherUsername = intent.getStringExtra("teacher_username");
        teacher_firstname = intent.getStringExtra("teacher_firstName");
        teacher_lastname = intent.getStringExtra("teacher_lastName");
        teacher_username = intent.getStringExtra("teacher_profileImage");
        String teacherProfileImage = intent.getStringExtra("teacher_profileImage");

        Log.d("stteacher", "Profile Name: " + FullName);
        profilename.setText(FullName);
        TCfullname = (teacher_firstname + " " + teacher_lastname);

        Log.d("smessagebody", "Teacher ProfileImage: " + teacherProfileImage);

        if (teacherProfileImage != null && !teacherProfileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + teacherProfileImage + ".jpg";
            Picasso.get()
                    .load(imageUrl)
                    .error(R.drawable.baseline_account_circle_24) // Placeholder on error
                    .into(profileImageView);
        } else {
            profileImageView.setImageResource(R.drawable.baseline_account_circle_24); // Placeholder for null image
        }

        tcname.setText(TCfullname);

        Log.d("smessagebody", "Username: " + username);
        Log.d("smessagebody", "FullName: " + FullName);
        Log.d("smessagebody", "ProfileImage: " + profileImage);
        Log.d("smessagebody", "Teacher Username: " + teacher_username);
        Log.d("smessagebody", "Teacher FirstName: " + teacher_firstname);
        Log.d("smessagebody", "Teacher LastName: " + teacher_lastname);
        Log.d("smessagebody", "Teacher ProfileImage: " + pfimageofteacher);

        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String senderId = "BIIT0001"; // Get this dynamically
        String receiverId = username; // Get this dynamically

        Log.d("parms","teacherName"+senderId);
        Log.d("parms","Myusername"+receiverId);






        // Call API to fetch emojis
        fetchEmojis();
    }

    private void fetchEmojis() {
        Call<List<Emoji>> call = apiServices.getAllEmojis();
        call.enqueue(new Callback<List<Emoji>>() {

            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Emoji> emojiList = response.body();
                    // Assuming gridView is a GridView
                    ArrayAdapter<Emoji> emojiAdapter = new ArrayAdapter<>(almessagebody.this, android.R.layout.simple_list_item_1, emojiList);
                    gridView.setAdapter(emojiAdapter);
                } else {
                    Toast.makeText(almessagebody.this, "Failed to fetch emojis", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Toast.makeText(almessagebody.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }






}
