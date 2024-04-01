package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import adopter.samester_adopter;
import adopter.student_show_samester;

public class ad_student extends AppCompatActivity {
    Spinner spinnerdeportment;
    Spinner spinnersamester;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_student);

        Button adselect = findViewById(R.id.select);
        recyclerView = findViewById(R.id.Recsamseter);
        spinnerdeportment = findViewById(R.id.deparometspinner);
        spinnersamester = findViewById(R.id.semesterspinner);
        ImageView back=findViewById(R.id.back);

        populateSpinners();
        setupRecyclerView();

        adselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slectsemster();
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });



    }

    private void back() {

        Intent intent=new Intent(ad_student.this,ad_dashboard.class);
        startActivity(intent);
        finish();
    }

    private void slectsemster() {


        Intent intent=new Intent(ad_student.this,ad_section.class);
        startActivity(intent);
        finish();

        // You can also perform other actions based on the selected values, such as fetching data from a database
        // or updating the UI accordingly.
    }

    private void populateSpinners() {
        List<String> departments = getDepartmentData(); // Retrieve department data
        List<String> semesters = getSemesterData(); // Retrieve semester data

        // Create adapters for spinners
        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, semesters);

        // Set adapters to spinners
        spinnerdeportment.setAdapter(departmentAdapter);
        spinnersamester.setAdapter(semesterAdapter);
    }

    private List<String> getDepartmentData() {
        // Replace this with your actual data retrieval logic
        List<String> departments = new ArrayList<>();
        departments.add("BSIT");
        departments.add("BSCS");
        departments.add("BSAI");
        departments.add("BSSE");

        return departments;
    }

    private List<String> getSemesterData() {
        // Replace this with your actual data retrieval logic
        List<String> semesters = new ArrayList<>();
        semesters.add("Semester 1");
        semesters.add("Semester 2");
        semesters.add("Semester 3");
        semesters.add("Semester 4");
        semesters.add("Semester 5");
        semesters.add("Semester 6");
        semesters.add("Semester 7");
        semesters.add("Semester 8");


        return semesters;
    }

    private void setupRecyclerView() {
        List<student_show_samester> dataList = getDataList(); // Retrieve data list for RecyclerView

        // Create RecyclerView adapter
        samester_adopter adapter = new samester_adopter(dataList);

        // Set layout manager and adapter to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private List<student_show_samester> getDataList() {
        // Replace this with your actual data retrieval logic
        List<student_show_samester> dataList = new ArrayList<>();
        dataList.add(new student_show_samester("BSIT-1A", false));
        dataList.add(new student_show_samester("BSAI-1A", false));
        dataList.add(new student_show_samester("BSSE-1A", false));
        dataList.add(new student_show_samester("BSIT-1B", false));
        dataList.add(new student_show_samester("BSAI-1B", false));
        dataList.add(new student_show_samester("BSSE-1B", false));
        return dataList;
    }
}
