package student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import adopter.TeachercustomAdopter;

public class smassage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smassage);



        ListView listView;
        ImageView imgBack;
        listView=findViewById(R.id.lvmessag);
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

        TeachercustomAdopter adapter = new TeachercustomAdopter(this, teacherNames);
        // Set the adapter to the ListView
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the item that was clicked
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Do something with the selected item

                // Example: Start a new activity with the selected item
                Intent intent = new Intent(smassage.this, smessagebody.class);
                startActivity(intent);
            }
        });









    }
}