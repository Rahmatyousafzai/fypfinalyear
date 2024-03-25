package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class message_option_section_alumni_student extends AppCompatActivity {


    TextView course,samester,alumni,favstudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_option_section_alumni_student);

        course=findViewById(R.id.txtcours);
        samester=findViewById(R.id.txtsamester);
        alumni=findViewById(R.id.txtalumni);
        favstudent=findViewById(R.id.txtfavstudent);



        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessagBycourse();



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

    private void sendMessagBycourse() {
        Intent intent=new Intent(message_option_section_alumni_student.this,ft_send_message_by_course.class);
        startActivity(intent);
        finish();
    }
}