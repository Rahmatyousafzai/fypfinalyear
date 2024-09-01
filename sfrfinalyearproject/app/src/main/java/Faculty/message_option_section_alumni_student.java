package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import mydataapi.RetrofitClient;
import studentClasses.TeacherData;
import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class message_option_section_alumni_student extends AppCompatActivity {


    TextView course,samester,alumni,favstudent;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_option_section_alumni_student);

        course=findViewById(R.id.txtcours);
        samester=findViewById(R.id.txtsamester);
        alumni=findViewById(R.id.txtalumni);
        favstudent=findViewById(R.id.txtfavstudent);

        TextView profilename = findViewById(R.id.profelname);
        ImageView profile = findViewById(R.id.profileimage);


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




        samester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendmessageToSemster();

            }
        });
        alumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMeassageToAlumni();
            }
        });
        favstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMessageToFavstudent();


            }
        });

    }

    private void SendmessageToSemster() {
    }

    private void SendMeassageToAlumni() {

    }

    private void sendMessageToFavstudent() {

    }

 
}