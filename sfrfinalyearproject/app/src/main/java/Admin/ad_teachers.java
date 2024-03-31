package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import adopter.TeachercustomAdopter;
import adopter.userdetail;
import mydataapi.Apiservices;

public class ad_teachers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TeachercustomAdopter adapter;
    private List<userdetail> dataList;
    private Apiservices mAPIInterface;
    private ImageView pfimage, back;
    private TextView pfname, search;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_teachers);

        // Initialize views
        pfimage = findViewById(R.id.profileimage);
        pfname = findViewById(R.id.profelname);
        back = findViewById(R.id.back);
        search = findViewById(R.id.search);


        techerdata();

        // Initialize Retrofit instance from separate class


        // Create adapter and set it to RecyclerView

        // Get username from intent and set it to TextView
     /*   Intent intent = getIntent();
        String username = intent.getStringExtra("prfilename");
        pfname.setText(username);
*/
        // Handle back button click
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDashboard();
            }
        });

        // Setup search functionality

    }

    // Method to fetch data from API using Retrofit



    private void navigateToDashboard() {
        Intent intent = new Intent(ad_teachers.this, ad_dashboard.class);
        startActivity(intent);
        finish();
    }



    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }





    public void techerdata1(){





        RecyclerView recyclerView = findViewById(R.id.rvteacher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<userdetail> userList = new ArrayList<>();
        userdetail user1 = new userdetail("saeed",R.drawable.saeed,true);
        userdetail user2 = new userdetail("Rahmat yousafzai",R.drawable.rahmatpic,false);
        userdetail user3 = new userdetail("saeed",R.drawable.saeed,true);
        userdetail user4 = new userdetail("Rahmat yousafzai",R.drawable.rahmatpic,false);
        userdetail user5 = new userdetail("saeed",R.drawable.saeed,true);
        userdetail user6 = new userdetail("Rahmat yousafzai",R.drawable.rahmatpic,false);




        // Add more teacher data as needed

        TeachercustomAdopter adapter = new TeachercustomAdopter(userList);
        recyclerView.setAdapter(adapter);
    }




    public void techerdata() {
        RecyclerView recyclerView = findViewById(R.id.rvteacher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<userdetail> userList = new ArrayList<>();
        userList.add(new userdetail("saeed wattoo", R.drawable.saeed, true));
        userList.add(new userdetail("Rahmat  Yousafzai", R.drawable.rahmatpic, false));
        userList.add(new userdetail("saeed wattoo", R.drawable.saeed, true));
        userList.add(new userdetail("Rahmat  Yousafzai", R.drawable.rahmatpic, false));
        userList.add(new userdetail("saeed wattoo", R.drawable.saeed, true));
        userList.add(new userdetail("Rahmat  Yousafzai", R.drawable.rahmatpic, false));

        // Add more user details as needed

        TeachercustomAdopter adapter = new TeachercustomAdopter(userList);
        recyclerView.setAdapter(adapter);
    }


}
