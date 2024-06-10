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

import genral.templete;
import mydataapi.RetrofitClient;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class course_message_body extends AppCompatActivity {
Button btnsendwish;
    String username;
ImageView imgback,imgcourse,imgtemplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_message_body);
        btnsendwish=findViewById(R.id.sendwish);

        imgcourse=findViewById(R.id.addcourse);
        imgtemplete=findViewById(R.id.addtem);

       TextView profilename = findViewById(R.id.profelname);  // Initialize this
         ImageView  profile = findViewById(R.id.profileimage);          // Initialize this

         username = UserDataSingleton.getInstance().getUsername();

        teacherRepository userRepository = new teacherRepository();

        userRepository.fetchTeacherData(username, new teacherRepository.teacherRepositoryCallback() {
            @Override
            public void onSuccess(TeacherData data)
            {
                // Access user data fields

                String Disignation = data.getDisgnation();

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











        btnsendwish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendwish();
            }
        });


        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navegateback();


            }
        });
        imgtemplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateTemplate();

            }
        });
        imgcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
navigateCourse();
            }
        });







    }

    private void sendwish() {


        Intent intent=new Intent(course_message_body.this, faculty_dashboard.class);
        startActivity(intent);
        finish();
    }

    private void Navegateback() {
        Intent intent=new Intent(course_message_body.this, ft_send_message_by_course.class);
        startActivity(intent);
        finish();
    }

    private void navigateTemplate() {
        Intent intent=new Intent(course_message_body.this, templete.class);
        startActivity(intent);
        finish();
    }

    private void navigateCourse() {
        Intent intent=new Intent(course_message_body.this, ft_send_message_by_course.class);
        startActivity(intent);
        finish();
    }
}