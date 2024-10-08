package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import mydataapi.RetrofitClient;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;


public class faculty_select_message_option extends AppCompatActivity {

Button Student, alumni,program ;

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
        program=findViewById(R.id.Displine);

        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);


        username = UserDataSingleton.getInstance().getUsername();

        teacherRepository userRepository = new teacherRepository();

        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data)
            {
                // Access user data fields

                String Disignation = data.getDisgnatione();

                String profileImage = data.getProfileImage();
                String firstName = data.getFirstName();
                String lastName = data.getLastName();

                Log.e("UserData.........", "Section Name:........... " + Disignation);

                // Optionally, update UI with user data
                TextView textView = findViewById(R.id.disgnation);
                String displayData = (Disignation);
                textView.setText(displayData);
                if (profileImage != null && !profileImage.isEmpty()) {
                    String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" +profileImage + ".jpg";
                    Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
                } else {
                    profile.setImageResource(R.drawable.baseline_account_circle_24);
                }



                String fullName = firstName+ " " + lastName;
                profilename.setText(fullName);

            }

            @Override
            public void onFailure(Exception e) {
                Log.e("SomeActivity", "Error fetching user data: " + e.getMessage());
                // Handle error case, e.g., show a toast or an error message
            }
        });


        alumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavegatetoAlumni();
            }
        });
Student.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        NavegateALL();
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


        startActivity(intent);
        finish();

    }


    public void NavegateALL(){


        Intent intent=new Intent(faculty_select_message_option.this, faculty_public_messagebody.class);



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