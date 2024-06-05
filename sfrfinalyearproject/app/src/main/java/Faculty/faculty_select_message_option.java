package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import mydataapi.RetrofitClient;




public class faculty_select_message_option extends AppCompatActivity {

Button Student, alumni, semseter,program ;

    private String username;
    private String firstName;
    private String lastName;
    private String profileImage;

TextView profilename;
ImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_select_message_option);

        Student=findViewById(R.id.Student);
        alumni=findViewById(R.id.Alumni);
        semseter=findViewById(R.id.Displine);
        program=findViewById(R.id.semeter);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);


        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        firstName = intent.getStringExtra("firstname");
        lastName = intent.getStringExtra("lastname");
        profileImage = intent.getStringExtra("profileimage");

        String fullName = firstName + " " + lastName;
        profilename.setText(fullName);

        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }

       Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavegateALL();

            }
        });

        alumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavegatetoAlumni();

            }
        });




        program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navegatetoprogram();

            }
        });


    }

    private void Navegatetoprogram() {

        Intent intent=new Intent(faculty_select_message_option.this, facultypapulationMessage.class);

        intent.putExtra("username", username);
        intent.putExtra("firstname", firstName);
        intent.putExtra("lastname", lastName);
        intent.putExtra("profileimage", profileImage);

        startActivity(intent);
        finish();

    }


    public void NavegateALL(){


        Intent intent=new Intent(faculty_select_message_option.this, faculty_public_messagebody.class);

        intent.putExtra("username", username);
        intent.putExtra("firstname", firstName);
        intent.putExtra("lastname", lastName);
        intent.putExtra("profileimage", profileImage);

        startActivity(intent);
        finish();


    }




    public void NavegatetoAlumni(){


Intent intent=new Intent(faculty_select_message_option.this,alumnimessage.class);
startActivity(intent);
finish();
    }

    @Override
    public void onBackPressed() {
        // Navigate back to the login screen
        Intent intent = new Intent(this, faculty_dashboard.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }


}