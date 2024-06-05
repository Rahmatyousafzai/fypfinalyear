package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import genral.templete;

public class course_message_body extends AppCompatActivity {
Button btnsendwish;
ImageView imgback,imgcourse,imgtemplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_message_body);
        btnsendwish=findViewById(R.id.sendwish);

        imgcourse=findViewById(R.id.addcourse);
        imgtemplete=findViewById(R.id.addtem);


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