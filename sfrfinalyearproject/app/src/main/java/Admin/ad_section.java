package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import Class_for_admin.sectionofstudent;


public class ad_section extends AppCompatActivity {
    private TableLayout tableLayout;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_section);


        ImageView imgback = findViewById(R.id.back);
        tableLayout = findViewById(R.id.tableLayout);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add a new row to the table on button click
                addRowToTable("New Name", "New Phone", "New Email");
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ad_section.this, Ad_messagebody.class);
                startActivity(intent);
                finish();
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ad_section.this, ad_student.class);
                startActivity(intent);
                finish();
            }
        });
        addInitialRowsToTable();
    }


    private void addInitialRowsToTable() {
        // Sample data
        List<String[]> initialData = new ArrayList<>();
        initialData.add(new String[]{ "2020-Arid-3535", "Rahmat", "rahmat@example.com"});
        initialData.add(new String[]{"2020-Arid03565", "jaweria", "rahmat@example.com"});
        initialData.add(new String[]{"2020-Arid-3623", "Faiza", "rahmat@example.com"});
        initialData.add(new String[]{ "2020-Arid-3535", "Rahmat", "rahmat@example.com"});
        initialData.add(new String[]{"2020-Arid03565", "jaweria", "rahmat@example.com"});
        initialData.add(new String[]{"2020-Arid-3623", "Faiza", "rahmat@example.com"});
        initialData.add(new String[]{ "2020-Arid-3535", "Rahmat", "rahmat@example.com"});
        initialData.add(new String[]{"2020-Arid03565", "jaweria", "rahmat@example.com"});
        initialData.add(new String[]{"2020-Arid-3623", "Faiza", "rahmat@example.com"});



        // Add each row to the table
        for (String[] rowData : initialData) {
            addRowToTable(rowData[0], rowData[1], rowData[2]);
        }
    }

    // Method to add a new row to the table
    private void addRowToTable(String name, String phone, String email) {
        // Inflate the row layout
        View rowView = LayoutInflater.from(this).inflate(R.layout.table_row, null);

        // Find views in the inflated layout
        CheckBox checkBox = rowView.findViewById(R.id.checkboxselect);
        TextView nameTextView = rowView.findViewById(R.id.nameTextView);
        TextView phoneTextView = rowView.findViewById(R.id.numberTextView); // Assuming this TextView represents phone number
        TextView emailTextView = rowView.findViewById(R.id.emailTextView);

        // Set data to views
        nameTextView.setText(name);
        phoneTextView.setText(phone);
        emailTextView.setText(email);

        // Add the inflated row to the TableLayout
        tableLayout.addView(rowView);
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        // Set other properties for textView if needed
        return textView;
    }

    private List<sectionofstudent> createStudentList() {
        List<sectionofstudent> students = new ArrayList<>();
        students.add(new sectionofstudent("2020-Arid-3535", "Rahmat", "rahmatyousafzai111@gmail.com"));
        students.add(new sectionofstudent("2020-Arid-3545", "Jaweria", "jaweria222@gmail.com"));
        students.add(new sectionofstudent("2020-Arid-3535", "Rahmat", "rahmatyousafzai111@gmail.com"));
        students.add(new sectionofstudent("2020-Arid-3545", "Jaweria", "jaweria222@gmail.com"));
        students.add(new sectionofstudent("2020-Arid-3535", "Rahmat", "rahmatyousafzai111@gmail.com"));
        students.add(new sectionofstudent("2020-Arid-3545", "Jaweria", "jaweria222@gmail.com"));
        return students;
    }
}
