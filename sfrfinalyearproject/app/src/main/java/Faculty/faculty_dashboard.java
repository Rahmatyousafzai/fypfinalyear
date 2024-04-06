package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class faculty_dashboard extends AppCompatActivity {


    TextView news,notification,message,student,favstudent,typepost;
    ImageView imgnews,imgNotification,imgmessage,imgstudent,imgfavstudent,imgpost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_dashboard);


       /////////////////////////////////////////////////////////////////
      //////////////////////////////  Find view.////////////////////////
      /////////////////////////////////////////////////////////////////
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
            imgpost=findViewById(R.id.pluspost);
            typepost=findViewById(R.id.typepost);

        /////////////////////////////////////////////////////////////////////////
        ///////////////////////// Set click listeners////////////////////////////
        /////////////////////////////////////////////////////////////////////////
            imgpost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TYPEPOST();
                }
            });

            typepost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TYPEPOST();
                }
            });


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

        //////////////////////////////////////////////////////
        /////////////////////////FUNCTION/////////////////////
        //////////////////////////////////////////////////////
    private void TYPEPOST() {

        Intent intent=new Intent(faculty_dashboard.this,faculty_select_message_option.class);
        startActivity(intent);
        finish();
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
