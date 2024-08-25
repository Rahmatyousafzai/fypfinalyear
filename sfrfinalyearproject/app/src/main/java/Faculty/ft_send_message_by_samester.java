package Faculty;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.SelectAudeince;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class ft_send_message_by_samester extends AppCompatActivity {

    private Button sendMessage;
    private EditText typeSomething;
    private TextView semesterSection;
    private TextView profileName;
    private TextView designation;
    private ImageView profile;
    private ProgressBar progressBar;
    private Apiservices apiservices;

    private String username;
    private ArrayList<Integer> selectedIds; // Define selectedIds to use in sendBulkWish

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_send_message_by_samester);

        // Initialize UI components
        initializeUI();

        // Fetch user data
        username = UserDataSingleton.getInstance().getUsername();
        fetchUserData();

        // Initialize Retrofit service
        apiservices = RetrofitClient.getInstance();

        // Set OnClickListener for sendMessage button
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Confirm before sending message
                showConfirmationDialog();
            }
        });
    }

    private void initializeUI() {
        sendMessage = findViewById(R.id.done);
        typeSomething = findViewById(R.id.typesomthing);
        semesterSection = findViewById(R.id.semsetersection);
        profileName = findViewById(R.id.profelname);
        designation = findViewById(R.id.disgnation);
        profile = findViewById(R.id.profilepicture);
        progressBar = findViewById(R.id.progressBar); // Ensure you have this in your layout

        // Extract data from Intent
        Intent intent = getIntent();
        selectedIds = intent.getIntegerArrayListExtra("selectedIds");
        ArrayList<String> selectedTitles = intent.getStringArrayListExtra("selectedTitles");

        StringBuilder allocationsString = new StringBuilder();
        if (selectedTitles != null) {
            for (String title : selectedTitles) {
                allocationsString.append(title).append("\n");
            }
            semesterSection.setText(allocationsString.toString());
        }
    }

    private void fetchUserData() {
        teacherRepository userRepository = new teacherRepository();
        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data) {
                updateTeacherProfile(data);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("UserData", "Error fetching user data: " + e.getMessage());
                Toast.makeText(ft_send_message_by_samester.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTeacherProfile(TeacherData data) {
        designation.setText(data.getDisgnatione());

        String profileImage = data.getProfileImage();
        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }

        String fullName = data.getFirstName() + " " + data.getLastName();
        profileName.setText(fullName);
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Are you sure you want to send this message?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Send the message
                        String selectedIdsString = convertIdsToString(selectedIds);
                        String wishMessage = typeSomething.getText().toString();
                        sendBulkWish(username, selectedIdsString, wishMessage);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    private String convertIdsToString(ArrayList<Integer> ids) {
        StringBuilder sb = new StringBuilder();
        if (ids != null) {
            for (int id : ids) {
                sb.append(id).append(",");
            }
            // Remove trailing comma
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
        }
        return sb.toString();
    }

    private void sendBulkWish(String senderId, String selectedIds, String wishMessage) {
        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Create request object
        SelectAudeince request = new SelectAudeince(senderId, selectedIds, wishMessage);

        // Make API call
        Call<Void> call = apiservices.sendBulkWish(request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Hide progress bar
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    // Show success message and redirect
                    Toast.makeText(ft_send_message_by_samester.this, "Wishes sent successfully!", Toast.LENGTH_SHORT).show();
                    redirectToDashboard();
                } else {
                    // Show error message
                    Toast.makeText(ft_send_message_by_samester.this, "Request failed: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Hide progress bar
                progressBar.setVisibility(View.GONE);

                // Show error message
                Toast.makeText(ft_send_message_by_samester.this, "Request failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void redirectToDashboard() {
        Intent intent = new Intent(ft_send_message_by_samester.this, faculty_dashboard.class);
        startActivity(intent);
        finish(); // Optional: Close the current activity
    }
}
