package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class faculty_dashboard extends AppCompatActivity {


    TextView news,notification,message,student,favstudent;
    ImageView imgnews,imgNotification,imgmessage,imgstudent,imgfavstudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_dashboard);
             news = findViewById(R.id.news);
            notification=findViewById(R.id.txtnotification);
            message=findViewById(R.id.txtmessage);
            favstudent=findViewById(R.id.txtfavstudent);
            student =findViewById(R.id.textstudent);
            imgnews=findViewById(R.id.imgnews);
            imgNotification=findViewById(R.id.imgnptification);
            imgmessage=findViewById(R.id.imgmessage);
            imgstudent=findViewById(R.id.imgstudent);
            imgfavstudent=findViewById(R.id.imgfavstudent);
            // Set click listeners
            news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    news();
                }
            });
            notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notification();
                }
            });
            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    message();
                }
            });
            student.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    student();
                }
            });
            favstudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favstudent();
                }
            });

            imgnews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    news();
                }
            });
            imgNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notification();
                }
            });
            imgmessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    message();
                }
            });
            imgstudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    student();
                }
            });
            imgfavstudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favstudent();
                }
            });

            ////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////
        }

        private void message() {


            Intent intent = new Intent(faculty_dashboard.this, ft_message.class);
            startActivity(intent);
            finish();
        }
        private void student() {
            Intent intent = new Intent(faculty_dashboard.this, ftStudent.class);
            startActivity(intent);
            finish();

        }
        private void favstudent() {
            Intent intent = new Intent(faculty_dashboard.this, ftfavstudent.class);
            startActivity(intent);
            finish();
        }
        private void notification() {



        }


        public void  news() {

            Intent intent = new Intent(faculty_dashboard.this, faculty_dashboard.class);
            startActivity(intent);
            finish();
        }


    }
