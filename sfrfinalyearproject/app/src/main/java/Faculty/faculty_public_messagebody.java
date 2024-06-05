package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import genral.templete;
import modelclassespost.SendWishRequestDto;
import modelclassespost.SendWishResponse;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class faculty_public_messagebody extends AppCompatActivity {
    Button sendwish;
    ImageView addtemplate, back;
    private static final String TAG = "studentmessage";
    private String username;
    private String firstName;
    private String lastName;
    private String profileImage;
    private Apiservices apiServices;
    TextView profilename;
    ImageView profile;
    EditText typesomthing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_public_messagebody);

        sendwish = findViewById(R.id.sendwish);

        back = findViewById(R.id.back);
        profilename = findViewById(R.id.profelname);  // Initialize this
        profile = findViewById(R.id.profilepicture);          // Initialize this
typesomthing=findViewById(R.id.typesomthing);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        firstName = intent.getStringExtra("firstname");
        lastName = intent.getStringExtra("lastname");
        profileImage = intent.getStringExtra("profileimage");

        Log.d("usernameofsender","username"+username);
        String fullName = firstName + " " + lastName;
        profilename.setText(fullName);

        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }

        apiServices = RetrofitClient.getInstance();

        sendwish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Assume these values are obtained from your app's logic
                String senderId = username;
                String content = typesomthing.getText().toString();
                Integer templateId = null;  // Replace with actual value if available
                String messageType = null;  // Replace with actual value if available
                Integer emojiId = null;     // Replace with actual value if available
                Integer achievId = null;    // Replace with actual value if available
                String dateTime = getCurrentDateTime();
                Boolean isEmail = true;
                String enrollmentId = null; // Replace with actual value if available
                String receiverId = null;   // Replace with actual value if available
                String sectionId = null;    // Replace with actual value if available
                String semesterId = null;   // Replace with actual value if available
                String discipline = null;   // Replace with actual value if available
                String courseId = null;     // Replace with actual value if available
                String papulation = "Student";

                // Create the SendWishRequestDto object with all necessary parameters
                SendWishRequestDto wishData = new SendWishRequestDto(
                        senderId, content, templateId, messageType, emojiId, achievId, dateTime,
                        isEmail, enrollmentId, receiverId, sectionId, semesterId, discipline, courseId, papulation
                );

                sendWish(wishData);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }








        });





    }

    private void sendWish(SendWishRequestDto wishData) {
        apiServices.sendWishByPapluation(wishData).enqueue(new Callback<SendWishResponse>() {
            @Override
            public void onResponse(Call<SendWishResponse> call, Response<SendWishResponse> response) {
                if (response.isSuccessful()) {
                    SendWishResponse sendWishResponse = response.body();
                    if (sendWishResponse != null) {
                        Toast.makeText(faculty_public_messagebody.this, "Wish sent successfully with ID: " + sendWishResponse.getSendWishId(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(faculty_public_messagebody.this, "Wish sent successfully", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("Failed to send wish. Response code: ", "error sending wish" + response.code());
                }
            }

            @Override
            public void onFailure(Call<SendWishResponse> call, Throwable t) {
                Log.d("Error sending wish: ", "error sending" + t.getMessage());
                Toast.makeText(faculty_public_messagebody.this, "Error sending wish", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf.format(new Date());
    }

    private void back() {
        Intent intent = new Intent(faculty_public_messagebody.this, faculty_select_message_option.class);
        startActivity(intent);
        finish();
    }

    private void addtemplate() {
        Intent intent = new Intent(faculty_public_messagebody.this, templete.class);
        startActivity(intent);
        finish();
    }

    private void sendwish() {
        Intent intent = new Intent(faculty_public_messagebody.this, faculty_dashboard.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Navigate back to the login screen
        Intent intent = new Intent(this, faculty_select_message_option.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }

}
