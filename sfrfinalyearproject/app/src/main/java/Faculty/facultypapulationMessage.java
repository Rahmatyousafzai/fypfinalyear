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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private LinearLayout layoutSemesters;
    private Button BTNdDONE;

    private List<Program> programs = new ArrayList<>();
    private Map<Integer, List<Section>> semesterSectionsMap = new HashMap<>();
    private List<Integer> selectedSemesterIds = new ArrayList<>();
    private List<Integer> selectedSectionIds = new ArrayList<>();
    private int selectedProgramId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultypapulation_message);

        TextView profilename = findViewById(R.id.profelname);
        ImageView profile = findViewById(R.id.profilepicture);
        BTNdDONE = findViewById(R.id.btndone);
        layoutSemesters = findViewById(R.id.layout_checkboxes);
        apiservices = RetrofitClient.getInstance();

        username = UserDataSingleton.getInstance().getUsername();

        teacherRepository userRepository = new teacherRepository();
        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data) {
                String Disignation = data.getDisgnatione();
                String profileImage = data.getProfileImage();
                String firstName = data.getFirstName();
                String lastName = data.getLastName();

                Log.d("UserData", "Designation: " + Disignation);

                TextView textView = findViewById(R.id.disgnation);
                textView.setText(Disignation);

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
                Log.e("UserDataFetch", "Error fetching user data: " + e.getMessage());
            }
        });

        spinnerProgram = findViewById(R.id.programspinner);

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

        BTNdDONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void fetchPrograms() {
        Call<List<Program>> call = apiservices.getPrograms(username);
        call.enqueue(new Callback<List<Program>>() {
            @Override
            public void onResponse(Call<List<Program>> call, Response<List<Program>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    programs.clear();
                    programs.addAll(response.body());
                    populateProgramSpinner();
                } else {
                    String errorMessage = "Failed to fetch programs. Response code: " + response.code() + ", Message: " + response.message();
                    Log.e("fetchPrograms", errorMessage);
                    Toast.makeText(facultypapulationMessage.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Program>> call, Throwable t) {
                String errorMessage = "Failed to fetch programs: " + t.getMessage();
                Log.e("fetchPrograms", errorMessage);
                Toast.makeText(facultypapulationMessage.this, errorMessage, Toast.LENGTH_SHORT).show();
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
    }

    private void fetchSemesters(String username, int selectedProgramId) {
        Call<List<Semester>> call = apiservices.GetSemestersForProgram(username, selectedProgramId);
        Log.d("fetchSemesters", "Fetching semesters for username: " + username + ", program ID: " + selectedProgramId);
        call.enqueue(new Callback<List<Semester>>() {
            @Override
            public void onResponse(Call<List<Semester>> call, Response<List<Semester>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Semester> semesters = response.body();
                    populateSemesterCheckBoxes(semesters);
                } else {
                    String errorMessage = "Failed to fetch semesters. Response code: " + response.code() + ", Message: " + response.message();
                    Log.e("fetchSemesters", errorMessage);
                    Toast.makeText(facultypapulationMessage.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Semester>> call, Throwable t) {
                String errorMessage = "Failed to fetch semesters: " + t.getMessage();
                Log.e("fetchSemesters", errorMessage);
                Toast.makeText(facultypapulationMessage.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateSemesterCheckBoxes(List<Semester> semesters) {
        layoutSemesters.removeAllViews(); // Clear previous checkboxes

        for (Semester semester : semesters) {
            LinearLayout semesterLayout = new LinearLayout(this);
            semesterLayout.setOrientation(LinearLayout.VERTICAL);

            CheckBox semesterCheckBox = new CheckBox(this);
            semesterCheckBox.setText("Semester " + semester.getSemsternumber());
            semesterCheckBox.setTag(semester);
            semesterCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Semester selectedSemester = (Semester) buttonView.getTag();
                    if (isChecked) {
                        if (!selectedSemesterIds.contains(selectedSemester.getSemesetrID())) {
                            selectedSemesterIds.add(selectedSemester.getSemesetrID());
                        }
                        fetchSections(selectedSemester, semesterLayout);
                    } else {
                        selectedSemesterIds.remove((Integer) selectedSemester.getSemesetrID());
                        removeSections(semesterLayout);
                    }
                }
            });
            semesterLayout.addView(semesterCheckBox);
            layoutSemesters.addView(semesterLayout);
        }
    }

    private void fetchSections(Semester semester, LinearLayout semesterLayout) {
        Call<List<Section>> call = apiservices.getSections(username, semester.getSemesetrID());
        call.enqueue(new Callback<List<Section>>() {
            @Override
            public void onResponse(Call<List<Section>> call, Response<List<Section>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Section> sections = response.body();
                    semesterSectionsMap.put(semester.getSemesetrID(), sections); // Store sections by semester ID
                    populateSectionCheckBoxes(sections, semesterLayout);
                } else {
                    String errorMessage = "Failed to fetch sections for semester " + semester.getSemsternumber() + ". Response code: " + response.code() + ", Message: " + response.message();
                    Log.e("fetchSections", errorMessage);
                    Toast.makeText(facultypapulationMessage.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Section>> call, Throwable t) {
                String errorMessage = "Failed to fetch sections: " + t.getMessage();
                Log.e("fetchSections", errorMessage);
                Toast.makeText(facultypapulationMessage.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateSectionCheckBoxes(List<Section> sections, LinearLayout semesterLayout) {
        for (Section section : sections) {
            CheckBox sectionCheckBox = new CheckBox(this);
            sectionCheckBox.setText("Section " + section.getSectionname());
            sectionCheckBox.setTag(section);
            sectionCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Section selectedSection = (Section) buttonView.getTag();
                    if (isChecked) {
                        if (!selectedSectionIds.contains(selectedSection.getSectionid())) {
                            selectedSectionIds.add(selectedSection.getSectionid());
                        }
                    } else {
                        selectedSectionIds.remove((Integer) selectedSection.getSectionid());
                    }
                }
            });
            semesterLayout.addView(sectionCheckBox);
        }
    }

    private void removeSections(LinearLayout semesterLayout) {
        // Remove all section checkboxes from the semester layout
        for (int i = 1; i < semesterLayout.getChildCount(); i++) {
            View child = semesterLayout.getChildAt(i);
            if (child instanceof CheckBox) {
                semesterLayout.removeViewAt(i);
                i--; // Adjust index after removal
            }
        }
    }

    private void sendMessage() {
        if (selectedSectionIds.isEmpty()) {
            Toast.makeText(this, "No sections selected", Toast.LENGTH_SHORT).show();
            return;
        }

        // Debugging logs
        Log.d("sendMessage", "Selected Program ID: " + selectedProgramId);
        Log.d("sendMessage", "Selected Semester IDs: " + selectedSemesterIds.toString());
        Log.d("sendMessage", "Selected Section IDs: " + selectedSectionIds.toString());

        // Create an Intent and pass the selected program ID, semester IDs, and section IDs
        Intent intent = new Intent(facultypapulationMessage.this, ft_send_message_by_samester.class);
        intent.putExtra("selectedProgramId", selectedProgramId);
        intent.putIntegerArrayListExtra("selectedSemesterIds", new ArrayList<>(selectedSemesterIds));
        intent.putIntegerArrayListExtra("selectedSectionIds", new ArrayList<>(selectedSectionIds));

        // Debugging Toasts
        Toast.makeText(this, "Selected Program ID: " + selectedProgramId, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Selected Semester IDs: " + selectedSemesterIds.toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Selected Section IDs: " + selectedSectionIds.toString(), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
}
