package Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sfrfinalyearproject.R;

public class  ad_alumni_message_option extends AppCompatActivity {
    public Button Mpublic,Mprvate,newalumni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_alumni_message_option);

        Mprvate=findViewById(R.id.aprivate);
        Mpublic=findViewById(R.id.apublic);
        newalumni=findViewById(R.id.newalumni);

mpublic();
mprivate();
newalumni();




















    }




    public void  mpublic(){

        Mpublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ad_alumni_message_option.this, admin_post_messagebody.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack
                // when the student_login activity starts
                finish();

            }
        });


    }
    public void  mprivate(){

        Mprvate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ad_alumni_message_option.this, admin_post_messagebody.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack
                // when the student_login activity starts
                finish();

            }
        });


    }
    public void  newalumni(){
        newalumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ad_alumni_message_option.this, adminAddAlumni.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack
                // when the student_login activity starts
                finish();

            }
        });


    }






}