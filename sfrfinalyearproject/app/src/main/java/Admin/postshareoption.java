package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class postshareoption extends AppCompatActivity {
    Button faculty,student,alumni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postshareoption);



        faculty=findViewById(R.id.afaculty);
        student=findViewById(R.id.astudent);
        alumni=findViewById(R.id.aalumni);







        alumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alumni();
            }
        });
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Faculty();
            }
        });


        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                student();

            }
        });

    }
    public void Faculty(){

        Intent intent = new Intent(postshareoption.this,ad_teachers.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack
        // when the student_login activity starts
        finish();



    }






    public  void alumni(){
        Intent intent = new Intent(postshareoption.this, ad_alumni_message_option.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack
        // when the student_login activity starts
        finish();





    }






    public  void  student(){
        Intent intent = new Intent(postshareoption.this, postbody.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack
        // when the student_login activity starts
        finish();

    }
}