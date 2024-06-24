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
import facultyClasses.postpapolation;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class ft_send_message_by_samester extends AppCompatActivity {

    private Button done;
    private EditText typesomething;
    private TextView semsetersection;
    private TextView profilename;
    private TextView disgnation;
    private ImageView profile;

    private String username;
    private int selectedProgramId;
    private ArrayList<Integer> selectedSemesterIds;
    private ArrayList<Integer> selectedSectionIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_send_message_by_samester);

        initializeUI();
        username = UserDataSingleton.getInstance().getUsername();
        fetchTeacherData();
        extractIntentExtras();
        displaySelectedDetails();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void initializeUI() {
        done = findViewById(R.id.done);
        typesomething = findViewById(R.id.typesomthing);
        semsetersection = findViewById(R.id.semsetersection);
        profilename = findViewById(R.id.profelname);
        disgnation = findViewById(R.id.disgnation);
        profile = findViewById(R.id.profilepicture);
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
        disgnation.setText(data.getDisgnation());

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
            selectedProgramId = intent.getIntExtra("programId", -1);
            selectedSemesterIds = intent.getIntegerArrayListExtra("semesterIds");
            selectedSectionIds = intent.getIntegerArrayListExtra("sectionIds");
        }
    }

    private void displaySelectedDetails() {
        Log.d("ft_send_message", "Selected Program ID: " + selectedProgramId);
        Log.d("ft_send_message", "Selected Semester IDs: " + selectedSemesterIds);
        Log.d("ft_send_message", "Selected Section IDs: " + selectedSectionIds);

        semsetersection.setText(selectedProgramId + " " + selectedSemesterIds + " " + selectedSectionIds);

        if (selectedProgramId == -1 || selectedSemesterIds == null || selectedSectionIds == null) {
            Toast.makeText(this, "Failed to receive data", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void sendMessage() {
        String content = typesomething.getText().toString();
        if (content.isEmpty()) {
            showErrorDialog("Message content cannot be empty.");
            return;
        }

        String formattedDateTime = getCurrentFormattedDateTime();

        List<Audience> audienceList = new ArrayList<>();
        for (int semesterId : selectedSemesterIds) {
            for (int sectionId : selectedSectionIds) {
                Audience audienceDto = new Audience(selectedProgramId, semesterId, sectionId,null);
                audienceList.add(audienceDto);
            }
        }

        postpapolation sendWishRequestDto = new postpapolation(
                username,
                content,
                null, // Assuming esid is not used or can be null
                audienceList
        );

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending message...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Apiservices apiService = RetrofitClient.getInstance();

        Call<Void> sendWishCall = apiService.sendWish(sendWishRequestDto);
        sendWishCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    // Handle successful response
                    Toast.makeText(ft_send_message_by_samester.this, "Wish sent successfully.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    // Handle unsuccessful response
                    showErrorDialog("Failed to send wish. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                // Handle failure
                showErrorDialog("Error sending wish: " + t.getMessage());
            }
        });
    }

    private String getCurrentFormattedDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
