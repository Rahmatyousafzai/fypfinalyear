package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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

public class ftStudent extends AppCompatActivity {
ImageView imgback;
ListView tclistview;

    private List<Student> studentList;
    private List<Student> filteredList;
    private EditText searchEditText;
    String username;
    private RecyclerView recyclerView;
    private StudentAdopter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_student);

        TextView profilename = findViewById(R.id.profelname);
        ImageView profile = findViewById(R.id.profilepicture);
searchEditText=findViewById(R.id.serattext);
        username = UserDataSingleton.getInstance().getUsername();

        teacherRepository userRepository = new teacherRepository();

        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data)
            {
                // Access user data fields

                String Disignation = data.getDisgnatione();

                String profileImage = data.getProfileImage();
                String firstName = data.getFirstName();
                String lastName = data.getLastName();

                Log.e("UserData.........", "Section Name:........... " + Disignation);

                // Optionally, update UI with user data
                TextView textView = findViewById(R.id.disgnation);
                String displayData = (Disignation);
                textView.setText(displayData);
                if (profileImage != null && !profileImage.isEmpty()) {
                    String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" +profileImage + ".jpg";
                    Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
                } else {
                    profile.setImageResource(R.drawable.baseline_account_circle_24);
                }



                String fullName = firstName+ " " + lastName;
                profilename.setText(fullName);

            }

            @Override
            public void onFailure(Exception e) {
                Log.e("SomeActivity", "Error fetching user data: " + e.getMessage());
                // Handle error case, e.g., show a toast or an error message
            }
        });





        recyclerView = findViewById(R.id.StudentRC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchAlumni();
      //  searchEditText.onCheckIsTextEditor();


        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // No action needed here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Call filterStudents when text changes
                filterStudents();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No action needed here
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
        adapter.notifyDataSetChanged();
    }

    private void fetchAlumni() {
       Apiservices apiService = RetrofitClient.getInstance();

        Call<List<Student>> call = apiService.getstudent();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Student> studentList = response.body();
                    adapter = new StudentAdopter(ftStudent.this, studentList);
                    filteredList = new ArrayList<>(studentList);
                    adapter = new StudentAdopter(ftStudent.this, filteredList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(ftStudent.this, "Failed to fetch alumni", Toast.LENGTH_SHORT).show();
              Log.d("msg","student"+recyclerView);


                }

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("AlumniActivity", "API call failed: " + t.getMessage());
                Toast.makeText(ftStudent.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }











    @Override
    public void onBackPressed() {
        // Navigate back to the login screen
        Intent intent = new Intent(this, faculty_dashboard.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }



}