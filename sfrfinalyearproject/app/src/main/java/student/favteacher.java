package student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

public class favteacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favteacher);
        ListView listView;
        ImageView imgBack;
        imgBack=findViewById(R.id.imgback);

        listView =findViewById(R.id.favtecher);
        // Sample data
        List<String> teacherNames = new ArrayList<>();
        teacherNames.add("Teacher 1");
        teacherNames.add("Teacher 2");
        teacherNames.add("Teacher 3");
        teacherNames.add("Teacher 3");
        teacherNames.add("Teacher 3");
        teacherNames.add("Teacher 3");
        teacherNames.add("Teacher 3");
        teacherNames.add("Teacher 3");
        teacherNames.add("Teacher 3");
        teacherNames.add("Teacher 3");
        teacherNames.add("Teacher 3");
        teacherNames.add("Teacher 3");
        teacherNames.add("Teacher 3");
        teacherNames.add("Teacher 3");
        // Add more teacher names as needed

        // Create an ArrayAdapter to populate the ListView

      //  TeachercustomAdopter adapter = new TeachercustomAdopter(this, teacherNames);
        // Set the adapter to the ListView
       // listView.setAdapter(adapter);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the student_login activity
                Intent intent = new Intent(favteacher.this, stdashboard.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack
                // when the student_login activity starts
                finish();


            }
        });
    }
}