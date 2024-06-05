package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import facultyClasses.Course;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class facultypapulationMessage extends AppCompatActivity {

    private Apiservices apiservices;
    private String username;
    private String firstName;
    private String lastName;
    private String profileImage;

    private Spinner spinnerProgram;
    private List<Course> courses;
    private LinearLayout layoutCheckboxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultypapulation_message);

        TextView profilename = findViewById(R.id.profelname);
        ImageView profile = findViewById(R.id.profilepicture);
        layoutCheckboxes = findViewById(R.id.layout_checkboxes);

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

        spinnerProgram = findViewById(R.id.programspinner);
        layoutCheckboxes = findViewById(R.id.layout_checkboxes);

        // Populate spinner with programs
        retrieveCoursesFromAPI();
    }

    private void retrieveCoursesFromAPI() {
        // Initialize apiservices if not already done
        if (apiservices == null) {
            apiservices = RetrofitClient.getInstance();
        }

        apiservices.getSemesterSection("BIIT0001").enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    courses = response.body();
                    populateSpinner();
                } else {
                    // Handle unsuccessful response or null body
                    // For example:
                    // Toast.makeText(facultypapulationMessage.this, "Failed to retrieve courses", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                // Handle failure
                // For example:
                // Log.e("RetrieveCourses", "Error: " + t.getMessage());
                // Toast.makeText(facultypapulationMessage.this, "Failed to retrieve courses", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateSpinner() {
        List<String> programTitles = new ArrayList<>();
        for (Course selection : courses) {
            programTitles.add(selection.getProgramTitle());
        }
        ArrayAdapter<String> programAdapter = new ArrayAdapter<>(facultypapulationMessage.this, android.R.layout.simple_spinner_item, programTitles);
        programAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProgram.setAdapter(programAdapter);

        spinnerProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Course selectedCourse = courses.get(position);
                createCheckboxesForProgram(selectedCourse);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected
            }
        });
    }


    private void createCheckboxesForProgram(Course selectedCourse) {
        layoutCheckboxes.removeAllViews();
        for (int i = 1; i <= selectedCourse.getSemesterNo(); i++) {
            CheckBox checkBox = new CheckBox(facultypapulationMessage.this);
            checkBox.setText("Semester " + i);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Handle checkbox state change
                }
            });
            layoutCheckboxes.addView(checkBox);
        }
    }
}
