package student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import mydataapi.RetrofitClient;

public class sprfile extends AppCompatActivity {



    TextView Name,txtusename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprfile);
        ImageView imgprofile=findViewById(R.id.profelpicture);
       txtusename=findViewById(R.id.txtusername);
        Name=findViewById(R.id.txtname);




        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String firstname = intent.getStringExtra("firstname");
        String lastname = intent.getStringExtra("lastname");
        String profileimage = intent.getStringExtra("profileimage");

        // Concatenate first name and last name
        String fullName =(firstname + " " + lastname);

        txtusename.setText(username);
        // Set profile name
        Name.setText(fullName);
        Picasso.get().setIndicatorsEnabled(true);
        Picasso.get().setLoggingEnabled(true);
        // Load profile image using Picasso




        if (profileimage != null && !profileimage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" +profileimage+ ".jpg";
            Picasso.get().load(imageUrl).into(imgprofile);
        } else {
            imgprofile.setImageResource(R.drawable.baseline_account_circle_24);
        }

    }




    @Override
    public void onBackPressed() {
        // Navigate back to the login screen
        Intent intent = new Intent(this, stdashboard.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }
}