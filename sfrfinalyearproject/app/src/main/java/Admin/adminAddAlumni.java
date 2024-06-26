package Admin;

import android.content.Intent;
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
        ///////////////////////////////////////////////////////////////////////////////
        ////////////////////////////onclickListener////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////

        imgback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(adminAddAlumni.this,ad_alumni_message_option.class);
        startActivity(intent);
        finish();

    }
});

        alumniList = new ArrayList<>();
        alumniList.add(new alumni(R.drawable.rahmatpic, "Rahmat"));
        alumniList.add(new alumni(R.drawable.sufi, "sufian"));
        alumniList.add(new alumni(R.drawable.zindalash, "Khuram"));
        alumniList.add(new alumni(R.drawable.bilal, "Muhammad Bilal"));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "Adeel Hassan"));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "Najeeb ur Rehman "));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "Najeeb ur Rehman "));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "Faiza Anwar"));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "Jaweria Abbasi"));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "Aqib wilayat"));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "Hamid"));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "Haya "));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "Anwar Ali"));  alumniList.add(new alumni(R.drawable.rahmatpic, "Rahmat"));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "Nouman Khan"));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "abdul Haq"));
        alumniList.add(new alumni(R.drawable.baseline_account_circle_24, "Hamza Satti"));

        // Add more data as neede
        // Initialize adapter
        alumniAdopter  adapter = new alumniAdopter(alumniList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}