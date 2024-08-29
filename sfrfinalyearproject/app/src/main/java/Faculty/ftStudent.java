package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
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
import java.util.stream.Collectors;

import ModeClasees.Student;
import adopter.StudentAdopter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;
import facultyClasses.OnStudentInteractionListener;

public  class ftStudent extends AppCompatActivity implements OnStudentInteractionListener {

    ImageView imgback;
    private List<Student> studentList = new ArrayList<>();
    private List<Student> filteredList = new ArrayList<>();
    private EditText searchEditText;
    private RecyclerView recyclerView;
    private StudentAdopter adapter;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_student);

        TextView profilename = findViewById(R.id.profelname);
        ImageView profile = findViewById(R.id.profilepicture);
        searchEditText = findViewById(R.id.serattext);

        username = UserDataSingleton.getInstance().getUsername();

        teacherRepository userRepository = new teacherRepository();
        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data) {
                String Disignation = data.getDisgnatione();
                String profileImage = data.getProfileImage();
                String firstName = data.getFirstName();
                String lastName = data.getLastName();

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
                Log.e("SomeActivity", "Error fetching user data: " + e.getMessage());
                Toast.makeText(ftStudent.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = findViewById(R.id.StudentRC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchStudents();

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // No action needed here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterStudents();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No action needed here
            }
        });
    }

    private void fetchStudents() {
        Apiservices apiService = RetrofitClient.getInstance();

        Call<List<Student>> call = apiService.getstudent();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    studentList = response.body();
                    Log.d("fetchStudents", "Fetched data size: " + studentList.size());
                    filteredList = new ArrayList<>(studentList);
                    if (adapter == null) {
                        adapter = new StudentAdopter(ftStudent.this, studentList, ftStudent.this);
                        recyclerView.setAdapter(adapter);
                    } else {
                        adapter.updateData(filteredList);
                    }
                } else {
                    Toast.makeText(ftStudent.this, "Failed to fetch students", Toast.LENGTH_SHORT).show();
                    Log.d("fetchStudents", "Response unsuccessful or empty");
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("fetchStudents", "API call failed: " + t.getMessage());
                Toast.makeText(ftStudent.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterStudents() {
        String query = searchEditText.getText().toString().toLowerCase();
        filteredList = studentList.stream()
                .filter(student -> student.getFname().toLowerCase().contains(query) ||
                        student.getLname().toLowerCase().contains(query) ||
                        student.getUsername().toLowerCase().contains(query))
                .collect(Collectors.toList());
        adapter.updateData(filteredList);
    }

    @Override
    public void onSendMessage(Student student) {
        Intent intent = new Intent(this, facultymessagebody.class);
        intent.putExtra("username", student.getUsername());
        intent.putExtra("fname", student.getFname());
        intent.putExtra("lname", student.getLname());
        intent.putExtra("profile_image", student.getImage());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, faculty_dashboard.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
