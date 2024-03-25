package Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sfrfinalyearproject.R;

public class ad_message_option extends AppCompatActivity {
    Button faculty,student,alumni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_message_option);


        faculty=findViewById(R.id.afaculty);
        student=findViewById(R.id.astudent);
        alumni=findViewById(R.id.aalumni);

Faculty();
alumni();
student();










    }

    public void Faculty(){
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ad_message_option.this, admin_post_messagebody.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack
                // when the student_login activity starts
                finish();

            }
        });


    }






    public  void alumni(){

        alumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ad_message_option.this, ad_alumni_message_option.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack
                // when the student_login activity starts
                finish();

            }
        });



    }






    public  void  student(){

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ad_message_option.this, admin_post_messagebody.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack
                // when the student_login activity starts
                finish();

            }
        });
    }

}