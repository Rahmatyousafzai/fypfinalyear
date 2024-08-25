package Faculty;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
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

    Apiservices apiservices;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_send_message_by_samester);
        username = UserDataSingleton.getInstance().getUsername();
        initializeUI();
        fetchTeacherData();



        apiservices=RetrofitClient.getInstance();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  showConfirmationDialog();
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























    /*private void showConfirmationDialog() {
        String content = typesomething.getText().toString();
        if (content.isEmpty()) {
            showErrorDialog("Message content cannot be empty.");
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Confirm Send")
                .setMessage("Are you sure you want to send this message?")
                .setPositiveButton("Yes", (dialog, which) -> sendMessage())
                .setNegativeButton("No", null)
                .show();
    }

    /*private void sendMessage() {
        String content = typesomething.getText().toString();
        if (content.isEmpty()) {
            showErrorDialog("Message content cannot be empty.");
            return;
        }

        String formattedDateTime = getCurrentFormattedDateTime();

        List<Audience> audienceList = new ArrayList<>();
        for (int semesterId : selectedSemesterIds) {
            for (int sectionId : selectedSectionIds) {
                Audience audienceDto = new Audience(selectedProgramId, semesterId, sectionId, null);
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
    }*/




   /* public class course_message_body extends AppCompatActivity {
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
//            done.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showConfirmationDialog();
//                }
//            });
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

//        private void showConfirmationDialog() {
//            String content = typesomething.getText().toString();
//            if (content.isEmpty()) {
//                showErrorDialog("Message content cannot be empty.");
//                return;
//            }
//
//            new AlertDialog.Builder(this)
//                    .setTitle("Confirm Send")
//                    .setMessage("Are you sure you want to send this message?")
//                    .setPositiveButton("Yes", (dialog, which) -> sendMessage(username,content,selectedCourseIds))
//                    .setNegativeButton("No", null)
//                    .show();
//        }



        private String getCurrentFormattedDateTime() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            return sdf.format(new Date());
        }



/*
        private void sendMessage(String username,String content, List<String> selecedcours) {
            publicWish wish = new publicWish();
            wish.setContent(content);
            wish.setEmojiID(null);
            wish.setTempleteID(null);
            wish.setAchievID(null);
            wish.setDateTime("");
            wish.setEmail(true);
            InsertPapolationDataDto dto = new InsertPapolationDataDto();


            Audience audience = new Audience();
            audience.setSenderid(username);
            AdueinceDetail adueinceDetail = new AdueinceDetail();
            adueinceDetail.setAdid(null);
            adueinceDetail.setStatus(0);

            dto.setWish(wish);
            dto.setAudienceList(Collections.singletonList(audience));
            dto.setAdueinceDetailsList(Collections.singletonList(adueinceDetail));
            dto.setSelectedcourseIds(Arrays.asList(selecedcours.toString()));


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
                        Toast.makeText(Faculty.course_message_body.this, "Wish sent successfully.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Faculty.course_message_body.this, "Failed to send wish. Response code: " + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(Faculty.course_message_body.this, "Error sending wish: " + t.getMessage(), Toast.LENGTH_LONG).show();
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

    */






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
