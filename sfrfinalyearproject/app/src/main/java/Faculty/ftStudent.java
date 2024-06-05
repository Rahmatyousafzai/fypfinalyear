package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

import ModeClasees.Student;
import adopter.StudentAdopter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ftStudent extends AppCompatActivity {
ImageView imgback;
ListView tclistview;
    private RecyclerView recyclerView;
    private StudentAdopter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_student);
        imgback=findViewById(R.id.tcback);
        //tclistview=findViewById(R.id.tclistview);



        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });

        recyclerView = findViewById(R.id.StudentRC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchAlumni();


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









    public void back(){


        Intent intent=new Intent(ftStudent.this,faculty_dashboard.class);
        startActivity(intent);
        finish();

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