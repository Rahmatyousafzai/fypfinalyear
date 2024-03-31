package Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import Class_for_admin.alumni;
import adopter.alumniAdopter;

public class adminAddAlumni extends AppCompatActivity {
    private RecyclerView recyclerView;
    private alumniAdopter adapter;
    private List<alumni> alumniList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_alumni);
         recyclerView = findViewById(R.id.Rcalumni);
ImageView imgback=findViewById(R.id.imgback);
        // Initialize data

imgback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});

        alumniList = new ArrayList<>();
        alumniList.add(new alumni(R.drawable.rahmatpic, "Rahmat"));
        alumniList.add(new alumni(R.drawable.sufi, "sufian"));
        alumniList.add(new alumni(R.drawable.zindalash, "Khuram"));
        alumniList.add(new alumni(R.drawable.bilal, "Muhammad Bilal"));

        // Add more data as neede
        // Initialize adapter
        adapter = new alumniAdopter(alumniList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}