package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import student.Program;
import student.Section;
import student.Semester;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class facultypapulationMessage extends AppCompatActivity {

    private Apiservices apiservices;
    private String username;
    private Spinner spinnerProgram;
    private List<Course> courses;
private  int selectedProgramId;
    private LinearLayout layoutCheckboxes;

    private List<Program> programs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultypapulation_message);

        TextView profilename = findViewById(R.id.profelname);
        ImageView profile = findViewById(R.id.profilepicture);
        Button BTNdDONE = findViewById(R.id.btndone);
        layoutCheckboxes = findViewById(R.id.layout_checkboxes);
        apiservices = RetrofitClient.getInstance();

        username = UserDataSingleton.getInstance().getUsername();

        teacherRepository userRepository = new teacherRepository();

        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data) {
                String Disignation = data.getDisgnation();
                String profileImage = data.getProfileImage();
                String firstName = data.getFirstName();
                String lastName = data.getLastName();

                Log.e("UserData.........", "Section Name:........... " + Disignation);

                TextView textView = findViewById(R.id.disgnation);
                String displayData = (Disignation);
                textView.setText(displayData);
                if (profileImage != null && !profileImage.isEmpty()) {
                    String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
                    Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
                } else {
                    profile.setImageResource(R.drawable.baseline_account_circle_24);
                }

                String fullName = firstName + " " + lastName;
                profilename.setText(fullName);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("SomeActivity", "Error fetching user data: " + e.getMessage());
            }
        });

        spinnerProgram = findViewById(R.id.programspinner);
        layoutCheckboxes = findViewById(R.id.layout_checkboxes);

        // Fetch programs and populate spinner
        fetchPrograms();

        spinnerProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Program selectedProgram = programs.get(position);
                 selectedProgramId = Integer.parseInt(selectedProgram.getProgramId());
                Log.d("SelectedProgramID", "Selected Program ID: " + selectedProgramId);
                fetchSemesters(username, selectedProgramId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected
            }
        });
    }

    private void fetchPrograms() {
        Call<List<Program>> call = apiservices.getPrograms("BIIT0001");
        call.enqueue(new Callback<List<Program>>() {
            @Override
            public void onResponse(Call<List<Program>> call, Response<List<Program>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    programs = response.body();
                    populateProgramSpinner();
                } else {
                    Toast.makeText(facultypapulationMessage.this, "Failed to fetch programs", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Program>> call, Throwable t) {
                Toast.makeText(facultypapulationMessage.this, "Failed to fetch programs: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateProgramSpinner() {
        List<String> programTitles = new ArrayList<>();
        for (Program program : programs) {
            programTitles.add(program.getProgramTitle());
        }

        ArrayAdapter<String> programAdapter = new ArrayAdapter<>(facultypapulationMessage.this, android.R.layout.simple_spinner_item, programTitles);
        programAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProgram.setAdapter(programAdapter);

        spinnerProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Program selectedProgram = programs.get(position);
                int selectedProgramId = Integer.parseInt(selectedProgram.getProgramId());
                Log.d("SpinnerItemSelected", "Selected Program ID: " + selectedProgramId);
                fetchSemesters(username, selectedProgramId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected
            }
        });
    }

    private void fetchSemesters(String username, int selectedProgramId) {
        Call<List<Semester>> call = apiservices.GetSemestersForProgram(username, selectedProgramId);
        Log.d("fetchSemesters", "Fetching semesters for username: " + username + ", program ID: " + selectedProgramId);
        call.enqueue(new Callback<List<Semester>>() {
            @Override
            public void onResponse(Call<List<Semester>> call, Response<List<Semester>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Semester> semesters = response.body();
                    fetchSections(semesters);
                } else {
                    Toast.makeText(facultypapulationMessage.this, "Failed to fetch semesters", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Semester>> call, Throwable t) {
                Toast.makeText(facultypapulationMessage.this, "Failed to fetch semesters: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchSections(List<Semester> semesters) {
        for (final Semester semester : semesters) {
            Call<List<Section>> call = apiservices.getSections(username, semester.getSemesetrID());
            call.enqueue(new Callback<List<Section>>() {
                @Override
                public void onResponse(Call<List<Section>> call, Response<List<Section>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Section> sections = response.body();
                        populateCheckBoxes(semester, sections);
                    } else {
                        Toast.makeText(facultypapulationMessage.this, "Failed to fetch sections for semester " + semester.getSemsternumber(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Section>> call, Throwable t) {
                    Toast.makeText(facultypapulationMessage.this, "Failed to fetch sections for semester " + semester.getSemsternumber() + ": " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void populateCheckBoxes(final Semester semester, List<Section> sections) {
        layoutCheckboxes.removeAllViews(); // Clear previous checkboxes

        if (sections != null && !sections.isEmpty()) {
            CheckBox semesterCheckBox = new CheckBox(facultypapulationMessage.this);
            semesterCheckBox.setText("Semester " + semester.getSemsternumber());
            layoutCheckboxes.addView(semesterCheckBox);

            final List<CheckBox> sectionCheckBoxes = new ArrayList<>(); // Store section checkboxes to manage their visibility later

            for (final Section section : sections) {
                CheckBox sectionCheckBox = new CheckBox(facultypapulationMessage.this);
                sectionCheckBox.setText(section.getSectionname()); // Display section name
                layoutCheckboxes.addView(sectionCheckBox);
                sectionCheckBox.setVisibility(View.GONE); // Initially hide section checkboxes

                // Set click listener for section checkbox
                sectionCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // Handle checkbox checked state if needed
                    }
                });
            }

            // Set semester checkbox listener
            semesterCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    for (CheckBox sectionCheckBox : sectionCheckBoxes) {
                        sectionCheckBox.setVisibility(isChecked ? View.VISIBLE : View.GONE); // Toggle visibility of section checkboxes
                    }
                }
            });

            // Button to send selected data to the next activity
            Button sendButton = new Button(facultypapulationMessage.this);
            sendButton.setText("Send Message");
            layoutCheckboxes.addView(sendButton);

            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Gather selected data and send to next activity
                    List<String> selectedSections = new ArrayList<>();

                    for (CheckBox sectionCheckBox : sectionCheckBoxes) {
                        if (sectionCheckBox.isChecked()) {
                            selectedSections.add(sectionCheckBox.getText().toString()); // Add section name to list
                        }
                    }

                    // Start activity with selected data
                    Intent intent = new Intent(facultypapulationMessage.this, ft_send_message_by_samester.class);
                    intent.putExtra("programId", selectedProgramId);
                    intent.putExtra("semesterIds", new int[] { semester.getSemesetrID() }); // Pass semester ID as array
                    intent.putExtra("sections", selectedSections.toArray(new String[0]));
                    startActivity(intent);
                }
            });
        } else {
            // Handle case when no sections are available for the semester
            Toast.makeText(facultypapulationMessage.this, "No sections available for semester " + semester.getSemsternumber(), Toast.LENGTH_SHORT).show();
        }
    }
}
