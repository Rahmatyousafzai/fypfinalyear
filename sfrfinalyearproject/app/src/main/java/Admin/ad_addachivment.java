package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

public class ad_addachivment extends AppCompatActivity {
    ImageView imgback;
    Button btnsave;

    TextView viewach ,title,messagedescription;
Spinner catogary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_addachivment);
        viewach = findViewById(R.id.viewachevment);
        btnsave = findViewById(R.id.btnsave);
        imgback = findViewById(R.id.imageback);
        title = findViewById(R.id.title);
        messagedescription = findViewById(R.id.titlemessage);

        catogary = findViewById(R.id.selectcatagory);
        viewach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDisplayachvement();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDisplayachvement();
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDashboard();
            }
        });
        ArrayAdapter<String> categoryAdapter = null; // Corrected variable name

        List<String> categoryList = new ArrayList<>(); // Corrected variable name
        categoryList.add("TEACHER");
        categoryList.add("STUDENT");
        categoryList.add("ALUMNI");

// Use the ArrayAdapter constructor with three arguments: context, layout resource, and data list
        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);

// Assuming 'catogary' is a Spinner object, set the adapter
        catogary.setAdapter(categoryAdapter); // Make sure 'catogary' is defined and initialized as a Spinner object



    }

    private void navigateToDashboard() {
        Intent intent = new Intent(ad_addachivment.this,ad_dashboard.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack

        // when the student_login activity starts
        finish();
    }

    private void navigateToDisplayachvement() {
        Intent intent = new Intent(ad_addachivment.this,view_achievments.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack

        // when the student_login activity starts
        finish();
    }

}














