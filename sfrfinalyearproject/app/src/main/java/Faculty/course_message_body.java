package Faculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import facultyClasses.Audience;
import facultyClasses.InsertPapolationDataDto;
import facultyClasses.publicWish;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class course_message_body extends AppCompatActivity {
    private Button done;
    private EditText typesomething;
    private TextView courseslist;
    private TextView profilename;
    private TextView disgnation;
    private ImageView profile;

    private String username;
    private ArrayList<String> selectedCourseIds;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_message_body);

        initializeUI();
        username = UserDataSingleton.getInstance().getUsername();
        fetchTeacherData();
        extractIntentExtras();
        displaySelectedDetails();

        progressDialog = new ProgressDialog(this);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    private void initializeUI() {
        done = findViewById(R.id.sendwish);
        typesomething = findViewById(R.id.typepost);
        courseslist = findViewById(R.id.courseslist);
        profilename = findViewById(R.id.profelname);
        disgnation = findViewById(R.id.desgnation);
        profile = findViewById(R.id.profileimage);
    }

    private void fetchTeacherData() {
        teacherRepository userRepository = new teacherRepository();
        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data) {
                updateTeacherProfile(data);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("ft_send_message", "Error fetching teacher data: " + e.getMessage());
            }
        });
    }

    private void updateTeacherProfile(TeacherData data) {
        disgnation.setText(data.getDisgnatione());

        String profileImage = data.getProfileImage();
        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }

        String fullName = data.getFirstName() + " " + data.getLastName();
        profilename.setText(fullName);
    }

    private void extractIntentExtras() {
        Intent intent = getIntent();
        if (intent != null) {
            selectedCourseIds = intent.getStringArrayListExtra("selectedCourseIds");
        }
    }

    private void displaySelectedDetails() {
        Log.d("ft_send_message", "Selected course IDs: " + selectedCourseIds);
        courseslist.setText(selectedCourseIds.toString());

        if (selectedCourseIds == null) {
            Toast.makeText(this, "Failed to receive data", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void showConfirmationDialog() {
        String content = typesomething.getText().toString();
        if (content.isEmpty()) {
            showErrorDialog("Message content cannot be empty.");
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Confirm Send")
                .setMessage("Are you sure you want to send this message?")
                .setPositiveButton("Yes", (dialog, which) -> sendMessage(username,content,selectedCourseIds))
                .setNegativeButton("No", null)
                .show();
    }



    private String getCurrentFormattedDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }




    private void sendMessage(String username, String content, List<String> selectedCourseIds) {
        publicWish wish = new publicWish();
        wish.setContent(content);
        wish.setEmojiID(null); // or set a valid value if needed
        wish.setTempleteID(null); // or set a valid value if needed
        wish.setAchievID(null); // or set a valid value if needed
        wish.setDateTime(getCurrentFormattedDateTime()); // Set the current date and time
        wish.setEmail(true);

        InsertPapolationDataDto dto = new InsertPapolationDataDto();
        dto.setWish(wish);

        // Create Audience list
        List<Audience> audienceList = new ArrayList<>();
        for (String courseId : selectedCourseIds) {
            Audience audience = new Audience();
            audience.setSenderid(username);
            audience.setCourseID(courseId); // Assuming Audience has a setCourseID method
            audience.setSemesterID(null); // Can be null if not used
            audience.setSectionID(null); // Can be null if not used
            audienceList.add(audience);
        }
        dto.setAudienceList(audienceList);

        // Create AdueinceDetail list
        List<AdueinceDetail> adueinceDetailList = new ArrayList<>();
        for (String courseId : selectedCourseIds) {
            AdueinceDetail adueinceDetail = new AdueinceDetail();
            adueinceDetail.setAdid(null); // Assuming Adid can be null
            adueinceDetail.setStatus(0); // Or set a valid status if needed
            adueinceDetail.setReceiverID(null); // Assuming ReceiverID can be null
            adueinceDetail.setWishID(null); // Assuming WishID can be null
            adueinceDetail.setReaddate(getCurrentFormattedDateTime()); // Set the current date and time
            adueinceDetailList.add(adueinceDetail);
        }
        dto.setAdueinceDetailsList(adueinceDetailList);

        // Assuming selectedCourseIds is used in the DTO
        dto.setSelectedcourseIds(selectedCourseIds);

        progressDialog.setMessage("Sending message...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Apiservices apiService = RetrofitClient.getInstance();

        // Call the Retrofit method
        Call<Void> call = apiService.insertpublicmessage(dto);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(course_message_body.this, "Wish sent successfully.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(course_message_body.this, "Failed to send wish. Response code: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(course_message_body.this, "Error sending wish: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
