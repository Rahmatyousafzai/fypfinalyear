package Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adopter.CustomAdapter_checkbox;

public class ad_student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_student);
        ListView listView = findViewById(R.id.CheckBoxlistView);
        Button adselect=findViewById(R.id.select);

        Spinner spinnerdeportment = findViewById(R.id.deparometspinner);
        Spinner spinnersamester = findViewById(R.id.semesterspinner);

        List<String> sem = new ArrayList<>();
        sem.add("Smester");
        sem.add("1");
        sem.add("2");
        sem.add("3");
        sem.add("4");
        sem.add("5");




        // Add more items as needed

        // Create an ArrayAdapter using the list of items
        ArrayAdapter<String> adaptersamester = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sem);

        // Specify the layout to use when the list of choices appears
        adaptersamester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerdeportment.setAdapter(adaptersamester);

        // Set a listener to handle item selection
        spinnerdeportment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Display a toast message with the selected item
                String selectedItem = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });








        // Create a list of items to populate the Spinner

        List<String> dep = new ArrayList<>();

        dep.add("BSIT");
        dep.add("BSAI");
        dep.add("BSCS");
        // Add more items as needed

        // Create an ArrayAdapter using the list of items
        ArrayAdapter<String> adapterdeportment = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, dep);

        // Specify the layout to use when the list of choices appears
        adapterdeportment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerdeportment.setAdapter(adapterdeportment);

        // Set a listener to handle item selection
        spinnerdeportment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Display a toast message with the selected item
                String selectedItem = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });



        List<String> dataList = new ArrayList<>(Arrays.asList("CS_3A", "IT_3A", "AI_3B","CS_3B", "IT_3C", "AI_3D"));
        CustomAdapter_checkbox adapter1 = new CustomAdapter_checkbox(this, dataList);
        listView.setAdapter(adapter1);


        adselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ad_student.this, ad_section.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack

                // when the student_login activity starts
                finish();

            }
        });







    }
}